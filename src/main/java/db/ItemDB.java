package db;


import java.sql.*;
import java.util.Collection;
import java.util.Vector;

public class ItemDB extends bo.Item{

    /**
     * search after all available items in the database
     * @return Collection of all the items
     */
    public static Collection searchItems(){
        Vector v = new Vector();
        try {
            Connection con = dbManager.getConnection();
            PreparedStatement st = con.prepareStatement("SELECT * FROM t_items");
            ResultSet rs = st.executeQuery();

            while (rs.next()){
                int itemID = rs.getInt("itemID");
                String iName = rs.getString("item_name");
                int price = rs.getInt("price");
                int quantity = rs.getInt("quantity");
                v.add(new ItemDB(itemID, iName, price, quantity));
            }
        } catch (SQLException e){
            e.printStackTrace();}
        return v;
    }

    private ItemDB(int id, String name, int price, int quantity){
        super(id, name,price,quantity);
    }
}