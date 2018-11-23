package client.handler.impl;

import client.handler.Handler;
import com.alibaba.fastjson.JSON;
import common.ReturnMessage;
import javafx.application.Platform;
import javafx.scene.control.Alert;

public class GroupHdl implements Handler {
    @Override
    public Object handle(Object obj) {
        if(obj != null) {
            try{
                ReturnMessage rm = JSON.parseObject(obj.toString(), ReturnMessage.class);
                if(rm.isSuccess()) {
                    //group create success
                    Platform.runLater(()-> {
                        showSignupWindow("INFORMATION",rm.getContent().toString());
                    });
                } else {
                    //group create fail
                    Platform.runLater(()-> {
                        showSignupWindow("INFORMATION",rm.getMessage());
                    });
                }

            } catch (Exception e){
                e.printStackTrace();
            }
        }
        return null;
    }

    public void showSignupWindow(String title, String msg) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setContentText(msg);
        alert.show();
    }
}
