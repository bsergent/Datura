
package com.challengercity.datura;

/**
 *
 * @author Ben Sergent V at http://sergenttech.net/
 */
public abstract class Entity extends RenderableObject {
    
    private int entityId;
    public byte motionX = 0;
    public byte motionY = 0;
    public byte facing = 3;

    public Entity() {
    }

    public Entity(int x, int y, int entityId) {
        super(x, y, Tile.SIZE, Tile.SIZE);
        this.entityId = entityId;
    }
    
    public int getEntityId() {
        return entityId;
    }
    
    public abstract void tick(long delta);
    
}
