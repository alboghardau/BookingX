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

    public void setCheckInSelector(LocalDate checkInSelector, Room room) {
        this.checkInSelector = checkInSelector;
        this.selectedRoom = room;
        this.checkOutSelector = null;
    }

    public LocalDate getCheckOutSelector() {
        return checkOutSelector;
    }

    public void setCheckOutSelector(LocalDate checkOutSelector, Room room) {
        if(selectedRoom.getId() == room.getId() && checkOutSelector.isAfter(checkInSelector)){
            this.checkOutSelector = checkOutSelector;
        }else{

        }
    }
}
