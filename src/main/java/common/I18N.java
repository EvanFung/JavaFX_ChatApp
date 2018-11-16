package common;

public interface I18N {

    //---------------------------------
    // TEXT
    //---------------------------------
    /**
     * APP_NAME
     */
    String TEXT_APP_NAME = "BU-CHAT";
    /**
     * 登陆
     */
    String TEXT_LOGIN = "LOGIN";
    /**
     * 注册
     */
    String TEXT_REGISTER = "REGISTER";
    /**
     * 账号
     */
    String TEXT_USERNAME = "USERNAME";
    /**
     * 密码
     */
    String TEXT_PASSWORD = "PASSWORD";

    //---------------------------------
    // BTN
    //---------------------------------
    /**
     * 注册
     */
    String BTN_REGISTER = "REGISTER";
    /**
     * 登陆
     */
    String BTN_LOGIN = "LOGIN";
    /**
     * 退出
     */
    String BTN_EXIT = "QUIT";
    /**
     * 发送
     */
    String BTN_SEND = "SEND";
    /**
     * 发送文件
     */
    String BTN_SEND_FILE = "SEND FILE";

    //---------------------------------
    // INFO
    //---------------------------------
    /**
     * 请填写注册账号和密码
     */
    String INFO_REGISTER_EMPTY_DATA = "PLEASE FILL UP THE USERNAME OR PASSWORD";
    /**
     * 用户已存在
     */
    String INFO_REGISTER_CLIENT_EXIST = "THIS USER ALREADY EXISTED";
    /**
     * 注册成功
     */
    String INFO_REGISTER_OK = "REGISTER SUCCESS";
    /**
     * 请输入登陆账号和密码
     */
    String INFO_LOGIN_EMPTY_DATA = "PLEASE ENTER YOUR USERNAME AND PASSWORD";
    /**
     * 登陆账号或密码错误
     */
    String INFO_LOGIN_ERROR_DATA = "WRONG USERNAME OR PASSWORD";
    /**
     * 暂不支持文件群发
     */
    String INFO_FILE_TO_ALL_ERROR = "NOT SUPPORT SEND FILE";
    /**
     * 文件发送成功
     */
    String INFO_FILE_SEND_SUCCESSFULLY = "FILE SENT SUCCESS";
    /**
     * 文件接收成功
     */
    String INFO_FILE_RECEIVE_SUCCESSFULLY = "FILE RECEIVED SUCCESS";
    /**
     * 建立群组成功
     */
    String INFO_GROUP_OK = "CREATE GROUP SUCCESS";
    /**
     * 群组已存在
     */
    String INFO_GROUP_EXIST = "THE GROUP ALREADY EXIST";
    /**
     * 请填写群组名字
     */
    String INFO_GROUP_EMPTY_DATA = "PLEASE FILL UP THE GROUP NAME";
    /**
     * 进入群组成功
     */
    String INFO_GROUP_ENTER_SUCCESS = "ENTER GROUP SUCCESS";
    /**
     * 进入群组失败
     */
    String INFO_GROUP_ENTER_FAILED = "THE GROUP DOESN'T EXIST, ENTER GROUP FAIL";
}
