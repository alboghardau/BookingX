package controllers;

import DatabaseOperators.RoomsDAO;
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
    private RoomsDAO roomsDAO;

    private SQLiteController(){
        try{
            Class.forName("org.sqlite.JDBC");
            this.connection = DriverManager.getConnection("jdbc:../../resources/sqlite:Bookings.db");
            System.out.println("Conencted to database!");

            roomsDAO = new RoomsDAO(connection);
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
        return roomsDAO.listRooms();
    }
}
