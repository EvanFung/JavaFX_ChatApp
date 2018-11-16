package client;

import client.callback.Callback;
import common.ConstantValue;

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
