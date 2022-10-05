package db;


import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Collection;
import java.util.Vector;

public class PersonDB extends bo.Person{

    public static Collection searchUser (String username, String password){
        Vector v = new Vector();

        try {
            Connection con = dbManager.getConnection();
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery("select username, password from t_person");

            while (rs.next()){
                String user = rs.getString("username");
                String pass = rs.getString("password");
                v.add(new PersonDB("Viktor", "Lindström", 14152, user, pass));
            }
        } catch (SQLException e){
            e.printStackTrace();}
        return v;
    }

    private PersonDB(String fname, String lname, int postcode, String username, String password) {
        super(fname, lname, postcode, username, password);
    }
}
