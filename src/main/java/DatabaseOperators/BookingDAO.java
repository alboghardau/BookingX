package DatabaseOperators;

import models.Booking;
import models.Guest;
import models.Room;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class BookingDAO {

    Connection connection;

    public BookingDAO(Connection connection){
        this.connection = connection;
    }

    public void addBooking(Booking booking, Guest guest, Room room){
        try{
            PreparedStatement statement = connection.prepareStatement("INSERT INTO bookings (room_id, guest_id, date_in, date_out, value) ");
            statement.setInt(1,room.getId());
            statement.setInt(2,guest.getId());
            statement.setString(3,booking.getCheckIn().toString());
            statement.setString(4,booking.getCheckOut().toString());
            statement.setDouble(5, booking.getPrice());
            statement.executeUpdate();
        }catch (SQLException e){
            e.printStackTrace();
            Logger.getGlobal().log(Level.INFO, "Failed to add booking into database");
        }
    }
}
