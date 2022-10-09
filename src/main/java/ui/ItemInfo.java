package ui;


public class ItemInfo {
    private int itemID;
    private String iName;
    private int price;
    private int quantity;

    public ItemInfo(int itemID, String iName, int price, int quantity) {
        this.itemID = itemID;
        this.iName = iName;
        this.price = price;
        this.quantity = quantity;
    }

    public ItemInfo(String iName, int price, int itemID) {
        this.itemID = itemID;
        this.iName = iName;
        this.price = price;
    }

    public int getItemID() {
        return itemID;
    }

    public String getiName() {
        return iName;
    }

    public int getPrice() {
        return price;
    }

    public int getQuantity() {
        return quantity;
    }

    @Override
    public String toString() {
        return itemID + " " + iName + " " + price;
    }
}

