package bo;

import ui.CartInfo;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

public class CartHandler {

    public static Collection<CartInfo> showCart(String username){
        Collection c = Cart.showCart(username);
        ArrayList<CartInfo> cart = new ArrayList<CartInfo>();
        for (Iterator it = c.iterator(); it.hasNext();){
            Cart cItem = (Cart) it.next();
            cart.add(new CartInfo(cItem.getOrderID(), cItem.getTotal_amount(), cItem.getUserID()));
        }
        return cart;
    }
}

