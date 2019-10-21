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
            Logger.getLogger("test").log(Level.INFO,"RoomsDAO - Rooms list aquired");
            return list;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return Collections.emptyList();
    }
}
