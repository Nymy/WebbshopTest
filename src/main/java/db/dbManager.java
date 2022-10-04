package db;

import java.sql.Connection;
import java.sql.DriverManager;

public class dbManager {
    private static dbManager instance = null;
    private Connection con = null;

    private static dbManager getInstance(){
        if (instance == null)
            instance = new dbManager();
        return instance;
    }

    private dbManager(){
        try {
            Class.forName("org.postgresql.Driver").newInstance();
            con = DriverManager.getConnection("postgres://postgres:mysecretpassword@db:5432/postgres");
        }catch (Exception e) {e.printStackTrace();}
    }

    public static Connection getConnection(){
        return getInstance().con;
    }
}
