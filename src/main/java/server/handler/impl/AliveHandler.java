package server.handler.impl;

import com.alibaba.fastjson.JSON;
import common.BaseMessage;
import server.SocketHolder;
import server.SocketWrapper;
import server.handler.SocketHandler;
import util.StringUtil;

import java.net.Socket;
import java.util.Date;

public class AliveHandler implements SocketHandler {
    @Override
    public Object handle(Socket client, Object data) {
        if (data != null) {
            BaseMessage message = JSON.parseObject(data.toString(), BaseMessage.class);
            if (StringUtil.isNotEmpty(message.getFrom())) {
                SocketWrapper wrapper = SocketHolder.get(message.getFrom());
                if (wrapper != null) {
                    wrapper.setLastAliveTime(new Date()); // KEEP SOCKET ...
                    SocketHolder.put(message.getFrom(), wrapper);
                }
            }
        }
        return null;
    }
}
