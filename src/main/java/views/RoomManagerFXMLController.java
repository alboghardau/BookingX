package views;

import controllers.SQLiteController;
import helpers.WindowMove;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import javafx.scene.effect.GaussianBlur;
import javafx.scene.layout.BorderPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.stage.Window;


import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class RoomManagerFXMLController implements Initializable {

    @FXML
    private Button buttonAddRoom;

    @FXML
    public void initialize(URL url, ResourceBundle rb) {

        SQLiteController.getInstance().listRooms();
    }

    @FXML
    public void onButtonAddRoomClick(){
        System.out.println("Test");
        try {
            Stage dialog = new Stage();
            Parent root = FXMLLoader.load(getClass().getResource("../AddRoomModal.fxml"));
            Window parentWindow = Stage.getWindows().stream().filter(Window::isShowing).findFirst().orElse(null);
            dialog.initStyle(StageStyle.UNDECORATED);
            dialog.setScene(new Scene(root));
            dialog.initOwner(parentWindow);
            dialog.initModality(Modality.APPLICATION_MODAL);
            dialog.show();
            WindowMove.setMovableWindow(root,dialog);

        } catch (IOException e) {
            e.printStackTrace();
        }



    }
}
