package client.callback;

import java.net.Socket;

public interface Callback {
    public void doWork(Socket server, Object data);
}
