package views;

import java.net.URL;
import java.util.ResourceBundle;

import controllers.SQLiteController;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;

public class FXMLController implements Initializable {
    
    @FXML
    private Label labelSelectDate;
    @FXML
    private BorderPane paneMainView;

    @FXML
    private Button buttonRoomEditor;
    
    @FXML
    public void initialize(URL url, ResourceBundle rb) {
        String javaVersion = System.getProperty("java.version");
        String javafxVersion = System.getProperty("javafx.version");


    }

    @FXML
    public void onBtnRoomEditorClick(){
        RoomManager roomManager = new RoomManager();
        paneMainView.setCenter(roomManager);
    }
}
