package bo;

import ui.CartInfo;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

public class CartHandler {

    public static Collection<CartInfo> showCart(String username){
        Collection c = Cart.showCart(username);
        ArrayList<CartInfo> cart = new ArrayList<CartInfo>();
        for (Iterator it = c.iterator(); it.hasNext();){
            Cart cItem = (Cart) it.next();
            if(cItem != null) {
                CartInfo temp = new CartInfo(cItem.getOrderID(), cItem.getUserID());
                ArrayList<Item> itemArrayList = cItem.getItems();
                for (int i = 0; i < itemArrayList.size(); i++) {
                    temp.addItems(itemArrayList.get(i).getiName(), itemArrayList.get(i).getPrice(), itemArrayList.get(i).getItemID(), itemArrayList.get(i).getQuantity());
                }
                cart.add(temp);
            }
        }
        return cart;
    }

    public static void addToCart (String username, int itemID){
        Cart.addToCart(username, itemID);
    }

    public static void removeFromCart ( int itemID, int orderID){
        Cart.removeFromCart( itemID, orderID);
    }


}

