package server.handler.impl;

import com.alibaba.fastjson.JSON;
import common.*;
import server.handler.SocketHandler;
import server.user.service.UserService;
import util.StringUtil;

import java.net.Socket;

public class RegisterHandler  implements SocketHandler {
    private UserService userService = new UserService();


    @Override
    public Object handle(Socket client, Object data) {
        ReturnMessage result = new ReturnMessage();
        result.setSuccess(false).setFrom(ConstantValue.SERVER_NAME);
        if (data != null) {
            RegisterMessage message = JSON.parseObject(data.toString(), RegisterMessage.class);
            if (StringUtil.isNotEmpty(message.getUsername()) && StringUtil.isNotEmpty(message.getPassword())) {
                if (userService.register(message.getUsername(), message.getPassword()) != null) {
                    result.setSuccess(true).setContent(I18N.INFO_REGISTER_OK);
                } else {
                    result.setMessage(I18N.INFO_REGISTER_CLIENT_EXIST);
                }
            } else {
                result.setMessage(I18N.INFO_REGISTER_EMPTY_DATA);
            }

            if (StringUtil.isNotEmpty(message.getUsername())) {
                result.setTo(message.getUsername());
            }
            // AFTER REGISTER
            result.setKey(Key.REGISTER);
            SendHelper.send(client, result);
        }
        return null;
    }
}
