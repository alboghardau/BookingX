package controllers;

import models.Booking;
import models.Guest;
import models.Room;

import java.awt.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class SQLiteController {

    private Connection connection;

    private SQLiteController(){
        try{
            Class.forName("org.sqlite.JDBC");
            connection = DriverManager.getConnection("jdbc:src/main/resources/sqlite:Bookings.db");
            System.out.println("Conencted to database!");
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

    //retrieves all rooms from the database
    public List<Room> listRooms(){
        List<Room> list= new ArrayList<>();
        try {
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM rooms");
            ResultSet set = statement.executeQuery();
            while (set.next()){
                Room r = new Room(set.getInt("id"),set.getString("name"));
                list.add(r);
            }
            return list;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return Collections.emptyList();
    }

    //adds booking to the database
    public void addBooking(Booking booking, Guest guest, Room room){

        try{
            PreparedStatement statement = connection.prepareStatement("\"INSERT INTO bookings (room_id,guest_id,date_in,date_out,value) VALUES (?,?,?,?,?)");
            statement.setInt(1, room.getId());
            statement.setInt(2, guest.getId());
            System.out.println("Added new booking");
        }catch (Exception e){
            System.out.println("Failed to add booking");
            System.out.println(e.toString());
        }
    }

}
