package client.view.dialog.newgroup;

import client.Client;
import client.ClientHolder;
import common.GroupMessage;
import common.SendHelper;
import javafx.fxml.FXML;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import util.StringUtil;


public class NewGroupController {
    @FXML
    private TextField groupNameField;
    @FXML
    private PasswordField passwordField;

    public void handleNewGroup() {
        String groupName = groupNameField.getText();
        String password = passwordField.getText();
        if(StringUtil.isEmpty(groupName) && StringUtil.isNotEmpty(password)) {
            return;
        }
        GroupMessage message = new GroupMessage();
        message.setGroupName(groupName);
        message.setCreator(ClientHolder.client.getFrom());
        message.setFrom(ClientHolder.client.getFrom());
        message.setPassword(password);
        SendHelper.send(ClientHolder.getClient().getSocket(),message);
    }

}
