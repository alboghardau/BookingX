package views;

import helpers.WindowActionHelper;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class MainApp extends Application {
    private double xOffset = 0;
    private double yOffset = 0;
    private static Stage primaryStage;
    private static Parent parent;

    @Override
    public void start(Stage stage) throws Exception {
        setPrimaryStage(stage);
        Parent root = FXMLLoader.load(getClass().getResource("/scene.fxml"));
        setParent(root);

        Scene scene = new Scene(root);
        scene.getStylesheets().add(getClass().getResource("/styles.css").toExternalForm());
        //stage.initStyle(StageStyle.UNDECORATED);
        stage.setTitle("BookingX");
        stage.sizeToScene();
        //stage.setResizable(false);
        stage.setScene(scene);
        stage.show();
        stage.setMinWidth(stage.getWidth());
        stage.setMinHeight(stage.getHeight());

        WindowActionHelper.setMovableWindow(root, stage);
    }

    private void setParent(Parent parent){
        MainApp.parent = parent;
    }

    private void setPrimaryStage(Stage stage) {
        MainApp.primaryStage = stage;
    }

    public static Stage getPrimaryStage() {
        return MainApp.primaryStage;
    }

    public static Parent getParent(){
        return MainApp.parent;
    }

    public static void main(String[] args) {
        launch(args);
    }

}
