package bo;

import db.CartDB;

import java.util.ArrayList;
import java.util.Collection;

public class Cart {

    private int orderID;
    private int total_amount;
    private String userID;

    private cartStatus status;
    private ArrayList<Item> items;


    static public Collection showCart(String username){
        return CartDB.showCart(username);
    }
    static public void addToCart(String username, int itemID){
        CartDB.addToCart(username, itemID);
    }

    static public void removeFromCart( int itemID, int orderID){
        CartDB.removeFromCart( itemID, orderID);
    }
    public Cart(int orderID, int total_amount, String userID) {
        this.orderID = orderID;
        this.total_amount = total_amount;
        this.userID = userID;
        this.status = cartStatus.PROCESSING;
    }

    public int getOrderID() {
        return orderID;
    }

    public void setOrderID(int orderID) {
        this.orderID = orderID;
    }

    public int getTotal_amount() {
        return total_amount;
    }

    public void setTotal_amount(int total_amount) {
        this.total_amount = total_amount;
    }

    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    public void addItems(Item item){
        if (items == null){
            items = new ArrayList<>();
        }
        items.add(item);
        System.out.println(item + " has been added to cart");
    }

    public ArrayList<Item> getItems() {
        return items;
    }

    //ha kvar?
    public void addItems(String iName, int price, int itemID){
        Item item = new Item(iName, price, itemID);

        if (items == null){
            items = new ArrayList<>();
        }
        items.add(item);
    }

    public String toString(){
        return userID.toString() + " "  + items.toString();
    }
}
