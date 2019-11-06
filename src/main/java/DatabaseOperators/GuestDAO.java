package DatabaseOperators;

import models.Guest;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class GuestDAO {

    Connection connection;

    public GuestDAO(Connection connection){
        this.connection = connection;
    }

    //retrieve all guests into ArrayList
    public List<Guest> listAll(){
        try{
            List<Guest> list = new ArrayList<>();
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM guests");
            ResultSet set = statement.executeQuery();
            while (set.next()){
                Guest g = new Guest(
                        set.getInt("id"),
                        set.getString("firstName"),
                        set.getString("lastName"),
                        set.getString("telephone"),
                        set.getString("email")
                );
                list.add(g);
            }
            return list;
        }catch (SQLException e){
            e.printStackTrace();
            Logger.getGlobal().log(Level.INFO,"Failed to get guests list");
        }
        return Collections.emptyList();
    }

    //add guest to the table
    public void addGuest(Guest guest){
        try{
            PreparedStatement statement = connection.prepareStatement("INSERT INTO guests (firstName,lastName,telephone,email) VALUES (?,?,?,?)");
            statement.setString(1,guest.getFirstName());
            statement.setString(2,guest.getLastName());
            statement.setString(3,guest.getTelephone());
            statement.setString(4,guest.getEmail());
            statement.executeUpdate();
        }catch (SQLException e){
            e.printStackTrace();
        }
    }

    //delete guest from the database
    public void deleteGuest(Guest guest){
        try {
            PreparedStatement statement = connection.prepareStatement("DELETE FROM bookigns WHERE id = ?");
            statement.setInt(1, guest.getId());
            statement.executeUpdate();
        }catch (SQLException e){
            e.printStackTrace();
        }
    }

    //retrieves last guest id
    public int lastGuestId(){
        try{
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM guests ORDER BY id DESC LIMIT 1");
            ResultSet set = statement.executeQuery();
            return set.getInt("id");
        }catch (SQLException e){
            e.printStackTrace();
        }
        return 0;
    }

    //returns one guest data as object
    public Guest oneGuest(int id){
        try{
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM guests WHERE id = ?");
            statement.setInt(1,id);
            ResultSet set = statement.executeQuery();
            return null;
        }catch (SQLException e){
            e.printStackTrace();
        }
        return null;
    }
}
