package DatabaseOperators;

import models.Booking;
import models.Guest;
import models.Room;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class BookingDAO {

    Connection connection;

    public BookingDAO(Connection connection){
        this.connection = connection;
    }

    public List<Booking> listBookings(List<Room> roomList, List<Guest> guestList){
        List<Booking> list = new ArrayList<>();
        try{
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM bookings");
            ResultSet set = statement.executeQuery();
            while(set.next()){
                int bookingId = set.getInt("id");
                int guestId = set.getInt("guest_id");
                int roomId = set.getInt("room_id");
                Booking b = new Booking(
                        bookingId,
                        guestList.stream().filter(guest -> guest.getId() == guestId).findFirst().orElse(new Guest(0,null,null,null,null)),
                        roomList.stream().filter(room -> room.getId() == roomId).findFirst().orElse(new Room(0,null)),
                        set.getInt("persons"),
                        LocalDate.parse(set.getString("date_in")),
                        LocalDate.parse(set.getString("date_out")),
                        set.getDouble("value")
                );
                list.add(b);
            }
            return list;
        }catch (SQLException e){
            e.printStackTrace();
        }
        return Collections.emptyList();
    }

    //retrieves only the bookings records in a month - use (?) in the SQL statement
    public List<Booking> monthlyBooking(List<Room> roomList, List<Guest> guestList, LocalDate date){
        List<Booking> list = new ArrayList<>();
        LocalDate lastDay = LocalDate.of(date.getYear(),date.getMonth(),date.lengthOfMonth());
        LocalDate firsDay = LocalDate.of(date.getYear(),date.getMonth(),1);
        try{
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM bookings WHERE date_in <= (?) AND date_out >= (?)");
            statement.setString(1, lastDay.toString());
            statement.setString(2, firsDay.toString());
            System.out.println(statement);
            System.out.println(firsDay);
            System.out.println(lastDay);
            ResultSet set = statement.executeQuery();
            while (set.next()){
                int bookingId = set.getInt("id");
                int guestId = set.getInt("guest_id");
                int roomId = set.getInt("room_id");
                Booking b = new Booking(
                        bookingId,
                        guestList.stream().filter(guest -> guest.getId() == guestId).findFirst().orElse(new Guest(0,null,null,null,null)),
                        roomList.stream().filter(room -> room.getId() == roomId).findFirst().orElse(new Room(0,null)),
                        set.getInt("persons"),
                        LocalDate.parse(set.getString("date_in")),
                        LocalDate.parse(set.getString("date_out")),
                        set.getDouble("value")
                );
                System.out.println(b);
                list.add(b);
            }
            return list;
        }catch (SQLException e){
            e.printStackTrace();
        }
        return Collections.emptyList();
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

    public void deleteBooking(Booking booking){
        try{
            PreparedStatement statement = connection.prepareStatement("DELETE FROM bookings WHERE id = ?");
            statement.setInt(1, booking.getId());
            statement.executeUpdate();
        }catch (SQLException e){
            e.printStackTrace();
        }
    }
}
