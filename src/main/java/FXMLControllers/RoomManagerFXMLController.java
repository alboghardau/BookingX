package FXMLControllers;

import controllers.SQLiteController;
import helpers.WindowEditorHelper;
import helpers.WindowActionHelper;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.stage.Window;
import models.Room;

import javax.swing.*;
import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class RoomManagerFXMLController implements Initializable {

    private Room editRoom;

    @FXML
    private Button buttonAddRoom;
    @FXML
    private VBox roomsVBox;
    @FXML
    private HBox hboxAdd;
    @FXML
    private TextField textRoomName;
    @FXML
    private Label labelRoomName;
    @FXML
    private Button buttonEditRoom;
    @FXML
    private Button buttonCancelEdit;


    private List<Room> list;

    @FXML
    public void initialize(URL url, ResourceBundle rb) {
        list = SQLiteController.getInstance().listRooms();

        refreshRoomList();
        displayAddRoom();
    }

    //STATIC BUTTON CLICKS
    @FXML
    public void onButtonAddRoomClick(){
        if(!textRoomName.getText().equals("")){
            Room r = new Room(1,textRoomName.getText());
            SQLiteController.getInstance().addRoom(r);
            textRoomName.setText("");
            refreshRoomList();
        }else{
            blinkBlink();
        }
    }

    @FXML
    public void onButtonCancelEditAction(){
        displayAddRoom();
    }

    //TOP BAR CONTROLS
    private void displayEditRoom(Room room){
        textRoomName.setText(room.getName());
        buttonEditRoom.setVisible(true);
        buttonAddRoom.setVisible(false);
        buttonAddRoom.setManaged(false);
        buttonCancelEdit.setVisible(true);
        buttonEditRoom.setOnAction(event -> {
            if(!textRoomName.equals("") && !textRoomName.getText().equals(room.getName())){
                Room newRoom = new Room(room.getId(), textRoomName.getText());
                SQLiteController.getInstance().editRoom(newRoom);
                refreshRoomList();
            }else{
                blinkBlink();
            }
        });
    }

    private void displayAddRoom(){
        textRoomName.setText("");
        buttonAddRoom.setVisible(true);
        buttonEditRoom.setVisible(false);
        buttonCancelEdit.setVisible(false);
    }

    //OHTER UI METHODS
    public void refreshRoomList(){
        roomsVBox.getChildren().clear();
        list = SQLiteController.getInstance().listRooms();
        list.forEach((e) -> {
            HBox box = WindowEditorHelper.createHBox(5);
            Label roomName = WindowEditorHelper.createLabel(e.getName());
            Button buttonDelete = WindowEditorHelper.createButton("Delete");
            buttonDelete.setOnAction(event -> {
                SQLiteController.getInstance().deleteRoom(e);
                refreshRoomList();
            });
            Button buttonEdit = WindowEditorHelper.createButton("Edit");
            buttonEdit.setOnAction(event -> {
                displayEditRoom(e);
            });

            roomsVBox.getChildren().add(box);
            box.getChildren().add(roomName);
            box.getChildren().add(buttonDelete);
            box.getChildren().add(buttonEdit);
        });
    }

    //name label red blink
    private void blinkBlink() {
        Thread thread = new Thread(() -> {
            labelRoomName.setTextFill(new Color(1,0,0,1));
            try {
                Thread.sleep(250);
                labelRoomName.setTextFill(new Color(1,1,1,1));
                Thread.sleep(250);
                labelRoomName.setTextFill(new Color(1,0,0,1));
                Thread.sleep(250);
                labelRoomName.setTextFill(new Color(1,1,1,1));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        thread.start();
    }
}

