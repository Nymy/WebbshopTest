package bo;



import ui.ItemInfo;
import ui.PersonInfo;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

public class ItemHandler {

public static Collection<ItemInfo> getItems(){
    Collection c = Item.searchItems();
    ArrayList<ItemInfo> items = new ArrayList<ItemInfo>();
    for (Iterator it = c.iterator(); it.hasNext();){
        Item item = (Item) it.next();
        items.add(new ItemInfo(item.getItemID(), item.getiName(), item.getPrice(), item.getQuantity()));
    }
    return items;
}
}

