package client.view.main;

import client.ClientHolder;
import client.callback.ChatRoomAction;
import client.callback.DefaultCallback;
import client.handler.impl.EnterGroupHdl;
import client.view.dialog.entergroup.EnterGroupController;
import client.view.dialog.newgroup.NewGroupController;
import com.sun.tools.javac.comp.Enter;
import common.ChatMessage;
import common.SendHelper;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.scene.text.TextFlow;
import javafx.stage.FileChooser;
import javafx.util.Callback;
import util.ControlledStage;
import util.DateUtils;
import util.StageController;
import util.StringUtil;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;

public class MainPageController implements ControlledStage, Initializable {
    private static MainPageController instance;
    private StageController stageController;
    private ObservableList chatRecord;
    private static String thisUser = ClientHolder.getClient().getFrom();
    @FXML
    private TextField messageTextField;
    @FXML
    private BorderPane mainBorderPane;
    @FXML
    private ListView msgSession;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        chatRecord = DefaultCallback.getChatRecord();
        msgSession.setItems(chatRecord);

        msgSession.setCellFactory(new Callback<ListView, ListCell>() {
            @Override
            public ListCell call(ListView param) {
                return new ChatCell();
            }
        });
    }

    public MainPageController() {
    }

    public static MainPageController getInstance() {
        if (instance == null) {
            instance = new MainPageController();
        }
        return instance;
    }

    @Override
    public void setStageController(StageController stageController) {
        this.stageController = stageController;
    }

    public void showDialog() {

        Dialog<ButtonType> dialog = new Dialog<>();
        dialog.initOwner(mainBorderPane.getScene().getWindow());

        dialog.setTitle("Add New Group");
        dialog.setHeaderText("Use this dialog to create a new group");
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(getClass().getResource("../dialog/newgroup/newgroup.fxml"));

        try {
            dialog.getDialogPane().setContent(fxmlLoader.load());

        } catch (IOException e) {
            System.out.println("Couldn't load the dialog!");
            e.printStackTrace();
            return;
        }
        dialog.getDialogPane().getButtonTypes().add(ButtonType.OK);
        dialog.getDialogPane().getButtonTypes().add(ButtonType.CANCEL);
        Optional<ButtonType> result = dialog.showAndWait();
        if (result.isPresent() && result.get() == ButtonType.OK) {
            NewGroupController newGroupController = fxmlLoader.getController();
            newGroupController.handleNewGroup();
        }

    }


    @FXML
    public void showEnterDialog() {
        System.out.println("enter group");
        Dialog<ButtonType> dialog = new Dialog<>();
        dialog.initOwner(mainBorderPane.getScene().getWindow());

        dialog.setTitle("Add Enter Group");
        dialog.setHeaderText("Use this dialog to enter a new group");
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(getClass().getResource("../dialog/entergroup/entergroup.fxml"));

        try {
            dialog.getDialogPane().setContent(fxmlLoader.load());

        } catch (IOException e) {
            System.out.println("Couldn't load the dialog!!");
            e.printStackTrace();
            return;
        }
        dialog.getDialogPane().getButtonTypes().add(ButtonType.OK);
        dialog.getDialogPane().getButtonTypes().add(ButtonType.CANCEL);
        Optional<ButtonType> result = dialog.showAndWait();
        if (result.isPresent() && result.get() == ButtonType.OK) {
            EnterGroupController enterGroupController = fxmlLoader.getController();
            enterGroupController.handleEnterGroup();

        }
    }

    @FXML
    public void showLeaveDialog() {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirmation Dialog");
        alert.setHeaderText("Are you sure to leave this group?");

        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.OK) {
            // ... user chose OK
        } else {
            // ... user chose CANCEL or closed the dialog
        }
    }

    @FXML
    public void sendMsg() {
        String msg = messageTextField.getText().trim();
        messageTextField.clear();
        if (EnterGroupHdl.getCurrentRoom() != null) {
            ChatMessage cm = new ChatMessage();
            cm.setRoomName(EnterGroupHdl.getCurrentRoom());
            cm.setContent(msg);
            cm.setFrom(ClientHolder.getClient().getFrom());
            cm.setTimer(DateUtils.getFormatDate());
            SendHelper.send(ClientHolder.getClient().getSocket(), cm);

        } else {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Warning");
            alert.setHeaderText("ROOM DOESN'T EXIST");
            alert.setContentText("NOT ALLOW TO SEND MESSAGE");

            Optional<ButtonType> result = alert.showAndWait();
            if (result.get() == ButtonType.OK) {
                // ... user chose OK
            } else {
                // ... user chose CANCEL or closed the dialog
            }
        }
    }


    public static class ChatCell extends ListCell<ChatMessage> {
        @Override
        protected void updateItem(ChatMessage item, boolean empty) {
            super.updateItem(item, empty);
            Platform.runLater(() -> {
                if (item != null) {
                    VBox box = new VBox();
                    HBox hBox = new HBox();
                    TextFlow txtContent = new TextFlow(new Text(item.getContent()));
                    Label labUser = new Label(item.getOwner() + "[" + item.getTimer() + "]");
                    labUser.setStyle("-fx-background-color: #7bc5cd; -fx-text-fill: white");
                    hBox.getChildren().addAll(labUser);
                    if (item.getFrom().equals(thisUser)) {
                        txtContent.setTextAlignment(TextAlignment.RIGHT);
                        hBox.setAlignment(Pos.CENTER_RIGHT);
                        box.setAlignment(Pos.CENTER_RIGHT);
                    }
                    box.getChildren().addAll(hBox, txtContent);
                    setGraphic(box);

                } else {
                    setGraphic(null);
                }
            });
        }
    }

    @FXML
    public void handleUpload() {
        System.out.println("Open upload!!");
        FileChooser chooser = new FileChooser();
        chooser.setTitle("Save Application File");
        chooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("Zip", "*.zip"),
                new FileChooser.ExtensionFilter("PDF", "*.pdf"),
                new FileChooser.ExtensionFilter("Image Files", "*.jpg", "*.png", "*.gif"),
                new FileChooser.ExtensionFilter("All Files", "*.*")
        );
        File file = chooser.showOpenDialog(mainBorderPane.getScene().getWindow());

        String room = EnterGroupHdl.getCurrentRoom();
        if (StringUtil.isNotEmpty(room)) {
            ChatRoomAction.upload(room, file);
        } else {

            Platform.runLater(() -> {
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setTitle("Warning");
                alert.setHeaderText("ROOM DOESN'T EXIST");
                alert.setContentText("NOT ALLOW TO UPLOAD FILE");
                alert.showAndWait();
            });
        }
    }
}