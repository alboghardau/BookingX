package FXMLControllers;

import controllers.SQLiteController;
import controllers.BookingController;
import helpers.WindowActionHelper;
import helpers.WindowEditorHelper;
import helpers.WindowViewHelper;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.stage.*;
import models.Booking;
import models.Room;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

public class PlannerFXMLController implements Initializable {

    @FXML
    private VBox paneMain;
    @FXML
    private DatePicker dateSelector;
    @FXML
    private Button buttonAddBooking;
    @FXML
    private ScrollPane scrollPane;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        paneMain.prefWidthProperty().bind(scrollPane.widthProperty());
        dateSelectorInitialize();
    }

    private void refreshList(){
        //TOP BUTTONS AREA
        if(BookingController.getInstance().getCheckInSelector() == null){
            WindowViewHelper.setVisibility(buttonAddBooking, false);
        }else{
            WindowViewHelper.setVisibility(buttonAddBooking, true);
        }

        //ROOM LIST AREA
        paneMain.getChildren().clear();

        int daysThisMonth = BookingController.getInstance().getAppDate().lengthOfMonth();
        List<Room> roomsList = SQLiteController.getInstance().listRooms();
        List<Booking> bookingList = SQLiteController.getInstance().listBookingMonth();

        roomsList.stream().forEach(element ->{
            List<Booking> bookedPerRoom = bookingList.stream().filter( b -> b.getRoom().getId() == element.getId()).collect(Collectors.toList());
            Map<Integer, Booking> mapDaysBookedPerRoom = bookedDays(bookedPerRoom,daysThisMonth);

            HBox h = WindowEditorHelper.createHBox(1);
            paneMain.getChildren().add(h);
            Label label = WindowEditorHelper.createLabel(element.getName());

            h.setHgrow(label,Priority.ALWAYS);
            h.getChildren().add(label);

            for(int i = 1; i <= daysThisMonth; i++){
                final int day = i;
                Button buttonDay = null;

                if(mapDaysBookedPerRoom.containsKey(day)){
                    buttonDay = WindowEditorHelper.createButtonDay("booked",i+"", paneMain.getWidth());
                }else if(dateSelectorCheck(i) && element.getId() == BookingController.getInstance().getSelectedRoom().getId()){
                    buttonDay = WindowEditorHelper.createButtonDay("checked",i+"", paneMain.getWidth());
                }else {
                    buttonDay = WindowEditorHelper.createButtonDay("day",i+"", paneMain.getWidth());
                }
                h.setHgrow(buttonDay,Priority.ALWAYS);
                h.getChildren().add(buttonDay);

                buttonDay.setOnAction(new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent event) {
                        BookingController.getInstance().setDate(LocalDate.of(BookingController.getInstance().getAppDate().getYear(), BookingController.getInstance().getAppDate().getMonth(),day),element);
                        refreshList();
                    }
                });
            }
        });
    }

    //returns a map of ( day number, booking record )
    private Map<Integer,Booking> bookedDays(List<Booking> bookedPerRoom, int daysOfMonth) {
        Map<Integer, Booking> map = new TreeMap<Integer, Booking>();
        for(int i = 1; i <= daysOfMonth; i++) {
            LocalDate testDay = LocalDate.of(BookingController.getInstance().getAppDate().getYear(), BookingController.getInstance().getAppDate().getMonth(),i);
            for (Booking booking : bookedPerRoom) {
                if ((testDay.isAfter(booking.getCheckIn()) || testDay.isEqual(booking.getCheckIn())) && (testDay.isBefore(booking.getCheckOut().minusDays(1)) || testDay.isEqual(booking.getCheckOut().minusDays(1)))) {
                    map.put(i, booking);
                }
            }
        }
        return map;
    }

    private boolean dateSelectorCheck(int day){
        LocalDate dayDate = LocalDate.of(BookingController.getInstance().getAppDate().getYear(), BookingController.getInstance().getAppDate().getMonth(),day);
        LocalDate dateA = BookingController.getInstance().getCheckInSelector();
        LocalDate dateB = BookingController.getInstance().getCheckOutSelector();
        if(dateA != null && dateB != null){
            if ((dayDate.isAfter(dateA) || dayDate.isEqual(dateA)) && (dayDate.isBefore(dateB.minusDays(1)) || dayDate.isEqual(dateB.minusDays(1)))){
                return true;
            }
        }
        return false;
    }

    private void dateSelectorInitialize(){
        dateSelector.setValue(BookingController.getInstance().getAppDate());
        dateSelector.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                BookingController.getInstance().setAppDate(dateSelector.getValue());
            }
        });
        refreshList();
    }

    //BUTTONS ACTIONS
    @FXML
    public void onButtonNext(){
        BookingController.getInstance().setAppDate(BookingController.getInstance().getAppDate().plusMonths(1));
        dateSelector.setValue(BookingController.getInstance().getAppDate());
        refreshList();
    }

    @FXML
    public void onButtonPrevious(){
        BookingController.getInstance().setAppDate(BookingController.getInstance().getAppDate().minusMonths(1));
        dateSelector.setValue(BookingController.getInstance().getAppDate());
        refreshList();
    }

    //DISPLAY ADD/EDIT ROOM MODAL
    @FXML
    public void onButtonAddBookingAction(){
        try{
            Stage addBooking = new Stage();
            Parent root = FXMLLoader.load(getClass().getResource("../EditBookingModal.fxml"));
            Window parentWindow = Stage.getWindows().stream().filter(Window::isShowing).findFirst().orElse(null);
            addBooking.initStyle(StageStyle.UNDECORATED);
            addBooking.setScene(new Scene(root));
            addBooking.initOwner(parentWindow);
            addBooking.initModality(Modality.APPLICATION_MODAL);
            WindowActionHelper.setMovableWindow(root,addBooking);
            addBooking.showAndWait();
            refreshList();      //USED TO REFRESH WINDOW AFTER showAndWait();
        }catch (IOException e){
            e.printStackTrace();
        }
    }
}
