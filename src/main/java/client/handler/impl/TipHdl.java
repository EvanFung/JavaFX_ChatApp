package client.handler.impl;

import client.ClientHolder;
import client.callback.DefaultCallback;
import client.handler.Handler;
import client.view.main.MainPageController;
import com.alibaba.fastjson.JSON;
import common.ChatMessage;
import common.FileMessage;
import common.ReturnMessage;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import util.DateUtils;

import java.util.ArrayList;

public class TipHdl implements Handler {
    @Override
    public Object handle(Object obj) {
        if (obj != null) {
            ReturnMessage rm = JSON.parseObject(obj.toString(), ReturnMessage.class);
            if (rm.isSuccess() && rm.getContent() != null) {
                FileMessage fm = JSON.parseObject(rm.getContent().toString(),FileMessage.class);
                ChatMessage cm = new ChatMessage();
                cm.setFrom(fm.getFrom());
                cm.setRoomName(fm.getTo());
                cm.setContent("[File transmission] - "+ "[name="+ fm.getName() +"]" + "[size="+ fm.getSize() +" bytes]");
                cm.setTimer(DateUtils.getFormatDate());

                Platform.runLater(()-> {
                    DefaultCallback.getChatRecord().add(cm);
                });
            }
        }
        return null;
    }
}
