package client.handler;

import client.handler.impl.*;
import common.Key;

public class HF {

    public static Handler getHandler(String key) {
        switch (key) {
            case Key.LOGIN:
                return new LoginHdl();
            case Key.REGISTER:
                return new RegisterHdl();
            case Key.LISTUSER:
                return new ListUserHdl();
            case Key.ENTERGROUP:
                return new EnterGroupHdl();
            case Key.TIP:
                return new TipHdl();
            case Key.GROUP:
                return new GroupHdl();
        }
        return null;
    }
}
