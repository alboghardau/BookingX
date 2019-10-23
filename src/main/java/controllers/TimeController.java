package controllers;

import models.Room;

import java.time.LocalDate;
import java.util.logging.Level;
import java.util.logging.Logger;

public class TimeController {

    private LocalDate appDate;
    private LocalDate checkInSelector;
    private LocalDate checkOutSelector;
    private Room selectedRoom;

    private TimeController(){
        this.appDate = LocalDate.now();
        this.checkInSelector = null;
        this.checkOutSelector = null;
        this.selectedRoom = null;
    }

    private static class SingletonHelper{
        private static final TimeController INSTANCE = new TimeController();
    }

    public static TimeController getInstance(){
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

    public void setDate(LocalDate date, Room room){
        if(checkInSelector == null && room == null){
            setCheckInSelector(date);
            setSelectedRoom(room);
        }else if(checkInSelector!= null && date.isEqual(checkInSelector)){
            setCheckInSelector(null);
            setSelectedRoom(null);
        }else if(checkInSelector != null && date.isAfter(checkInSelector) && room.getId() == selectedRoom.getId()){
            setCheckOutSelector(date);
        }else if(checkInSelector != null && checkOutSelector != null){
            setCheckInSelector(null);
            setCheckOutSelector(null);
            setSelectedRoom(null);
        }
    }
}
