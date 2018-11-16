package server;

import client.handler.Handler;
import com.alibaba.fastjson.JSON;
import common.BaseMessage;
import common.ConstantValue;
import common.MessageType;
import server.handler.HandlerFactory;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.Socket;
import java.util.Date;

public class SocketDispatcher implements Runnable {
    private final Socket socket;

    public SocketDispatcher(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {

        if(socket != null) {
            while (!socket.isClosed()) {
                try {
                    InputStream is = socket.getInputStream();
                    String line = null;
                    StringBuffer sb = null;

                    if(is.available() > 0) {

                        BufferedReader bufr = new BufferedReader(new InputStreamReader(is));
                        sb = new StringBuffer();
                        while (is.available() > 0 && (line = bufr.readLine()) != null) {
                            sb.append(line);
                        }

                        System.out.println("RECEIVE [" + sb.toString() + "] AT " + new Date());
                        BaseMessage message = JSON.parseObject(sb.toString(), BaseMessage.class);
                        switch (message.getType()) {
                            case MessageType.ALIVE:
                                HandlerFactory.getHandler(MessageType.ALIVE).handle(socket, sb.toString());
                                break;
                            case MessageType.REGISTER:
                                HandlerFactory.getHandler(MessageType.REGISTER).handle(socket,sb.toString());
                                break;
                            case MessageType.LOGIN:
                                HandlerFactory.getHandler(MessageType.LOGIN).handle(socket,sb.toString());
                                break;
                            case MessageType.GROUP:
                                HandlerFactory.getHandler(MessageType.GROUP).handle(socket,sb.toString());
                                break;
                            case MessageType.ENTERGROUP:
                                HandlerFactory.getHandler(MessageType.ENTERGROUP).handle(socket,sb.toString());
                                break;
                            case MessageType.CHAT:
                                HandlerFactory.getHandler(MessageType.CHAT).handle(socket,sb.toString());
                                break;
                            case MessageType.FILE:
                                HandlerFactory.getHandler(MessageType.FILE).handle(socket,sb.toString());
                                break;
                        }

                    } else {
                        Thread.sleep(ConstantValue.MESSAGE_PERIOD);
                    }

                }catch (Exception e) {
                    System.out.println(e.getMessage());
                }

            }
        }

    }
}
