package client.handler.impl;

import client.handler.Handler;
import client.view.main.MainPageController;
import com.alibaba.fastjson.JSON;
import common.ReturnMessage;
import common.dto.GroupClientListDTO;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.HashSet;
import java.util.Set;

public class ListUserHdl implements Handler {
    @Override
    public Object handle(Object obj) {
        if (obj != null) {
            try {
                ReturnMessage rm = JSON.parseObject(obj.toString(), ReturnMessage.class);
                if (rm.isSuccess() && rm.getContent() != null) {
                    GroupClientListDTO groupClientListDTO = JSON.parseObject(rm.getContent().toString(), GroupClientListDTO.class);
                    putElementIntoCollections(groupClientListDTO.getListGroup(),MainPageController.listGroup);
                    putElementIntoCollections(groupClientListDTO.getListUser(),MainPageController.listUser);
                }
            } catch (Exception e) {
                System.out.println("Handle list user failed " + e.getMessage());
            }
        }
        return null;
    }

    public void putElementIntoCollections(Set<String> set, ObservableList<String> target) {
        Platform.runLater(() -> {
            target.clear();
            for (String s : set) {
                target.add(s);
            }
        });
    }
}
