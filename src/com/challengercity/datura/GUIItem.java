
package com.challengercity.datura;

/**
 *
 * @author Ben Sergent V/ha1fBit
 */
public class GUIItem extends GUI {

    private Item myItem;
    
    public GUIItem(int x, int y, int width, int height, int picX, int picY) {
        super(x, y, width, height, picX, picY, 16, 16);
    }

    @Override
    public void checkMouse() {
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
