package views;

import controllers.SQLiteController;
import helpers.WindowEditorHelper;
import helpers.WindowHelper;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.stage.Window;
import models.Room;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class RoomManagerFXMLController implements Initializable {

    @FXML
    public Button buttonAddRoom;
    public VBox roomsVBox;

    private List<Room> list;

    @FXML
    public void initialize(URL url, ResourceBundle rb) {
        list = SQLiteController.getInstance().listRooms();
        refreshRoomList();
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
            WindowHelper.setMovableWindow(root,dialog);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void refreshRoomList(){
        roomsVBox.getChildren().removeAll();
        list.forEach((e) -> {
            HBox box = WindowEditorHelper.createHBox();
            Label roomName = WindowEditorHelper.createLabel(e.getName());
            Button buttonDelete = WindowEditorHelper.createButton("Delete");
            Button buttonEdit = WindowEditorHelper.createButton("Edit");

            roomsVBox.getChildren().add(box);
            box.getChildren().add(roomName);
            box.getChildren().add(buttonDelete);
            box.getChildren().add(buttonEdit);
        });
    }
}

