package server.handler.impl;

import com.alibaba.fastjson.JSON;
import common.ChatMessage;
import common.SendHelper;
import server.SocketHolder;
import server.SocketWrapper;
import server.group.service.GroupService;
import server.handler.SocketHandler;
import util.StringUtil;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Date;

public class ChatHandler implements SocketHandler {
    GroupService groupService = new GroupService();
    @Override
    public Object handle(Socket client, Object data) {
        if (data != null) {
            ChatMessage message = JSON.parseObject(data.toString(), ChatMessage.class);
            if (StringUtil.isNotEmpty(message.getFrom())) {
                String owner = message.getFrom();
                message.setOwner(owner);
                ArrayList<SocketWrapper> trash = new ArrayList<>();
                ArrayList<SocketWrapper> room = groupService.getRooms().get(message.getRoomName());
                synchronized (room) {
                    for (int i = 0; i < room.size(); i++) {
                        SocketWrapper wrapper = room.get(i);
                        try {
                            PrintWriter out = new PrintWriter(wrapper.getSocket().getOutputStream());
                            System.out.println(" [" + JSON.toJSON(message) + "] SEND AT " + new Date());
                            out.println(JSON.toJSON(message));
                            out.flush();
                        } catch (IOException e) {
                            trash.add(wrapper);
                        }
                    }
                    trash.removeAll(trash);
                }
            }

        }
        return null;
    }
}
