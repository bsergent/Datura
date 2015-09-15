
package com.challengercity.datura;

import static org.lwjgl.opengl.GL11.*;
/**
 *
 * @author Ben Sergent V at http://sergenttech.net/
 */
public class GUITabInventory extends GUITab {

    private Inventory inv = new Inventory(25);
    private Item clickedItem;
    
    public GUITabInventory(int x, int y, int width, int height) {
        super(x, y, width, height, 0, 0);
    }
    
    public void setInv(Inventory inv) {
        this.inv = inv;
    }
    
    public Inventory getInv() {
        return inv;
    }

    @Override
    public void checkMouse() {
        // TODO Rewrite mouse stuff to be event-based like the keyboard
    }

    @Override
    public void draw() {
        glDisable(GL_TEXTURE_2D);
        glColor4f(0,0,0,0.5f);
        glBegin(GL_QUADS);
            glVertex3f(posX,posY,0);
            glVertex3f(posX+width,posY,0);
            glVertex3f(posX+width,posY+height,0);
            glVertex3f(posX,posY+height,0);
        glEnd();
        glEnable(GL_TEXTURE_2D);
        glColor4f(1,1,1,1);
        ResourcePool.getFont("Courier", 16).drawString(posX+2, posY+2, "Inventory");
        ResourcePool.getFont("Courier", 12).drawString(posX+2, posY+20, "Weight:"+inv.getWeight()+"/100 lbs.");
    }

    @Override
    public void delete() {
        // Do nothing
    }

}
