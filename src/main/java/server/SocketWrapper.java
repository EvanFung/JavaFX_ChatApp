package server;

import java.net.Socket;
import java.util.Date;

public class SocketWrapper {

    private Socket socket;
    private Date lastAliveTime;

    // full constructor
    public SocketWrapper(Socket socket, Date lastAliveTime) {
        this.socket = socket;
        this.lastAliveTime = lastAliveTime;
    }
    public Socket getSocket() {
        return socket;
    }
    public void setSocket(Socket socket) {
        this.socket = socket;
    }
    public Date getLastAliveTime() {
        return lastAliveTime;
    }
    public void setLastAliveTime(Date lastAliveTime) {
        this.lastAliveTime = lastAliveTime;
    }
}
