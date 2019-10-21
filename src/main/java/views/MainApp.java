package views;

import helpers.WindowMove;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

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
        stage.initStyle(StageStyle.UNDECORATED);
        Scene scene = new Scene(root);
        scene.getStylesheets().add(getClass().getResource("/styles.css").toExternalForm());

        stage.setTitle("BookingX");
        stage.setResizable(false);
        stage.setScene(scene);
        stage.show();

        WindowMove.setMovableWindow(root, stage);
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
