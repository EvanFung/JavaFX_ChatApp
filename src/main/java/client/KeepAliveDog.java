package client;

import com.alibaba.fastjson.JSON;
import common.AliveMessage;
import common.ConstantValue;

import java.io.PrintWriter;
import java.net.Socket;

public class KeepAliveDog implements Runnable {
    private final Socket socket;
    private final String from;

    public KeepAliveDog(Socket socket, String from) {
        this.socket = socket;
        this.from = from;
    }

    @Override
    public void run() {
        while (socket != null && !socket.isClosed()) {
            try {

                PrintWriter out = new PrintWriter(socket.getOutputStream());
                AliveMessage message = new AliveMessage();
                message.setFrom(from);
                out.println(JSON.toJSON(message));
                out.flush();

                Thread.sleep(ConstantValue.KEEP_ALIVE_PERIOD * 1000);

            } catch (Exception e) {
                System.out.println("Client send message failed !" + e.getMessage());
            }
        }
    }
}
