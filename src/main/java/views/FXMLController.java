package views;

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
    private DatePicker dateSelector;
    
    @FXML
    public void initialize(URL url, ResourceBundle rb) {
        onButtonPlannerClick();
        dateSelectorInitialize();
    }

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

    private void dateSelectorInitialize(){
        dateSelector.setValue(TimeController.getInstance().getAppDate());
        dateSelector.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                TimeController.getInstance().setAppDate(dateSelector.getValue());
            }
        });
    }

    @FXML
    public void onButtonPlannerClick(){
        setInnerPane("../Planner.fxml");
    }

    @FXML
    public void onBtnRoomEditorClick(){
        setInnerPane("../RoomEditor.fxml");
    }

    @FXML
    public void onButtonExitClick(){
        System.exit(0);
    }
}
