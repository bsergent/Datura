
package com.challengercity.datura;

/**
 *
 * @author Ben Sergent V at http://sergenttech.net/
 */
public abstract class GUI extends RenderableObject {

    protected int picX, picY, picWidth, picHeight;
    protected String texName;
    protected String texExt;
    
    public GUI(int x, int y, int width, int height, int picX, int picY, int picWidth, int picHeight) {
        super(x, y, width, height);
        this.picX=picX-1;
        this.picY=picY;
        this.picWidth=picWidth;
        this.picHeight=picHeight;
        
    }
    
    public abstract void checkMouse();
    
}
