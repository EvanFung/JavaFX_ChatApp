package client;

import client.callback.Callback;
import common.ConstantValue;

import java.io.IOException;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Client {
    private final Socket socket;
    private String from;
    private final ExecutorService pool;
    private Callback callback;

    public Client(Callback callback) throws IOException {
        this.socket = new Socket(ConstantValue.SERVER_IP, ConstantValue.SERVER_PORT);
        this.pool = Executors.newFixedThreadPool(2);
        this.callback = callback;
    }

    public void start() {
        pool.execute(new ReceiveListener(socket, callback));
    }

    public void keepAlive(String from) {
        this.from = from;
        pool.execute(new KeepAliveDog(socket, from));
    }

    public Socket getSocket() {
        return socket;
    }

    public String getFrom() {
        return from;
    }

}
