package FXMLControllers;

import java.net.URL;
import java.util.ResourceBundle;

import controllers.SQLiteController;
import controllers.TimeController;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;

public class MainFXMLController implements Initializable {
    
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
        onButtonPlannerClick();

    }

    //MAIN PANE SELECTORS
    @FXML
    public void onButtonPlannerClick(){
        setInnerPane("../Planner.fxml");
    }

    @FXML
    public void onBtnRoomEditorClick(){
        setInnerPane("../RoomEditor.fxml");
    }

    //OTHER METHODS FOR THIS FXML CONTROLLER
    //function to set the inner pane without copying the code
    private void setInnerPane(String url){
        Parent root = null;
        try{
            FXMLLoader loader = new FXMLLoader(getClass().getResource(url));
            root = loader.load();
        }catch (Exception e){
            e.printStackTrace();
        }
        paneMainView.setCenter(root);
    }

}
