package client.handler.impl;

import client.ClientHolder;
import client.ClientMain;
import client.handler.Handler;
import com.alibaba.fastjson.JSON;
import common.ReturnMessage;
import javafx.application.Platform;
import javafx.scene.control.Alert;

import static util.ViewUtil.loginViewID;
import static util.ViewUtil.mainViewID;
import static util.ViewUtil.mainViewRes;

public class LoginHdl implements Handler {
    @Override
    public Object handle(Object obj) {
        if (obj != null) {
            try {
                ReturnMessage rm = JSON.parseObject(obj.toString(), ReturnMessage.class);
                if (rm.isSuccess()) {
                    //redirect to main pages.
                    Platform.runLater(()-> {
                        ClientMain.stageController.loadStage(mainViewID,mainViewRes);
                        ClientMain.stageController.setStage(mainViewID,loginViewID);
                    });
                    //kepp alive watch dog started
                    ClientHolder.getClient().keepAlive(rm.getTo());
                } else {
                    //TODO alter box shown.
                    Platform.runLater(() -> {
                        showSignupWindow("Login fail", rm.getMessage());
                    });
                    System.out.println(rm.getMessage());
                }

            } catch (Exception e) {
                System.out.println("Handle login failed" + e.getMessage());
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
