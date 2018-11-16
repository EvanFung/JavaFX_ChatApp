package client.view.login;

import client.ClientHolder;
import client.view.dialog.signup.SignupController;
import common.LoginMessage;
import common.SendHelper;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import util.ControlledStage;
import util.StageController;
import util.StringUtil;

import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

public class LoginController implements ControlledStage, Initializable {
    private StageController stageController;
    @FXML
    AnchorPane loginPane;
    @FXML
    TextField usernameField;
    @FXML
    PasswordField passwordField;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
    }

    @Override
    public void setStageController(StageController stageController) {
        this.stageController = stageController;
    }

    @FXML
    public void showSignupDialog() {
        Dialog<ButtonType> dialog = new Dialog<>();
        dialog.initOwner(loginPane.getScene().getWindow());

        dialog.setTitle("Add New User");
        dialog.setHeaderText("Use this dialog to create a new user");
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(getClass().getResource("../dialog/signup/signup.fxml"));
        try {
            dialog.getDialogPane().setContent(fxmlLoader.load());

        } catch (IOException e) {
            System.out.println("Couldn't load the dialog");
            e.printStackTrace();
            return;
        }

        dialog.getDialogPane().getButtonTypes().add(ButtonType.OK);
        dialog.getDialogPane().getButtonTypes().add(ButtonType.CANCEL);
        Optional<ButtonType> result = dialog.showAndWait();
        if (result.isPresent() && result.get() == ButtonType.OK) {
            SignupController controller = fxmlLoader.getController();
            controller.handleRegister();
        }

    }

    @FXML
    public void handleLogin() {
        String username = usernameField.getText();
        String password = passwordField.getText();
        if (StringUtil.isEmpty(username) || StringUtil.isEmpty(password)) {
            return;
        }
        LoginMessage message = new LoginMessage()
                .setUsername(username)
                .setPassword(password);
        message.setFrom(username);
        SendHelper.send(ClientHolder.getClient().getSocket(), message);
    }
}
