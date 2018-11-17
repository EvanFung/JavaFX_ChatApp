package server.handler;

import common.MessageType;
import server.handler.impl.*;

public class HandlerFactory {
    // can not create instance
    private HandlerFactory(){}

    public static SocketHandler getHandler(int type) {
        switch (type) {
            case MessageType.ALIVE: // usually use
                return new AliveHandler();
            case MessageType.REGISTER:
                return new RegisterHandler();
            case MessageType.LOGIN:
                return new LoginHandler();
            case MessageType.GROUP:
                return new GroupHandler();
            case MessageType.ENTERGROUP:
                return new EnterGroupHandler();
            case MessageType.CHAT:
                return new ChatHandler();
            case MessageType.FILE:
                return new FileHandler();
            case MessageType.LEAVEGROUP:
                return new LeaveGroupHandler();
        }
        return null; // NullPointException
    }
}
