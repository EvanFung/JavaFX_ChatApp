package client.view.dialog.entergroup;

import client.ClientHolder;
import common.EnterGroupMessage;
import common.MessageType;
import common.SendHelper;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import util.StringUtil;

public class EnterGroupController {
    @FXML
    private TextField groupNameField;

    public void handleEnterGroup() {
        String groupName = groupNameField.getText().trim();
        if(StringUtil.isEmpty(groupName)) {
            return;
        }

        EnterGroupMessage message = new EnterGroupMessage();
        message.setGroupName(groupName);
        message.setFrom(ClientHolder.getClient().getFrom());
        SendHelper.send(ClientHolder.getClient().getSocket(),message);

    }
}
