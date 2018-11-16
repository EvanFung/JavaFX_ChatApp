package client.handler.impl;

import client.handler.Handler;
import com.alibaba.fastjson.JSON;
import common.ReturnMessage;
import javafx.application.Platform;
import javafx.scene.control.Alert;

public class RegisterHdl implements Handler {
    @Override
    public Object handle(Object obj) {
        if(obj != null) {
            try {
                ReturnMessage returnMessage = JSON.parseObject(obj.toString(),ReturnMessage.class);
                if(returnMessage.isSuccess()) {
                    // show ok dialog
                    System.out.println(returnMessage.getContent());
                    Platform.runLater(() -> {
                        showSignupWindow("Signup successful",returnMessage.getContent().toString());
                    });

                } else {
                    // show alert dialog
                    Platform.runLater(() -> {
                        showSignupWindow("Signup failed",returnMessage.getMessage());
                    });
                }

            } catch (Exception e) {
                System.out.println("Handle register failed! " + e.getMessage());
            }
        }
        return null;
    }

    public void showSignupWindow(String title,String msg) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setContentText(msg);
        alert.show();
    }

}
