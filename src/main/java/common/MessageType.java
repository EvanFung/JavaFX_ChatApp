package common;

/**
 * SERVER RECEIVE MESSAGE TYPE
 */
public interface MessageType {
    /**
     * REGISTER
     */
    int REGISTER = 0;
    /**
     * LOGIN
     */
    int LOGIN = 1;
    /**
     * LOGOUT SYSTEM
     */
    int LOGOUT = 2;
    /**
     * CHAT
     */
    int CHAT = 3;
    /**
     * FILE TRANSMISSION
     */
    int FILE = 4;
    /**
     * SOCKET HEARTBEAT
     */
    int ALIVE = 5;
    /**
     * SERVER RESPONSE
     */
    int RETURN = 6;
    /**
     * CREATE GROUP
     */
    int GROUP = 7;
    /**
     * ENTER GROUP
     */
    int ENTERGROUP = 8;
    /**
     * LEAVE GROUP
     */
    int LEAVEGROUP = 9;
}
