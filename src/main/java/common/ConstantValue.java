package common;

public interface ConstantValue {
    /**
     * buffer size
     */
    int BUFF_SIZE = 1024;
    /**
     * client side receiving file path
     */
    String CLIENT_RECEIVE_DIR = "./clientFile";
    /**
     * server side receiving file path
     */
    String SERVER_RECEIVE_DIR = "./serverFile";

    /**
     * KEEPALIVE PERIOD'second
     */
    int KEEP_ALIVE_PERIOD = 20;
    /**
     * the maximum number of thread, maximum number of clients
     */
    int MAX_POOL_SIZE = 30;
    /**
     * <pre>
     * the time interval which to check whether there is a new data(ms)
     * (server.SocketDispatch,client.ReceiveListener,SendHelper)
     * if using the same thread sleep time, we can guarantee the data will be received correctly and lower the cpu rate
     * !!!!! - important - !!!!!
     * </pre>
     */
    int MESSAGE_PERIOD = 500;
    /**
     * server IP address
     */
    String SERVER_IP = "127.0.0.1";
    /**
     * server Ip address , users cannot register it
     */
    String SERVER_NAME = "EVANFENG";
    /**
     * server port number
     */
    int SERVER_PORT = 8888;
    /**
     * timeout of the socket (second)
     */
    int TIME_OUT = 120;

    /**
     * chat message type
     */
    String CHAT_TYPE_TEXT = "TEXT";

    String CHAT_TYPE_FILE = "FILE";
}
