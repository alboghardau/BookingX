//THIS CLASS WILL HOLD DISPLAY HELPERS
package helpers;

import javafx.scene.Node;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.effect.Glow;
import javafx.scene.paint.Color;

public class WindowViewHelper {

    public static void disableDatePicker(DatePicker picker){
        picker.setDisable(true);
        picker.setStyle("-fx-opacity: 1;");
    }

    public static void setVisibility(Node node, boolean state){
        if(state){
            node.setVisible(true);
            node.setManaged(true);
        }else {
            node.setVisible(false);
            node.setManaged(false);
        }
    }

    //name label red blink
    public static void blinkBlink(Node node) {
            Thread thread = new Thread(() -> {
                node.setEffect(new Glow(5));
                try {
                    Thread.sleep(250);
                    node.setEffect(new Glow(0));
                    Thread.sleep(250);
                    node.setEffect(new Glow(5));
                    Thread.sleep(250);
                    node.setEffect(new Glow(0));
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            });
            thread.start();
    }
}
