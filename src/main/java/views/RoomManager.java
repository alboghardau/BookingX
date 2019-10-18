package views;

import javafx.fxml.FXMLLoader;
import javafx.scene.layout.BorderPane;

import javax.imageio.IIOException;
import java.io.IOException;

public class RoomManager extends BorderPane {

    public RoomManager(){

        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("../RoomEditor.fxml"));

        //fxmlLoader.setRoot(this);
        fxmlLoader.setController(this);

        try{
            fxmlLoader.load();
        }catch (IOException e){
            e.printStackTrace();
        }
    }
}
