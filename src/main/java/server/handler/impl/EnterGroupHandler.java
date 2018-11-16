package server.handler.impl;

import com.alibaba.fastjson.JSON;
import common.*;
import server.SocketHolder;
import server.SocketWrapper;
import server.handler.SocketHandler;
import util.StringUtil;

import java.net.Socket;
import java.util.ArrayList;

public class EnterGroupHandler implements SocketHandler {

    @Override
    public Object handle(Socket client, Object data) {
        ReturnMessage rm = new ReturnMessage();
        rm.setSuccess(false).setFrom(ConstantValue.SERVER_NAME);
        if (data != null) {
            EnterGroupMessage egm = JSON.parseObject(data.toString(), EnterGroupMessage.class);
            if (StringUtil.isNotEmpty(egm.getGroupName())) {
                boolean flat = SocketHolder.enterRoom(egm.getGroupName(), SocketHolder.get(egm.getFrom()));
                if(!flat) {
                    rm.setMessage(I18N.INFO_GROUP_ENTER_FAILED).setKey(Key.ENTERGROUP);
                    SendHelper.send(client,rm);
                } else {
                    rm.setContent(egm).setKey(Key.ENTERGROUP).setSuccess(true);
                    SendHelper.send(client,rm);
                }
//                ArrayList<SocketWrapper> list = SocketHolder.getRoom(egm.getGroupName());
//
//                for (int i = 0; i < list.size(); i++) {
//                    System.out.println(list.get(i).getSocket().toString());
//                }

            }
        }


        return null;
    }
}
