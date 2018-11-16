package server.handler;

import java.net.Socket;

public interface SocketHandler {
    public Object handle(Socket client, Object data);
}
