package bo;

import db.ItemDB;
import db.PersonDB;

import java.util.Collection;

public class Item {
    private int itemID;
    private String iName;
    private int price;
    private int quantity;

    static public Collection searchItems(){
        return ItemDB.searchItems();
    }

    protected Item(int id, String name, int price, int quantity){
        itemID = id;
        iName = name;
        this.price = price;
        this.quantity = quantity;
    }

    public int getItemID() {
        return itemID;
    }

    public void setItemID(int itemID) {
        this.itemID = itemID;
    }

    public String getiName() {
        return iName;
    }

    public void setiName(String iName) {
        this.iName = iName;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
