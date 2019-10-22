package DatabaseOperators;

import java.sql.Connection;

public class BookingDAO {

    Connection connection;

    public BookingDAO(Connection connection){
        this.connection = connection;
    }


}
