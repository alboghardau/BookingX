package controllers;

import models.Booking;
import models.Room;

import java.time.LocalDate;
import java.util.logging.Level;
import java.util.logging.Logger;

public class BookingController {

    private LocalDate appDate;
    private LocalDate checkInSelector;
    private LocalDate checkOutSelector;
    private Room selectedRoom;
    private Booking selectedBooking;

    private BookingController(){
        this.appDate = LocalDate.now();
        this.checkInSelector = null;
        this.checkOutSelector = null;
        this.selectedRoom = null;
        this.selectedBooking = null;
    }

    private static class SingletonHelper{
        private static final BookingController INSTANCE = new BookingController();
    }

    public static BookingController getInstance(){
        return SingletonHelper.INSTANCE;
    }

    public LocalDate getAppDate() {
        return appDate;
    }

    public void setAppDate(LocalDate appDate) {
        this.appDate = appDate;
        Logger.getGlobal().log(Level.INFO, "New date set: " + appDate.toString());
    }

    public LocalDate getCheckInSelector() {
        return checkInSelector;
    }

    public void setCheckInSelector(LocalDate checkInSelector) {
        this.checkInSelector = checkInSelector;
    }

    public LocalDate getCheckOutSelector() {
        return checkOutSelector;
    }

    public void setCheckOutSelector(LocalDate checkOutSelector) {
            this.checkOutSelector = checkOutSelector;
    }

    public Room getSelectedRoom() {
        return selectedRoom;
    }

    public void setSelectedRoom(Room selectedRoom) {
        this.selectedRoom = selectedRoom;
    }

    public Booking getSelectedBooking() {
        return selectedBooking;
    }

    public void setSelectedBooking(Booking selectedBooking) {
        this.selectedBooking = selectedBooking;
    }

    //resets dates
    public void resetDates(){
        setCheckInSelector(null);
        setCheckOutSelector(null);
        setSelectedRoom(null);
    }

    //resets selected booking
    public void resetBooking(){
        setSelectedBooking(null);
    }

    public void setDate(LocalDate date, Room room){
        if(checkInSelector == null && selectedRoom == null){
            setCheckInSelector(date);
            setCheckOutSelector(date.plusDays(1));
            setSelectedRoom(room);
        }else if(room.getId() != selectedRoom.getId()){
            setCheckInSelector(date);
            setCheckOutSelector(date.plusDays(1));
            setSelectedRoom(room);
        }else if(date.isAfter(checkInSelector)){
            setCheckOutSelector(date.plusDays(1));
        }else if(date.isBefore(checkInSelector)){
            setCheckInSelector(date);
            setCheckOutSelector(date.plusDays(1));
        }else{
            setCheckInSelector(null);
            setCheckOutSelector(null);
            setSelectedRoom(null);
        }
    }

    @Override
    public String toString() {
        return "TimeController{" +
                "appDate=" + appDate +
                ", checkInSelector=" + checkInSelector +
                ", checkOutSelector=" + checkOutSelector +
                ", selectedRoom=" + selectedRoom +
                '}';
    }
}
