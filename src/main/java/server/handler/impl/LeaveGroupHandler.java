package server.handler.impl;

import com.alibaba.fastjson.JSON;
import common.I18N;
import common.Key;
import common.LeaveGroupMessage;
import common.ReturnMessage;
import server.SocketHolder;
import server.group.service.GroupService;
import server.handler.SocketHandler;

import java.net.Socket;

public class LeaveGroupHandler implements SocketHandler {
    GroupService groupService = new GroupService();
    @Override
    public Object handle(Socket client, Object data) {
        if (data != null) {
            ReturnMessage rm = new ReturnMessage();
            rm.setSuccess(true);
            rm.setKey(Key.LEAVEGROUP);
            LeaveGroupMessage lgm = JSON.parseObject(data.toString(), LeaveGroupMessage.class);
            rm.setContent(I18N.INFO_LEAVE_GROUP_SUCCESS + "[ " + lgm + " ]");
            groupService.leaveRoom(lgm.getGroupName(),SocketHolder.get(lgm.getUserName()));
        }
        return null;
    }
}
