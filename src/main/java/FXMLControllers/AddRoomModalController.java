/*
TESTED - OK
*/

package FXMLControllers;

import controllers.SQLiteController;
import helpers.WindowActionHelper;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.effect.GaussianBlur;
import models.Room;
import views.MainApp;

import java.net.URL;
import java.util.ResourceBundle;

public class AddRoomModalController implements Initializable {

    @FXML
    public Button addRoomModalCancel;
    public Button addRoomModalAdd;
    public TextField textRoomName;

    @FXML
    public void initialize(URL url, ResourceBundle rb) {
        MainApp.getParent().setEffect(new GaussianBlur(5));

        addRoomModalCancel.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                WindowActionHelper.closeModal(event);
            }
        });

        addRoomModalAdd.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                if(!textRoomName.getText().equals("")){
                    Room room = new Room(1, textRoomName.getText());
                    SQLiteController.getInstance().addRoom(room);
                    WindowActionHelper.closeModal(event);
                }
            }
        });
    }
}
