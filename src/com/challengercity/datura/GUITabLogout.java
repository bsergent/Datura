
package com.challengercity.datura;

import static org.lwjgl.opengl.GL11.*;

/**
 *
 * @author Ben Sergent V/ha1fBit
 */
public class GUITabLogout extends GUITab {
    
    public GUITabLogout(int x, int y, int width, int height) {
        super(x, y, width, height, 1, 0);
        Controller.addListenerMouse(new ListenerMouse() {

            @Override
            public void buttonDown(int key, int x, int y) {
                if (key==0&&x>=posX&&x<posX+GUITabLogout.this.width&&y>=posY&&y<=posY+GUITabLogout.this.height) { // If within button
                    // Click button
                }
            }
            
        });
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
        ResourcePool.getFont("Courier", 16).drawString(posX+2, posY+2, "Logout");
    }

}
