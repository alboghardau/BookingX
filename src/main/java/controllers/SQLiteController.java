package controllers;

import DatabaseOperators.BookingDAO;
import DatabaseOperators.GuestDAO;
import DatabaseOperators.RoomsDAO;
import models.Booking;
import models.Guest;
import models.Room;

import java.sql.*;
import java.util.List;

public class SQLiteController {

    private Connection connection;
    private RoomsDAO roomsDAO;
    private GuestDAO guestDAO;
    private BookingDAO bookingDAO;

    private SQLiteController(){
        try{
            Class.forName("org.sqlite.JDBC");
            this.connection = DriverManager.getConnection("jdbc:sqlite:src/main/resources/Bookings.db");
            System.out.println("Conencted to database!");

            roomsDAO = new RoomsDAO(connection);
            guestDAO = new GuestDAO(connection);
            bookingDAO = new BookingDAO(connection);
        }catch(Exception e){
            e.printStackTrace();
            System.out.println("Failed to connect to database!");
        }
    }

    private static class SingletonHelper{
        private static final SQLiteController INSTANCE = new SQLiteController();
    }

    public static SQLiteController getInstance(){
        return SingletonHelper.INSTANCE;
    }

    //ROOM DAO HERE
    //retrieves all rooms from the database
    public List<Room> listRooms(){
        return roomsDAO.listRooms();
    }

    //add new room to table
    public void addRoom(Room room) {
        roomsDAO.addRoom(room);
    }

    //editroom
    public void editRoom(Room newRoom){
        roomsDAO.editRoom(newRoom);
    }

    //delete room
    public void deleteRoom(Room room){
        roomsDAO.deleteRoom(room);
    }

    //get on room
    public Room oneRoom(int id){
        return roomsDAO.oneRoom(id);
    }

    //GUEST DAO HERE
    //list all guests
    public List<Guest> listGuests(){
        return guestDAO.listAll();
    }

    //add guest
    public void addGuest(Guest guest){
        guestDAO.addGuest(guest);
    }

    //delete guest
    public void deleteGuest(Guest guest){
        guestDAO.deleteGuest(guest);
    }

    //BOOKING DAO HERE
    //list all booking records
    public List<Booking> listBookingRecords(){
        return bookingDAO.listBookings(roomsDAO.listRooms(), guestDAO.listAll());
    }

    //list bookings in a month
    public List<Booking> listBookingMonth(){
        return bookingDAO.monthlyBooking(roomsDAO.listRooms(),guestDAO.listAll(), BookingController.getInstance().getAppDate());
    }

    //adds new booking and guest into database
    public void addBooking(Booking booking, Guest guest, Room room){
        addGuest(guest);
        Guest newGuest = guest;
        newGuest.setId(guestDAO.lastGuestId());
        bookingDAO.addBooking(booking, newGuest, room);
    }
}
