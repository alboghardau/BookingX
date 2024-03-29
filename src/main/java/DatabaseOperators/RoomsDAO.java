package DatabaseOperators;

import models.Room;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class RoomsDAO {

    Connection connection;

    public RoomsDAO(Connection connection){
        this.connection = connection;
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
            Logger.getLogger("test").log(Level.INFO,"List of rooms aquired");
            return list;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return Collections.emptyList();
    }

    //add room the the table
    public void addRoom(Room room){
        try{
            PreparedStatement statement = connection.prepareStatement("INSERT INTO rooms (name) VALUES (?)");
            statement.setString(1, room.getName());
            statement.executeUpdate();
            Logger.getLogger("test").log(Level.INFO,"Inserted room: "+room.getName());
        }catch (SQLException e){
            e.printStackTrace();
        }
    }

    //edit room into the table
    public void editRoom(Room newRoom){
        try{
            PreparedStatement statement = connection.prepareStatement("UPDATE rooms SET name = ? WHERE id = ?");
            statement.setString(1,newRoom.getName());
            statement.setInt(2,newRoom.getId());
            statement.executeUpdate();
        }catch (SQLException e){
            e.printStackTrace();
            Logger.getGlobal().log(Level.INFO, "Failed to edit room "+newRoom.getId());
        }
    }

    //delete room
    public void deleteRoom(Room room){
        try{
            PreparedStatement statement = connection.prepareStatement("DELETE FROM rooms WHERE id = ?");
            statement.setInt(1, room.getId());
            statement.executeUpdate();
        }catch (SQLException e){
            e.printStackTrace();
        }
    }

    //returns one room from table as object
    public Room oneRoom(int id){
        try{
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM rooms WHERE id = ?");
            statement.setInt(1,id);
            ResultSet set = statement.executeQuery();
            Room room = new Room(set.getInt("id"),set.getString("name"));
            return room;
        }catch (SQLException e){
            e.printStackTrace();
        }
        return null;
    }
}
