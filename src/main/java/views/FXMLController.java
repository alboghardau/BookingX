package views;

import java.net.URL;
import java.util.ResourceBundle;

import controllers.SQLiteController;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;

public class FXMLController implements Initializable {
    
    @FXML
    private Label labelSelectDate;
    @FXML
    private AnchorPane paneMainView;
    
    @FXML
    public void initialize(URL url, ResourceBundle rb) {
        String javaVersion = System.getProperty("java.version");
        String javafxVersion = System.getProperty("javafx.version");


    }
}
