package ui;

import bo.cartStatus;

import java.util.ArrayList;

public class CartInfo {
    private int orderID;

    private String userID;

    private cartStatus status;
    private ArrayList<ItemInfo> items;

    public CartInfo(int orderID, String userID) {
        this.orderID = orderID;
        this.userID = userID;
        this.status = cartStatus.PROCESSING;
        items = null;
    }

    public CartInfo(int orderID, String userID, ArrayList itemsincart) {
        this.orderID = orderID;
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
                ", userID='" + userID + '\'' +
                ", status=" + status +
                ", items=" + items +
                '}';
    }
}
