package FXMLControllers;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ResourceBundle;

public class EditBookingModalController implements Initializable {

    @FXML
    private DatePicker dateCheckInPicker;
    @FXML
    private DatePicker dateCheckOutPicker;
    @FXML
    private TextField textFieldRoom;
    @FXML
    private TextField textFieldFirstName;
    @FXML
    private TextField textFieldLastName;
    @FXML
    private TextField textFieldTelephone;
    @FXML
    private TextField textFieldEmail;
    @FXML
    private TextField textFieldPersons;
    @FXML
    private TextField textFieldPrice;

    @FXML
    public void initialize(URL url, ResourceBundle rb){

    }
}
