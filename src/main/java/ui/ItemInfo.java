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
}

