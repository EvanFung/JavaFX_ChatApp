package client.view.dialog.entergroup;

import client.ClientHolder;
import common.EnterGroupMessage;
import common.MessageType;
import common.SendHelper;
import javafx.fxml.FXML;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import util.StringUtil;

public class EnterGroupController {
    @FXML
    private TextField groupNameField;
    @FXML
    private PasswordField passwordField;

    public void handleEnterGroup() {
        String groupName = groupNameField.getText().trim();
        String password = passwordField.getText().trim();
        if(StringUtil.isEmpty(groupName) && StringUtil.isEmpty(password)) {
            return;
        }

        EnterGroupMessage message = new EnterGroupMessage();
        message.setGroupName(groupName);
        message.setFrom(ClientHolder.getClient().getFrom());
        message.setPassword(password);
        SendHelper.send(ClientHolder.getClient().getSocket(),message);

    }
}
