
package com.challengercity.datura;

/**
 *
 * @author Ben Sergent V at http://sergenttech.net/
 */
public class Inventory {

    private Item[] items;

    public Inventory(int size) {
        items = new Item[size];
    }
    
    public Item addItem(Item item) {
        for (int i = 0; i < items.length; i++) {
            if (items[i] == null) {
                items[i] = item;
                return null;
            }
            if (items[i].canStack(item)) {
                items[i].setCount(items[i].getCount()+item.getCount());
                return null;
            }
        }
        return item;
    }
    
    public boolean hasItem(Item item) {
        for (int i = 0; i < items.length; i++) {
            if (items[i] != null && items[i].canStack(item)) {
                return true;
            }
        }
        return false;
    }
    
    public boolean removeItem(Item item) {
        for (int i = 0; i < items.length; i++) {
            if (items[i] != null && items[i].canStack(item)) {
                items[i] = null;
                return true;
            }
        }
        return false;
    }
    
    public Item getItemAt(int pos) {
        if (pos < items.length) {
            return items[pos];
        }
        return null;
    }
    
    public void removeItemAt(int pos) {
        items[pos] = null;
    }
    
    public float getWeight() {
        return 0;
    }
    
}
