
package com.challengercity.datura;

import static org.lwjgl.opengl.GL11.*;
import org.newdawn.slick.opengl.Texture;

/**
 *
 * @author Ben Sergent V at http://sergenttech.net/
 */
public class Door {

    public int curX;
    public int curY;
    public int destX;
    public int destY;
    public String destRoom;
    

    public Door() {
    }

    public Door(int curX, int curY, int destX, int destY, String destRoom) {
        this.curX = curX;
        this.curY = curY;
        this.destX = destX;
        this.destY = destY;
        this.destRoom = destRoom;
    }
    
    public void draw(Tile top, Tile bottom, Tile left, Tile right) {
        glBegin(GL_QUADS); // Always fails if above if-statement is removed
//            glColor4f(1,1,1,1);
//            glVertex3f(ViewPort.getViewX(curX*Tile.SIZE),ViewPort.getViewY(curY*Tile.SIZE),0);
//            glVertex3f(ViewPort.getViewX(curX*Tile.SIZE)+Tile.SIZE,ViewPort.getViewY(curY*Tile.SIZE),0);
//            glVertex3f(ViewPort.getViewX(curX*Tile.SIZE)+Tile.SIZE,ViewPort.getViewY(curY*Tile.SIZE)+Tile.SIZE,0);
//            glVertex3f(ViewPort.getViewX(curX*Tile.SIZE),ViewPort.getViewY(curY*Tile.SIZE)+Tile.SIZE,0);
            
            Texture texture = ResourcePool.getTexture("tilesA", ".png");
            texture.bind();
            byte texX = 0;
            byte texY = 3;
            byte doorX = 0;
            byte doorY = 0;
            
            if (left.equals(Tile.STONE) && right.equals(Tile.STONE) && bottom.equals(Tile.STONEFLOOR)) { // Down
                texX=0;
                texY=3;
                doorY = 1;
            }
            if (top.equals(Tile.STONE) && bottom.equals(Tile.STONE) && left.equals(Tile.STONEFLOOR)) { // Left
                texX=1;
                texY=3;
                doorX = -1;
            }
            if (top.equals(Tile.STONE) && bottom.equals(Tile.STONE) && right.equals(Tile.STONEFLOOR)) { // Right
                texX=2;
                texY=3;
                doorX = 1;
            }
            if (left.equals(Tile.STONE) && right.equals(Tile.STONE) && top.equals(Tile.STONEFLOOR)) { // Up
                texX=3;
                texY=3;
                doorY = -1;
            }
            
            glTexCoord2f(Renderer.getTextureFloat((texX*64), texture.getImageWidth()), Renderer.getTextureFloat((texY*64), texture.getImageHeight()));  // Upper-Left
            glVertex2i(ViewPort.getViewX(curX*Tile.SIZE), ViewPort.getViewY(curY*Tile.SIZE));

            glTexCoord2f(Renderer.getTextureFloat((texX*64)+64, texture.getImageWidth()), Renderer.getTextureFloat((texY*64), texture.getImageHeight()));  // Upper-Right
            glVertex2i(ViewPort.getViewX(curX*Tile.SIZE)+Tile.SIZE, ViewPort.getViewY(curY*Tile.SIZE));

            glTexCoord2f(Renderer.getTextureFloat((texX*64)+64, texture.getImageWidth()), Renderer.getTextureFloat((texY*64)+64, texture.getImageHeight()));  // Lower-Right
            glVertex2i(ViewPort.getViewX(curX*Tile.SIZE)+Tile.SIZE, ViewPort.getViewY(curY*Tile.SIZE)+Tile.SIZE);

            glTexCoord2f(Renderer.getTextureFloat((texX*64), texture.getImageWidth()), Renderer.getTextureFloat((texY*64)+64, texture.getImageHeight()));  // Lower-Left
            glVertex2i(ViewPort.getViewX(curX*Tile.SIZE), ViewPort.getViewY(curY*Tile.SIZE)+Tile.SIZE);
            
            texY=2;
            
            glTexCoord2f(Renderer.getTextureFloat((texX*64), texture.getImageWidth()), Renderer.getTextureFloat((texY*64), texture.getImageHeight()));  // Upper-Left
            glVertex2i(ViewPort.getViewX((curX+doorX)*Tile.SIZE), ViewPort.getViewY((curY+doorY)*Tile.SIZE));

            glTexCoord2f(Renderer.getTextureFloat((texX*64)+64, texture.getImageWidth()), Renderer.getTextureFloat((texY*64), texture.getImageHeight()));  // Upper-Right
            glVertex2i(ViewPort.getViewX((curX+doorX)*Tile.SIZE)+Tile.SIZE, ViewPort.getViewY((curY+doorY)*Tile.SIZE));

            glTexCoord2f(Renderer.getTextureFloat((texX*64)+64, texture.getImageWidth()), Renderer.getTextureFloat((texY*64)+64, texture.getImageHeight()));  // Lower-Right
            glVertex2i(ViewPort.getViewX((curX+doorX)*Tile.SIZE)+Tile.SIZE, ViewPort.getViewY((curY+doorY)*Tile.SIZE)+Tile.SIZE);

            glTexCoord2f(Renderer.getTextureFloat((texX*64), texture.getImageWidth()), Renderer.getTextureFloat((texY*64)+64, texture.getImageHeight()));  // Lower-Left
            glVertex2i(ViewPort.getViewX((curX+doorX)*Tile.SIZE), ViewPort.getViewY((curY+doorY)*Tile.SIZE)+Tile.SIZE);
        glEnd();
    }
    
}
