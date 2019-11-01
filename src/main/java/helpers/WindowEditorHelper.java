//THIS FILE WILL CONTAIN WINDOW EDITING EVENTS (ADD NEW ELEMENTS, GENERATE ELEMENTS, so on)
package helpers;

import FXMLControllers.MainFXMLController;
import FXMLControllers.PlannerFXMLController;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import views.MainApp;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;


public class WindowEditorHelper {

    public static HBox createHBox(int spacing){
        HBox h = new HBox();
        h.setAlignment(Pos.CENTER_LEFT);
        h.setPrefWidth(770);            //temporary fix for center of controlls
        h.setSpacing(spacing);
        return h;
    }

    public static Button createButton(String text){
        Button b = new Button();
        switch (text){
            case "Delete":
                b.getStyleClass().add("buttonInnerDelete");
                Image image = new Image(MainApp.class.getResourceAsStream("/icons/icons8_delete_24px.png"));
                b.setGraphic(new ImageView(image));
                break;
            case "Edit":
                b.getStyleClass().add("buttonInnerEdit");
                Image image2 = new Image(MainApp.class.getResourceAsStream("/icons/icons8_edit_24px.png"));
                b.setGraphic(new ImageView(image2));
                break;
        }
        b.setText(text);
        return b;
    }

    public static Label createLabel(String text){
        Label label = new Label();
        label.getStyleClass().add("labelRoomName");
        label.setText(text);
        label.setMinWidth(100);
        label.setPadding(new Insets(0,0,0,5));
        return label;
    }

    public static Button createButtonDay(String type, String text, Double paneWidth){
        Button button = new Button();
        button.setText(text);
        button.setAlignment(Pos.CENTER);

        //TYPE OF THE BUTTON SWITCH
        switch (type){
            case "day":
                button.getStyleClass().add("buttonDay");
                break;
            case "booked":
                button.getStyleClass().add("buttonBooked");
                break;
            case "checked":
                button.getStyleClass().add("buttonChecked");
                break;
        }

        //DIMENSION PROPERTIES
        button.setMaxWidth(Double.MAX_VALUE);

        return button;
    }


}
