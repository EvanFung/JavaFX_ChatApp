package util;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.util.HashMap;

public class StageController {
    //create a map for storing stage
    private HashMap<String, Stage> stages = new HashMap<String, Stage>();


    public void addStage(String name, Stage stage) {
        stages.put(name, stage);
    }

    /**
     * Get Stage object through Stage's name
     *
     * @param name name of the stage
     * @return the corresponding stage object
     */
    public Stage getStage(String name) {
        return stages.get(name);
    }


    /**
     * It will save the primary stage object.
     *
     * @param primaryStageName set the name of primary stage
     * @param primaryStage     primary stage object
     */
    public void setPrimaryStage(String primaryStageName, Stage primaryStage) {
        this.addStage(primaryStageName, primaryStage);
    }


    /**
     * load window by address, fxml resource file is needed.
     *
     * @param name      name of the registered fxml windows
     * @param resources resource address
     * @param styles    styling parameters
     * @return 是否加载成功
     */
    public boolean loadStage(String name, String resources, StageStyle... styles) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource(resources));
            Pane tempPane = (Pane) loader.load();

            ControlledStage controlledStage = (ControlledStage) loader.getController();
            controlledStage.setStageController(this);

            Scene tempScene = new Scene(tempPane);
            Stage tempStage = new Stage();
            tempStage.setScene(tempScene);

            for (StageStyle style : styles) {
                tempStage.initStyle(style);
            }

            this.addStage(name, tempStage);

            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * display stage, but not hiding any stage
     *
     * @param name the name of the display window
     * @return result of display window
     */
    public boolean setStage(String name) {
        this.getStage(name).show();
        return true;
    }


    /**
     * display stage and hiding corresponding windows
     *
     * @param show  name of the window that need to display
     * @param close name of the window that need to close
     * @return
     */
    public boolean setStage(String show, String close) {
        getStage(close).close();
        setStage(show);
        return true;
    }


    /**
     * remove the loading stage object in the map
     *
     * @param name file name of the window of the fxml that need to be removed
     * @return result of removing the window
     */
    public boolean unloadStage(String name) {
        if (stages.remove(name) == null) {
            System.out.println("window not exist,please check");
            return false;
        } else {
            System.out.println("window remove successful");
            return true;
        }
    }
}
