package DatabaseOperators;

import javafx.scene.Parent;
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
                        set.getString("name"),
                        set.getString("familyName"),
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
            PreparedStatement statement = connection.prepareStatement("INSERT INTO guests (name,familyName,telephone,email) VALUES (?,?,?,?)");
            statement.setString(1,guest.getName());
            statement.setString(2,guest.getFamilyName());
            statement.setString(3,guest.getTelephone());
            statement.setString(4,guest.getEmail());
            statement.executeUpdate();
        }catch (SQLException e){
            e.printStackTrace();
        }
    }
}
