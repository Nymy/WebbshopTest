package ui;

import bo.Item;
import java.util.ArrayList;

public class CartInfo {
    enum cartStatus {processing, packed, sent}
    private int orderID;
    private int total_amount;
    private String userID;

    private cartStatus status;
    private ArrayList<Item> items;

    public CartInfo(int orderID, int total_amount, String userID) {
        this.orderID = orderID;
        this.total_amount = total_amount;
        this.userID = userID;
        this.status = cartStatus.processing;
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
    }
}
