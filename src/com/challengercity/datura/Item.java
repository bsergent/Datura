
package com.challengercity.datura;

/**
 *
 * @author Ben Sergent V/ha1fBit
 */
public class Item {

    
    private ItemType type;
    private int damage;
    private int count;
    // TODO Figure out how to draw the items
    // TODO Add weights to items

    public Item(ItemType it) {
        this.type = it;
    }

    public Item(ItemType it, int count) {
        this.type = it;
        this.count = count;
    }
    
    public boolean canStack(Item item) {
        if (item.type.equals(this.type)) {
            return true;
        }
        return false;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public int getDamage() {
        return damage;
    }

    public void setDamage(int damage) {
        this.damage = damage;
    }

    public int getId() {
        return type.getId();
    }

    public ItemType getType() {
        return type;
    }

    public void setType(ItemType type) {
        this.type = type;
    }

}
