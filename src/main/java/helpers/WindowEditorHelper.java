package helpers;

import javafx.geometry.Pos;
import javafx.scene.control.Button;
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
        h.setAlignment(Pos.CENTER);
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
        label.getStyleClass().add("labelInnerBlack");
        label.setText(text);
        label.setMinWidth(100);
        return label;
    }

    public static Button createButtonDay(String text){
        Button button = new Button();
        button.getStyleClass().add("labelDay");
        button.setText(text);
        button.setAlignment(Pos.CENTER);
        button.setMinWidth(30);

        //label.setMinHeight(24);
        return button;
    }
}
