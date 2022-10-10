package db;

import java.sql.*;
import java.util.Collection;
import java.util.Vector;

public class CartDB extends bo.Cart{

    public CartDB(int orderID, String userID) {
        super(orderID, userID);
    }

    public static Collection showCart(String username){
        Connection con ;
        Vector v = new Vector();
        CartDB cart = null;
        try {
            con = dbManager.getConnection();
            PreparedStatement test = con.prepareStatement(" SELECT t_order.orderID, t_order.userID, current_status, t_itemsorder.itemID, t_itemsorder.amount, " +
                    "item_name, price " +
                    "FROM t_order, t_itemsorder, t_items " +
                    "WHERE t_order.userID = ?" +
                    "AND t_order.current_status = 'processing' " +
                    "AND t_order.orderID = t_itemsorder.orderID " +
                    "AND t_itemsorder.itemID = t_items.itemID");
            test.setString(1, username);
            ResultSet rs = test.executeQuery();

            while (rs.next()){
                int orderID = rs.getInt("orderID");
                String userID = rs.getString("userID");
                String iName = rs.getString("item_name");
                int price = rs.getInt("price");
                int item_id = rs.getInt("itemID");
                int amount = rs.getInt("amount");
                if(cart == null)
                    cart = new CartDB(orderID,userID);
                cart.addItems(iName, price,item_id, amount);
            }
        } catch (SQLException e){
            e.printStackTrace();
        }
        v.add(cart);
        return v;
    }

    public static void removeFromCart(int itemID, int orderID){
        try{
            Connection con = dbManager.getConnection();
            PreparedStatement remove = con.prepareStatement("DELETE FROM t_itemsorder WHERE itemID = ? AND orderID = ?");
            remove.setInt(1, itemID);
            remove.setInt(2, orderID);
            remove.executeUpdate();
        } catch (SQLException e){
            e.printStackTrace();}
    }

    public static void addToCart(String username, int itemId){
        Connection con = null;
        try{
            con = dbManager.getConnection();
            con.setAutoCommit(false);

            PreparedStatement getOrder = con.prepareStatement("SELECT * FROM t_order WHERE userID = ? AND current_status = 'processing'");
            getOrder.setString(1, username);
            ResultSet rs = getOrder.executeQuery();

            int orderID = 0;
            boolean orderExists = false;
            while(rs.next()){
                orderID = rs.getInt("orderID");
                orderExists = true;
            }

            if(!orderExists){
                PreparedStatement insertOrder = con.prepareStatement("INSERT INTO T_Order(userID, current_status) VALUES (?, 'processing');");
                insertOrder.setString(1, username);
                insertOrder.executeUpdate();

                PreparedStatement getOrderId = con.prepareStatement("SELECT * FROM t_order WHERE userID = ? AND current_status = 'processing'");
                getOrderId.setString(1, username);
                ResultSet rs2 = getOrderId.executeQuery();
                while(rs2.next()){
                    orderID = rs2.getInt("orderID");
                }
            }

            PreparedStatement get = con.prepareStatement("SELECT * FROM t_itemsorder WHERE userID = ? AND itemID = ? AND orderID = ?");
            get.setString(1, username);
            get.setInt(2, itemId);
            get.setInt(3, orderID);
            ResultSet rs3 = get.executeQuery();

            int amount = 0;
            boolean exist = false;
            while (rs3.next()) {
                amount = rs3.getInt("amount");
                exist = true;
            }

            if(exist){
                PreparedStatement update = con.prepareStatement("UPDATE t_itemsorder SET amount = ? WHERE userID = ? AND itemID = ?");
                update.setInt(1, amount + 1);
                update.setString(2, username);
                update.setInt(3, itemId);
                update.executeUpdate();
            }else {
                PreparedStatement insert = con.prepareStatement("INSERT INTO T_ItemsOrder (orderID, itemID, amount, userID) VALUES (?, ?, ?, ?)");
                insert.setInt(1, orderID);
                insert.setInt(2, itemId);
                insert.setInt(3, 1);
                insert.setString(4, username);
                insert.executeUpdate();
            }
            con.commit();
        } catch (SQLException e){
            try {
                con.rollback();
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }
            e.printStackTrace();}
    }
}