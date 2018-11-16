package client;

import client.callback.DefaultCallback;
import javafx.application.Application;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import util.StageController;
import java.io.IOException;

import static util.ViewUtil.*;

public class ClientMain extends Application {
    public static StageController stageController;

    public static void main(String[] args) {

        try {
            Client client = new Client(new DefaultCallback());
            client.start();
            ClientHolder.setClient(client);
            launch(args);
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println(e.getMessage());
        }

    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        //新建一个StageController控制器
        stageController = new StageController();
        //加载两个舞台，每个界面一个舞台
        stageController.loadStage(loginViewID, loginViewRes, StageStyle.DECORATED);
        //显示MainView舞台
        stageController.setStage(loginViewID);

    }
}
