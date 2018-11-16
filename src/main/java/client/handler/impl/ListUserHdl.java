package client.handler.impl;

import client.handler.Handler;
import com.alibaba.fastjson.JSON;
import common.ReturnMessage;
import common.dto.GroupClientListDTO;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class ListUserHdl implements Handler {
    public static ObservableList listUser = FXCollections.observableArrayList();
    public static ObservableList listGroup = FXCollections.observableArrayList();


    @Override
    public Object handle(Object obj) {
        if (obj != null) {
            try {
                ReturnMessage rm = JSON.parseObject(obj.toString(), ReturnMessage.class);
                if (rm.isSuccess() && rm.getContent() != null) {
                    GroupClientListDTO groupClientListDTO = JSON.parseObject(rm.getContent().toString(), GroupClientListDTO.class);
                    Platform.runLater(()-> {
                        listUser.addAll(groupClientListDTO.getListUser());
                        listGroup.addAll(groupClientListDTO.getListGroup());
                    });
                }
            } catch (Exception e) {
                System.out.println("Handle list user failed " + e.getMessage());
            }
        }
        return null;
    }


    public static ObservableList getListUser() {
        return listUser;
    }

    public static ObservableList getListGroup() {
        return listGroup;
    }
}
