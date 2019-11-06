package FXMLControllers;

import controllers.SQLiteController;
import controllers.BookingController;
import helpers.WindowViewHelper;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import models.Booking;
import models.Guest;

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
    private Button buttonAdd;
    @FXML
    private Button buttonEdit;
    @FXML
    private Button buttonCancel;

    @FXML
    public void initialize(URL url, ResourceBundle rb){
        dateCheckInPicker.setValue(BookingController.getInstance().getCheckInSelector());
        WindowViewHelper.disableDatePicker(dateCheckInPicker);
        dateCheckOutPicker.setValue(BookingController.getInstance().getCheckOutSelector());
        WindowViewHelper.disableDatePicker(dateCheckOutPicker);
        textFieldRoom.setText(BookingController.getInstance().getSelectedRoom().getName());
        textFieldRoom.setEditable(false);
    }

    //SWITCHES DISPLAY FOR THE MODAL ACTION
    private void buttonsDisplay(String action){
        switch (action){
            case "add":
                WindowViewHelper.setVisibility(buttonAdd,true);
                break;
            case "edit":
                WindowViewHelper.setVisibility(buttonEdit,true);
                break;
        }
    }

    //BUTTONS ACTIONS
    @FXML
    public void onButtonAddAction(){
        //breakers for the method, if any is true -> return
        if(textFieldFirstName.equals("")){
            return;
        }
        if(textFieldLastName.equals("")){
            return;
        }
        if(textFieldTelephone.equals("")){
            return;
        }
        if(textFieldPrice.equals("")){
            return;
        }

        Guest guest = new Guest(0,
                textFieldFirstName.getText(),
                textFieldLastName.getText(),
                textFieldTelephone.getText(),
                textFieldEmail.getText()
        );
        Booking booking = new Booking(0,
                guest,
                BookingController.getInstance().getSelectedRoom(),
                Integer.parseInt(textFieldPersons.getText()),
                BookingController.getInstance().getCheckInSelector(),
                BookingController.getInstance().getCheckOutSelector(),
                Double.parseDouble(textFieldPrice.getText()));
        SQLiteController.getInstance().addBooking(booking,guest, BookingController.getInstance().getSelectedRoom());
        buttonAdd.getScene().getWindow().hide();
        BookingController.getInstance().resetDates();
    }

    @FXML
    public void onButtonEditAction(){

    }

    @FXML
    public void onButtonCancelAction(){
        buttonCancel.getScene().getWindow().hide();

    }

}
