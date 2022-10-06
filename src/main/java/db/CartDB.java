package db;


import java.sql.*;
import java.util.Collection;
import java.util.Vector;

public class CartDB extends bo.Cart{

    public CartDB(int orderID, int total_amount, String userID) {
        super(orderID, total_amount, userID);
    }

    public static Collection showCart(String username){
        System.out.println("in cart db" + " " + username);
        Vector v = new Vector();

        //använda username för att hitta cart id
        //hämta cart "total_amount", "current_status", samt vilka items orders som är kopplade till denna
        //hämta data inom items orders gällande "amount", "itemID"
        //hämta data om item gällande deras pris     //kanske

        try {
            Connection con = dbManager.getConnection();
            PreparedStatement st = con.prepareStatement("SELECT * FROM T_Order WHERE userID = ?");
            st.setString(1, username);
            ResultSet rs = st.executeQuery();

            while (rs.next()){
                int orderID = rs.getInt("orderID");
                int total = rs.getInt("total_amount");
                String userID = rs.getString("userID");
                System.out.println(total);
                System.out.println(orderID + " " + total + "kr " + userID);
                v.add(new CartDB(orderID,total,userID));
            }
        } catch (SQLException e){
            e.printStackTrace();}
        return v;
    }

    public static Collection addToCrart(String username, int itemId){
        return null;
    }

}