package server.handler.impl;

import com.alibaba.fastjson.JSON;
import common.*;
import common.dto.ClientListUserDTO;
import common.dto.GroupClientListDTO;
import server.SocketHolder;
import server.SocketWrapper;
import server.handler.SocketHandler;
import server.user.service.UserService;
import util.StringUtil;

import java.net.Socket;
import java.util.Date;

import static common.Key.LISTUSER;

public class LoginHandler implements SocketHandler {
    private UserService userService = new UserService();
    @Override
    public Object handle(Socket client, Object data) {
        ReturnMessage result = new ReturnMessage();
        result.setSuccess(false);
        if(data != null) {
            LoginMessage message = JSON.parseObject(data.toString(), LoginMessage.class);
            if (StringUtil.isNotEmpty(message.getUsername()) && StringUtil.isNotEmpty(message.getPassword())) {
                if (userService.login(message.getUsername(), message.getPassword()) != null) {
                    result.setSuccess(true);
                } else {
                    result.setMessage(I18N.INFO_LOGIN_ERROR_DATA);
                }
                result.setFrom(ConstantValue.SERVER_NAME).setTo(message.getUsername());
            } else {
                result.setMessage(I18N.INFO_LOGIN_EMPTY_DATA);
            }

            // AFTER LOGIN
            result.setKey(Key.LOGIN);
            if (result.isSuccess()) { // HOLD SOCKET
                System.out.println("[result.getTo]= "+result.getTo());
                SocketHolder.put(result.getTo(), new SocketWrapper(client, new Date()));
            }
            SendHelper.send(client,result);
            if(result.isSuccess()) {
                //once the user login successfully, we sent a list of group and online's user
                //set clients list and group list
                GroupClientListDTO groupClientListDTO = new GroupClientListDTO();
                groupClientListDTO.setListGroup(SocketHolder.keySetOfRoom());
                groupClientListDTO.setListUser(SocketHolder.keySet());

                ClientListUserDTO dto = new ClientListUserDTO();
                dto.setListUser(SocketHolder.keySet());
                result.setContent(groupClientListDTO).setKey(LISTUSER);
                SendHelper.send(client,result);
            }
        }
        return null;
    }
}
