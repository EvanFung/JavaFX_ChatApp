package client;

import client.callback.Callback;
import com.alibaba.fastjson.JSON;
import common.BaseMessage;
import common.ConstantValue;
import common.MessageType;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.Socket;
import java.util.Date;

public class ReceiveListener implements Runnable {
    private final Socket socket;
    private final Callback callback;


    public ReceiveListener(Socket socket, Callback callback) {
        this.socket = socket;
        this.callback = callback;
    }


    @Override
    public void run() {
        if (socket != null) {
            while (!socket.isClosed()) {
                try {
                    InputStream is = socket.getInputStream();
                    String line = null;
                    StringBuffer sb = null;

                    if (is.available() > 0) {

                        BufferedReader bufr = new BufferedReader(new InputStreamReader(is));
                        sb = new StringBuffer();
                        while (is.available() > 0 && (line = bufr.readLine()) != null) {
                            sb.append(line);
                        }
                        System.out.println("RECEIVE [" + sb.toString() + "] AT " + new Date());

                        callback.doWork(socket, sb.toString());
                        BaseMessage message = JSON.parseObject(sb.toString(),BaseMessage.class);
                        if(message.getType() == MessageType.FILE) {
                            Thread.sleep(ConstantValue.MESSAGE_PERIOD);
                        }
                    } else {
                        Thread.sleep(ConstantValue.MESSAGE_PERIOD);
                    }
                } catch (Exception e) {
                    System.out.println("Client send message failed !" + e.getMessage());
                }
            }
        }

    }
}
