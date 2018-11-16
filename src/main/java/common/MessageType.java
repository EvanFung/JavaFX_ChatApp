package common;

public interface MessageType {
    /**
     * 注册
     */
    int REGISTER = 0;
    /**
     * 登陆
     */
    int LOGIN = 1;
    /**
     * 退出
     */
    int LOGOUT = 2;
    /**
     * 聊天
     */
    int CHAT = 3;
    /**
     * 文件传输
     */
    int FILE = 4;
    /**
     * 心跳
     */
    int ALIVE = 5;
    /**
     * 服务器回馈
     */
    int RETURN = 6;
    /**
     * 群組 CREATE GROUP
     */
    int GROUP = 7;
    /**
     * ENTER GROUP
     */
    int ENTERGROUP = 8;
}
