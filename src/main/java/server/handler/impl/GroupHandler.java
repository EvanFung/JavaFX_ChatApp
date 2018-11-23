package server.handler.impl;

import com.alibaba.fastjson.JSON;
import common.*;
import common.dto.GroupClientListDTO;
import server.SocketHolder;
import server.SocketWrapper;
import server.group.service.GroupService;
import server.handler.SocketHandler;
import util.StringUtil;

import java.net.Socket;
import java.util.ArrayList;

import static common.Key.LISTUSER;

public class GroupHandler implements SocketHandler {
    private GroupService groupService = new GroupService();

    @Override
    public Object handle(Socket client, Object data) {
        ReturnMessage result = new ReturnMessage();
        result.setSuccess(false).setFrom(ConstantValue.SERVER_NAME);
        if (data != null) {
            GroupMessage message = JSON.parseObject(data.toString(), GroupMessage.class);
            if (StringUtil.isNotEmpty(message.getGroupName()) && StringUtil.isNotEmpty(message.getCreator()) && StringUtil.isNotEmpty(message.getPassword())) {
                if (groupService.createGroup(message.getGroupName(),message.getPassword(),message.getCreator())) {
                    //create room
                    ArrayList<SocketWrapper> room = new ArrayList<>();
                    boolean flag = groupService.putRoom(message.getGroupName(),room);
                    if(flag) {
                        result.setSuccess(true).setContent(I18N.INFO_GROUP_OK);
                    } else {
                        result.setMessage(I18N.INFO_GROUP_FAILED);
                    }
                } else {
                    result.setMessage(I18N.INFO_GROUP_EXIST);
                }
            } else {
                result.setMessage(I18N.INFO_GROUP_EMPTY_DATA);
            }

            if(StringUtil.isNotEmpty(message.getCreator())) {
                result.setTo(message.getCreator());
            }

            //ALTER GROUP WAS CREATED
            result.setKey(Key.GROUP);
            //ALTER ***ALL*** CLIENTS WAS UPDATED??

//            SendHelper.send(client,result);

            for(String s: SocketHolder.keySet()) {
                Socket socket = SocketHolder.get(s).getSocket();
                SendHelper.send(socket,result);
            }


            GroupClientListDTO groupClientListDTO = new GroupClientListDTO();
            groupClientListDTO.setListGroup(groupService.keySetOfRoom());
            groupClientListDTO.setListUser(SocketHolder.keySet());

            //put the group and user list into the result message
            result.setContent(groupClientListDTO).setKey(LISTUSER);
            //ALERT ALL CLIENTS THAT THE GROULIST AND CLIENT LIST WAS CREATE
//            SendHelper.send(client,result);

            for(String s: SocketHolder.keySet()) {
                Socket socket = SocketHolder.get(s).getSocket();
                SendHelper.send(socket,result);
            }
        }
        return null;
    }
}
