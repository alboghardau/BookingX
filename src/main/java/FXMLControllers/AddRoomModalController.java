package FXMLControllers;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.effect.GaussianBlur;
import javafx.stage.Stage;
import views.MainApp;

import java.net.URL;
import java.util.ResourceBundle;

public class AddRoomModalController implements Initializable {

    @FXML
    public Button addRoomModalCancel;
    public Button addRoomModalAdd;

    @FXML
    public void initialize(URL url, ResourceBundle rb) {
        MainApp.getParent().setEffect(new GaussianBlur(5));

        addRoomModalCancel.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                Node source = (Node) event.getSource();
                Stage stage = (Stage) source.getScene().getWindow();
                stage.close();
                MainApp.getParent().setEffect(null);
            }
        });
    }

}
