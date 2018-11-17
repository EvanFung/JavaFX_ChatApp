package common;

public interface ConstantValue {
    /**
     * 缓冲区大小
     */
    int BUFF_SIZE = 1024;
    /**
     * 调试模式
     */
    int DEBUG_LEVEL = 0;
    /**
     * 客户端端接收文件的存储路径
     */
    String CLIENT_RECEIVE_DIR = "./clientFile";
    /**
     * 服务器接受文件的存储路径
     */
    String SERVER_RECEIVE_DIR = "./serverFile";

    /**
     * KEEPALIVE PERIOD'second
     */
    int KEEP_ALIVE_PERIOD = 20;
    /**
     * 最大socket线程处理数
     */
    int MAX_POOL_SIZE = 30;
    /**
     * <pre>
     * 检测是否有新的数据时间间隔'ms
     * (server.SocketDispatch,client.ReceiveListener,SendHelper)
     * 使用同一个Thread.sleep时间保证数据能正确接收到，同时降低CPU的使用率
     * !!!!! -非常重要- !!!!!
     * </pre>
     */
    int MESSAGE_PERIOD = 500;
    /**
     * 服务器IP地址
     */
    String SERVER_IP = "127.0.0.1";
    /**
     * 服务器名称，用户注册不能使用此用户名
     */
    String SERVER_NAME = "EVANFENG";
    /**
     * 服务器端口
     */
    int SERVER_PORT = 8888;
    /**
     * SOCKET超时时间'second
     */
    int TIME_OUT = 120;


    String CHAT_TYPE_TEXT = "TEXT";

    String CHAT_TYPE_FILE = "FILE";
}
