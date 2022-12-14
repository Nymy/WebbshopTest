package db;


import java.sql.*;
import java.util.Collection;
import java.util.Vector;

public class PersonDB extends bo.Person{

    /**
     * Tries to log in the user through a search
     * @param uname username of the user to login
     * @param password password of the user to login
     * @return returns full information of the user
     */
    public static Collection searchUser(String uname, String password){
        Vector v = new Vector();

        try {
            Connection con = dbManager.getConnection();
            PreparedStatement st = con.prepareStatement("SELECT * FROM T_Person WHERE username = ? AND password = ?");
            st.setString(1, uname);
            st.setString(2, password);
            ResultSet rs = st.executeQuery();

            while (rs.next()){
                String fname = rs.getString("first_name");
                String lname = rs.getString("last_name");
                int postC = rs.getInt("postcode");
                String user = rs.getString("username");
                String pass = rs.getString("password");
                v.add(new PersonDB(fname, lname, postC, user, pass));
            }
        } catch (SQLException e){
            e.printStackTrace();}
        return v;
    }

    private PersonDB(String fname, String lname, int postcode, String username, String password) {
        super(fname, lname, postcode, username, password);
    }
}
