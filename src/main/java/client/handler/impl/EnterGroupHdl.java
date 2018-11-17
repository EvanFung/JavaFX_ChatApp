package client.handler.impl;

import client.handler.Handler;
import com.alibaba.fastjson.JSON;
import common.EnterGroupMessage;
import common.ReturnMessage;
import javafx.application.Platform;
import javafx.scene.control.Alert;

public class EnterGroupHdl implements Handler {
    public static String currentRoom;

    @Override
    public Object handle(Object obj) {
        if(obj != null) {
            try {
                ReturnMessage rm = JSON.parseObject(obj.toString(), ReturnMessage.class);
                if(!rm.isSuccess()) {
                    Platform.runLater(()-> {
                        showSignupWindow("WARNING","ENTER GROUP FAILED");
                    });
                } else {
                    EnterGroupMessage egm = JSON.parseObject(rm.getContent().toString(),EnterGroupMessage.class);
                    currentRoom = egm.getGroupName();

                }
            } catch (Exception e) {
                System.out.println("Handler enter group failed"+e.getMessage());
            }
        }
        return null;
    }

    public static void setCurrentRoom(String currentRoom) {
        EnterGroupHdl.currentRoom = currentRoom;
    }

    public static String  getCurrentRoom() {
        return currentRoom;
    }


    public void showSignupWindow(String title, String msg) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setContentText(msg);
        alert.show();
    }
}
