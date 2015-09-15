
package com.challengercity.datura;

/**
 *
 * @author Ben Sergent V at http://sergenttech.net/
 */
public class EntityItem extends Entity {

    private Item myItem;

    public EntityItem(int x, int y, int entityId) {
        super(x, y, entityId);
    }

    @Override
    public void tick(long delta) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void draw() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void delete() {
        throw new UnsupportedOperationException("Not supported yet.");
    }
    
}
