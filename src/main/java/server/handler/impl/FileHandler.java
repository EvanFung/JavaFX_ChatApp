package server.handler.impl;

import com.alibaba.fastjson.JSON;
import common.*;
import server.SocketHolder;
import server.SocketWrapper;
import server.handler.SocketHandler;
import util.StringUtil;

import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.util.ArrayList;

public class FileHandler implements SocketHandler {
    @Override
    public Object handle(Socket client, Object data) {
        if (client != null) {
            FileMessage message = JSON.parseObject(data.toString(), FileMessage.class);
            if (StringUtil.isNotEmpty(message.getFrom()) && StringUtil.isNotEmpty(message.getTo())) {
                if (SocketHolder.keySet().contains(message.getFrom())) {
                    System.out.println(message.getTo() + ":room");
                    ArrayList<SocketWrapper> room = SocketHolder.rooms.get(message.getTo());
                    for (int i = 0; i < room.size(); i++) {
                        SocketWrapper wrapper = room.get(i);
                        if (wrapper != null) {
                            SendHelper.send(wrapper.getSocket(), message);
                            try {
                                if (client != null && wrapper.getSocket() != null && message.getSize() > 0) {
                                    InputStream is = client.getInputStream();
                                    OutputStream os = wrapper.getSocket().getOutputStream();
                                    int total = 0;
                                    while (!client.isClosed() && !wrapper.getSocket().isClosed()) {
                                        if (is.available() > 0) {
                                            byte[] buff = new byte[ConstantValue.BUFF_SIZE];
                                            int len = -1;
                                            while (is.available() > 0 && (len = is.read(buff)) != -1) {
                                                os.write(buff, 0, len);
                                                total += len;
                                                System.out.println("SEND BUFF[" + len + "]");
                                            }
                                            os.flush();
                                            if (total >= message.getSize()) {
                                                System.out.println("SEND BUFF [OK]");
                                            }
                                        }
                                    }

                                    //after file was successfully sent
                                    //we give the response message to client who send this file
                                    ReturnMessage rm = new ReturnMessage();
                                    rm.setKey(Key.TIP);
                                    rm.setSuccess(true);
                                    rm.setContent(I18N.INFO_FILE_SEND_SUCCESSFULLY);
                                    rm.setFrom(message.getTo()).setTo(message.getFrom());
                                    rm.setOwner(ConstantValue.SERVER_NAME);

                                    SendHelper.send(client, rm);

                                    //receive successfully send to receiver of the file
                                    rm.setContent(I18N.INFO_FILE_RECEIVE_SUCCESSFULLY);
                                    rm.setFrom(message.getFrom());
                                    rm.setTo(message.getTo());
                                    SendHelper.send(wrapper.getSocket(), rm);

                                }
                            } catch (Exception e) {
                                System.out.println("Handle file failed" + e.getMessage());
                            }
                        }
                    }

                }
            }
        }
        return null;
    }
}
