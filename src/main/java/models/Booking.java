package models;

import java.time.LocalDate;

public class Booking {

    private int id;
    private Guest guest;
    private Room room;
    private int persons;
    private LocalDate checkIn;
    private LocalDate checkOut;
    private double price;

    public Booking(int id, Guest guest, Room room, int persons, LocalDate checkIn, LocalDate checkOut, double price) {
        this.id = id;
        this.guest = guest;
        this.room = room;
        this.persons = persons;
        this.checkIn = checkIn;
        this.checkOut = checkOut;
        this.price = price;
    }

    public void setCheckIn(LocalDate checkIn) {
        this.checkIn = checkIn;
    }

    public void setCheckOut(LocalDate checkOut) {
        this.checkOut = checkOut;
    }

    public LocalDate getCheckIn() {
        return checkIn;
    }

    public LocalDate getCheckOut() {
        return checkOut;
    }

    public Guest getGuest() {
        return guest;
    }

    public void setGuest(Guest guest) {
        this.guest = guest;
    }

    public int getPersons() {
        return persons;
    }

    public void setPersons(int persons) {
        this.persons = persons;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public Room getRoom() {
        return room;
    }

    public void setRoom(Room room) {
        this.room = room;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "Booking{" +
                "guest=" + guest.getId() +
                ", room=" + room.getId() +
                ", persons=" + persons +
                ", checkIn=" + checkIn +
                ", checkOut=" + checkOut +
                ", price=" + price +
                '}';
    }
}
