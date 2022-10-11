package db;

import java.sql.Connection;
import java.sql.DriverManager;


public class dbManager {
    private static dbManager instance = null;
    private Connection con = null;

    /**
     * get connection to DB
     * @return connection
     */
    private static dbManager getInstance(){
        if (instance == null)
            instance = new dbManager();
        return instance;
    }

    /**
     * connect to database
     */
    private dbManager(){
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/webbshop","root","viktor");
        }catch (Exception e) {
            e.printStackTrace();
            System.out.println(e.getMessage());
        }
    }

    public static Connection getConnection(){
        return getInstance().con;
    }
}
