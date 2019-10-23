package FXMLControllers;

import controllers.SQLiteController;
import controllers.TimeController;
import helpers.WindowEditorHelper;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import models.Booking;
import models.Room;


import java.net.URL;
import java.time.LocalDate;
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
        int daysThisMonth = TimeController.getInstance().getAppDate().lengthOfMonth();

        List<Room> roomsList = SQLiteController.getInstance().listRooms();
        List<Booking> bookingList = SQLiteController.getInstance().listBookingMonth();


        roomsList.stream().forEach(element ->{
            HBox h = WindowEditorHelper.createHBox(0);
            paneMain.getChildren().add(h);
            Label label = WindowEditorHelper.createLabel(element.getName());
            h.getChildren().add(label);

            for(int i = 1; i <= daysThisMonth; i++){
                final int day = i;
                Button buttonDay = WindowEditorHelper.createButtonDay(i+"");
                h.getChildren().add(buttonDay);
                buttonDay.setOnAction(new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent event) {
                        TimeController.getInstance().setDate(LocalDate.of(TimeController.getInstance().getAppDate().getYear(),TimeController.getInstance().getAppDate().getMonth(),day),element);
                    }
                });
            }
        });
    }
}
