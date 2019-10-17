package controllers;

import java.sql.Connection;

public class SQLiteController {

    private Connection connection;

    private SQLiteController(){}

    private static class SingletonHelper{
        private static final SQLiteController INSTANCE = new SQLiteController();
    }

    public static SQLiteController getInstance(){
        return SingletonHelper.INSTANCE;
    }

}
