package FXMLControllers;

import controllers.SQLiteController;
import controllers.TimeController;
import helpers.WindowEditorHelper;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import models.Room;


import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class PlannerFXMLController implements Initializable {

    @FXML
    private VBox paneMain;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        refreshList();
    }

    private void refreshList(){
        List<Room> roomsList = SQLiteController.getInstance().listRooms();

        roomsList.stream().forEach(element ->{
            HBox h = WindowEditorHelper.createHBox(0);
            paneMain.getChildren().add(h);
            Label label = WindowEditorHelper.createLabel(element.getName());
            h.getChildren().add(label);

            int daysThisMonth = TimeController.getInstance().getAppDate().lengthOfMonth();
            for(int i = 1; i <= daysThisMonth; i++){
                Button labelDay = WindowEditorHelper.createLabelDay(i+"");
                h.getChildren().add(labelDay);

            }
        });
    }
}
