package client.view.dialog.signup;

import client.ClientHolder;
import common.RegisterMessage;
import common.SendHelper;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import util.StringUtil;

public class SignupController {
    @FXML
    TextField usernameField;
    @FXML
    PasswordField passwordField;

    public void handleRegister() {
        String username = usernameField.getText();
        String password = passwordField.getText();
        if(StringUtil.isEmpty(username) || StringUtil.isEmpty(password)) {
            return;
        }
        RegisterMessage message = new RegisterMessage()
                .setUsername(username)
                .setPassword(password);
        message.setFrom(username);
        SendHelper.send(ClientHolder.getClient().getSocket(),message);
    }

}
