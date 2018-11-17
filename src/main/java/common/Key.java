package common;

/**
 *  SERVER PUSH MESSAGE KEY
 */
public interface Key {
    /**
     * 登陆
     */
    String LOGIN = "LOGIN";
    /**
     * 注册
     */
    String REGISTER = "REGISTER";
    /**
     * Client 上线 / 离线 通知
     */
    String NOTIFY = "NOTIFY";
    /**
     * 拉去在线 Client 列表
     */
    String LISTUSER = "LISTUSER";
    /**
     * TIP 提示
     */
    String TIP = "TIP";
    /**
     * 群组创建
     */
    String GROUP = "GROUP";
    /**
     * ENTER GROUP
     */
    String ENTERGROUP = "ENTERGROUP";
    /**
     * LEAVE GROUP
     */
    String LEAVEGROUP = "LEAVEGROUP";
}
