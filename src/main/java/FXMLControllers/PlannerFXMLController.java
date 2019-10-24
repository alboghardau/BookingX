package FXMLControllers;

import controllers.SQLiteController;
import controllers.TimeController;
import helpers.WindowEditorHelper;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import models.Booking;
import models.Room;
import views.MainApp;


import java.net.URL;
import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

public class PlannerFXMLController implements Initializable {

    @FXML
    private VBox paneMain;
    public DatePicker dateSelector;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        dateSelectorInitialize();
    }

    private void refreshList(){
        paneMain.getChildren().clear();

        int daysThisMonth = TimeController.getInstance().getAppDate().lengthOfMonth();
        List<Room> roomsList = SQLiteController.getInstance().listRooms();
        List<Booking> bookingList = SQLiteController.getInstance().listBookingMonth();

        roomsList.stream().forEach(element ->{
            List<Booking> bookedPerRoom = bookingList.stream().filter( b -> b.getRoom().getId() == element.getId()).collect(Collectors.toList());
            Map<Integer, Booking> mapDaysBookedPerRoom = bookedDays(bookedPerRoom,daysThisMonth);

            HBox h = WindowEditorHelper.createHBox(0);
            paneMain.getChildren().add(h);
            Label label = WindowEditorHelper.createLabel(element.getName());
            h.getChildren().add(label);

            for(int i = 1; i <= daysThisMonth; i++){
                final int day = i;
                Button buttonDay = null;
                if(mapDaysBookedPerRoom.containsKey(day)){
                    buttonDay = WindowEditorHelper.createButtonBooked(i+"");
                }else if(dateSelectorCheck(i) && element.getId() == TimeController.getInstance().getSelectedRoom().getId()){
                    buttonDay = WindowEditorHelper.createButtonCecked(i+"");
                }else {
                    buttonDay = WindowEditorHelper.createButtonDay(i+"");
                }

                h.getChildren().add(buttonDay);

                buttonDay.setOnAction(new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent event) {
                        TimeController.getInstance().setDate(LocalDate.of(TimeController.getInstance().getAppDate().getYear(),TimeController.getInstance().getAppDate().getMonth(),day),element);
                        System.out.println(TimeController.getInstance().toString());
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
            LocalDate testDay = LocalDate.of(TimeController.getInstance().getAppDate().getYear(),TimeController.getInstance().getAppDate().getMonth(),i);
            for (Booking booking : bookedPerRoom) {
                if ((testDay.isAfter(booking.getCheckIn()) || testDay.isEqual(booking.getCheckIn())) && (testDay.isBefore(booking.getCheckOut()) || testDay.isEqual(booking.getCheckOut()))) {
                    map.put(i, booking);
                }
            }
        }
        return map;
    }

    private boolean dateSelectorCheck(int day){
        LocalDate dayDate = LocalDate.of(TimeController.getInstance().getAppDate().getYear(),TimeController.getInstance().getAppDate().getMonth(),day);
        LocalDate dateA = TimeController.getInstance().getCheckInSelector();
        LocalDate dateB = TimeController.getInstance().getCheckOutSelector();
        if(dateA != null && dateB != null){
            if ((dayDate.isAfter(dateA) || dayDate.isEqual(dateA)) && (dayDate.isBefore(dateB) || dayDate.isEqual(dateB))){
                return true;
            }
        }
        return false;
    }

    private void dateSelectorInitialize(){
        dateSelector.setValue(TimeController.getInstance().getAppDate());
        dateSelector.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                TimeController.getInstance().setAppDate(dateSelector.getValue());
            }
        });
        refreshList();
    }

    @FXML
    public void onButtonNext(){
        TimeController.getInstance().setAppDate(TimeController.getInstance().getAppDate().plusMonths(1));
        dateSelector.setValue(TimeController.getInstance().getAppDate());
        refreshList();
    }

    @FXML void onButtonPrevious(){
        TimeController.getInstance().setAppDate(TimeController.getInstance().getAppDate().minusMonths(1));
        dateSelector.setValue(TimeController.getInstance().getAppDate());
        refreshList();
    }

}
