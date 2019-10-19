package views;

import java.net.URL;
import java.util.ResourceBundle;

import controllers.SQLiteController;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;

public class FXMLController implements Initializable {
    
    @FXML
    private Label labelSelectDate;
    @FXML
    private BorderPane paneMainView;
    @FXML
    private Button buttonPlanner;
    @FXML
    private Button buttonRoomEditor;
    @FXML
    private Button buttonNextGuests;
    @FXML
    private Button buttonAccounting;
    
    @FXML
    public void initialize(URL url, ResourceBundle rb) {


    }

    @FXML
    public void onButtonPlannerClick(){

    }

    @FXML
    public void onBtnRoomEditorClick(){
        Parent root = null;
        try{
            FXMLLoader loader = new FXMLLoader(getClass().getResource("../RoomEditor.fxml"));
            root = loader.load();
        }catch (Exception e){
            e.printStackTrace();
        }
        paneMainView.setCenter(root);
    }
}
