package ui;

import bo.Item;
import bo.cartStatus;

import java.util.ArrayList;

public class CartInfo {
    private int orderID;
    private int total_amount;
    private String userID;

    private cartStatus status;
    private ArrayList<ItemInfo> items;

    public CartInfo(int orderID, int total_amount, String userID) {
        this.orderID = orderID;
        this.total_amount = total_amount;
        this.userID = userID;
        this.status = cartStatus.PROCESSING;
        items = null;
    }

    public CartInfo(int orderID, int total_amount, String userID, ArrayList itemsincart) {
        this.orderID = orderID;
        this.total_amount = total_amount;
        this.userID = userID;
        this.status = cartStatus.PROCESSING;
        items = itemsincart;
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

    public ArrayList<ItemInfo> getItems() {
        return items;
    }

    public void addItems(ArrayList item){
        this.items = item;
    }

    public void addItems(String iName, int price, int itemID, int amount){
        ItemInfo item = new ItemInfo(itemID, iName, price, amount);

        if (items == null){
            items = new ArrayList<>();
        }
        items.add(item);
    }

    @Override
    public String toString() {
        return "CartInfo{" +
                "orderID=" + orderID +
                ", total_amount=" + total_amount +
                ", userID='" + userID + '\'' +
                ", status=" + status +
                ", items=" + items +
                '}';
    }
}
