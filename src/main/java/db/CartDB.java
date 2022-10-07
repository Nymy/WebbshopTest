package db;


import java.sql.*;
import java.util.Collection;
import java.util.Vector;

public class CartDB extends bo.Cart{

    public CartDB(int orderID, int total_amount, String userID) {
        super(orderID, total_amount, userID);
    }
    /*
    private ItemDB(int id, String name, int price, int quantity){
        super(id, name,price,quantity);
    }
     */


    public static Collection showCart(String username){
        Connection con = null;
        Vector v = new Vector();
        CartDB cart = null;
        //klar: använda username för att hitta cart id
        //klar: hämta cart "total_amount", "current_status", samt vilka items orders som är kopplade till denna
        //hämta data inom items orders gällande "amount", "itemID"
        //hämta data om item gällande deras pris     //kanske

        try {
            con = dbManager.getConnection();
            //con.setAutoCommit(false);
            PreparedStatement test = con.prepareStatement(" SELECT t_order.orderID, total_amount, t_order.userID, current_status, t_itemsorder.itemID, " +
                    "item_name, price " +
                    "FROM t_order, t_itemsorder, t_items " +
                    "WHERE t_order.userID = ?" +
                    "AND t_order.current_status = 'processing' " +
                    "AND t_order.orderID = t_itemsorder.orderID " +
                    "AND t_itemsorder.itemID = t_items.itemID");


          //  PreparedStatement st = con.prepareStatement("SELECT * FROM T_Order WHERE userID = ? AND current_status = 'processing'");
            test.setString(1, username);
            ResultSet rs = test.executeQuery();

            while (rs.next()){
                int orderID = rs.getInt("orderID");
                int total = rs.getInt("total_amount");
                String userID = rs.getString("userID");
                String iName = rs.getString("item_name");
                int price = rs.getInt("price");
                int item_id = rs.getInt("itemID");
                if(cart == null)
                    cart = new CartDB(orderID,total,userID);
                cart.addItems(iName, price,item_id);

            }

        } catch (SQLException e){
            e.printStackTrace();}
        v.add(cart);
        return v;
    }

    /**
     * Hämta rätt order där status är processing
     * hämta itemorder
     * */

    ///*
    private static Collection getItemsInCart(String user_id){
        Vector v = new Vector();
        try {
            Connection con = dbManager.getConnection();
            PreparedStatement test = con.prepareStatement(" SELECT t_order.orderID, total_amount, t_order.userID, current_status, t_itemsorder.itemID, " +
                    "item_name, price " +
                    "FROM t_order, t_itemsorder, t_items " +
                    "WHERE t_order.userID = ?" +
                    "AND t_order.current_status = 'processing' " +
                    "AND t_order.orderID = t_itemsorder.orderID " +
                    "AND t_itemsorder.itemID = t_items.itemID");
      //      PreparedStatement st = con.prepareStatement("SELECT * FROM T_ItemsOrder WHERE orderID = ?");
            test.setString(1, user_id);
            ResultSet rs = test.executeQuery();
            while (rs.next()) {
              //  int itemID = rs.getInt("itemID");
                int amount = rs.getInt("price");
                String name = rs.getString("item_name");
                System.out.println(name + " " + amount);
            }

        } catch (SQLException e){
            e.printStackTrace();}

        return v;
    }

     //*/

    public static void addToCart(String username, int itemId){
        try{
            Connection con = dbManager.getConnection();
            PreparedStatement add = con.prepareStatement("INSERT INTO t_itemsorder(orderID, itemID, amount, userID) " +
            "SELECT orderID, ?, 1, ? FROM t_order WHERE userID = ?");
            add.setInt(1, itemId);
            add.setString(2, username);
            add.setString(3, username);
            add.executeUpdate();
        } catch (SQLException e){
        e.printStackTrace();}

    }


    public static void removeFromCart(int itemID, int orderID){
        System.out.println("remove item with id: " + itemID);
        try{
            Connection con = dbManager.getConnection();
            PreparedStatement remove = con.prepareStatement("DELETE FROM t_itemsorder WHERE itemID = ? AND orderID = ? LIMIT 1");
            remove.setInt(1, itemID);
            remove.setInt(2, orderID);
            remove.executeUpdate();
        } catch (SQLException e){
            e.printStackTrace();}
    }

}