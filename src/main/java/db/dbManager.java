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
            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/webbshop","root","groda1");
            System.out.println(con);
        }catch (Exception e) {
            e.printStackTrace();
            System.out.println(e.getMessage());
        }
        System.out.println("Opened database successfully");
    }

    public static Connection getConnection(){
        System.out.println("hejhej");
        return getInstance().con;
    }
}
