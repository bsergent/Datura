
package com.challengercity.datura;

import java.awt.Color;
import java.util.ArrayList;
import java.util.HashMap;
import static org.lwjgl.opengl.GL11.*;
import org.newdawn.slick.opengl.Texture;

/**
 *
 * @author Ben Sergent V at http://sergenttech.net/
 */
public class Tile {

    private boolean collidable;
    private String name;
    private Item drop;
    private static Texture texture;
    private static HashMap<Color, Tile> tileColors = new HashMap<Color, Tile>();
    private static ArrayList<Tile> tileIds = new ArrayList<Tile>();
    public static final int SIZE = 64;
    public static final Tile AIR = new Tile().setCollidable(false).setName("Air").setColor(new Color(255,128,255)); // TODO Add fake tiles
    public static final Tile STONE = new Tile().setCollidable(true).setName("Stone").setColor(new Color(255,0,0));
    public static final Tile STONEFLOOR = new Tile().setCollidable(false).setName("Stone").setColor(new Color(0,255,0));

    public Tile() {
        tileIds.add(this);
    }
    
    public boolean isCollidable() {
        return collidable;
    }

    public Tile setCollidable(boolean collidable) {
        this.collidable = collidable;
        return this;
    }
    
    public Tile setColor(Color color) {
        tileColors.put(color, this);
        return this;
    }
    
    public static Tile getTileByColor(Color color) {
        return tileColors.get(color);
    }

//    public Item getDrop() {
//        return drop;
//    }
//
//    public Tile setDrop(Item drop) {
//        this.drop = drop;
//        return this;
//    }

    public int getTypeId() {
        return tileIds.indexOf(this);
    }
    
    public static Tile getTileById(int id) {
        if (id >= tileIds.size()) {
            return Tile.AIR;
        }
        return tileIds.get(id);
    }

    public String getName() {
        return name;
    }

    public Tile setName(String name) {
        this.name = name;
        return this;
    }
    
    public void draw(int x, int y, Tile top, Tile bottom, Tile left, Tile right) {
        if (texture == null) {
            texture = ResourcePool.getTexture("tilesA", ".png");
        }
        if (ViewPort.checkOnScreen(x, y, SIZE, SIZE)) {
            texture.bind(); // TODO Only bind once

            glBegin(GL_QUADS);
            if (this.equals(Tile.STONE)) { // TODO Fix some top-right and left inner corners of stone walls
                /* Choose Texture */
                // Down, Left, Right, Up, Left-Up, Right-Up, Right-Down, Left-Down, Left-Up2, Right-Up2
                byte texX = 0; // Top > Bottom > Right > Left
                byte texY = 0;
                
                if (right.equals(Tile.STONE) && bottom.equals(Tile.STONE)) { // Left-Up2
                    texX=4;
                    texY=1;
                }
                if (left.equals(Tile.STONE) && bottom.equals(Tile.STONE)) { // Right-Up2
                    texX=5;
                    texY=1;
                }
                if (top.equals(Tile.STONE) && right.equals(Tile.STONE)) {
                    texX=3;
                    texY=0;
                }
                if (left.equals(Tile.STONE) && top.equals(Tile.STONE)) {
                    texX=3;
                    texY=0;
                }
                
                if (left.equals(Tile.STONE) && right.equals(Tile.STONE) && bottom.equals(Tile.STONEFLOOR)) { // Down
                    texX=0;
                    texY= x%64==0?0:(byte)1;
                    
                }
                if (top.equals(Tile.STONE) && bottom.equals(Tile.STONE) && left.equals(Tile.STONEFLOOR)) { // Left
                    texX=1;
                    texY= y%64==0?0:(byte)1;
                }
                if (top.equals(Tile.STONE) && bottom.equals(Tile.STONE) && right.equals(Tile.STONEFLOOR)) { // Right
                    texX=2;
                    texY= y%64==0?0:(byte)1;
                }
                if (left.equals(Tile.STONE) && right.equals(Tile.STONE) && top.equals(Tile.STONEFLOOR)) { // Up
                    texX=3;
                    texY=0;
                }
                
                if (left.equals(Tile.STONE) && top.equals(Tile.STONE) && right.equals(Tile.STONEFLOOR) && bottom.equals(Tile.STONEFLOOR)) { // Left-Up
                    texX=4;
                    texY=0;
                }
                if (top.equals(Tile.STONE) && right.equals(Tile.STONE) && left.equals(Tile.STONEFLOOR) && bottom.equals(Tile.STONEFLOOR)) { // Right-Up
                    texX=5;
                    texY=0;
                }
                if (right.equals(Tile.STONE) && bottom.equals(Tile.STONE) && top.equals(Tile.STONEFLOOR) && left.equals(Tile.STONEFLOOR)) { // Right-Down
                    texX=6;
                    texY=0;
                }
                if (left.equals(Tile.STONE) && bottom.equals(Tile.STONE) && top.equals(Tile.STONEFLOOR) && right.equals(Tile.STONEFLOOR)) { // Left-Down
                    texX=7;
                    texY=0;
                }
                
                /* Draw Texture */
                glTexCoord2f(Renderer.getTextureFloat((texX*64), texture.getImageWidth()), Renderer.getTextureFloat((texY*64), texture.getImageHeight()));  // Upper-Left
                glVertex2i(ViewPort.getViewX(x), ViewPort.getViewY(y));

                glTexCoord2f(Renderer.getTextureFloat((texX*64)+64, texture.getImageWidth()), Renderer.getTextureFloat((texY*64), texture.getImageHeight()));  // Upper-Right
                glVertex2i(ViewPort.getViewX(x)+SIZE, ViewPort.getViewY(y));

                glTexCoord2f(Renderer.getTextureFloat((texX*64)+64, texture.getImageWidth()), Renderer.getTextureFloat((texY*64)+64, texture.getImageHeight()));  // Lower-Right
                glVertex2i(ViewPort.getViewX(x)+SIZE, ViewPort.getViewY(y)+SIZE);

                glTexCoord2f(Renderer.getTextureFloat((texX*64), texture.getImageWidth()), Renderer.getTextureFloat((texY*64)+64, texture.getImageHeight()));  // Lower-Left
                glVertex2i(ViewPort.getViewX(x), ViewPort.getViewY(y)+SIZE);
            } else if (this.equals(Tile.STONEFLOOR)) {
                glTexCoord2f(Renderer.getTextureFloat(256, texture.getImageWidth()), Renderer.getTextureFloat(128, texture.getImageHeight()));  // Upper-Left
                glVertex2i(ViewPort.getViewX(x), ViewPort.getViewY(y));

                glTexCoord2f(Renderer.getTextureFloat(256+64, texture.getImageWidth()), Renderer.getTextureFloat(128, texture.getImageHeight()));  // Upper-Right
                glVertex2i(ViewPort.getViewX(x)+SIZE, ViewPort.getViewY(y));

                glTexCoord2f(Renderer.getTextureFloat(256+64, texture.getImageWidth()), Renderer.getTextureFloat(128+64, texture.getImageHeight()));  // Lower-Right
                glVertex2i(ViewPort.getViewX(x)+SIZE, ViewPort.getViewY(y)+SIZE);

                glTexCoord2f(Renderer.getTextureFloat(256, texture.getImageWidth()), Renderer.getTextureFloat(128+64, texture.getImageHeight()));  // Lower-Left
                glVertex2i(ViewPort.getViewX(x), ViewPort.getViewY(y)+SIZE);
            } else {
//                glTexCoord2f(Renderer.getTextureFloat(240, texture.getImageWidth()), Renderer.getTextureFloat(240, texture.getImageHeight()));  // Upper-Left
//                glVertex2i(ViewPort.getViewX(x), ViewPort.getViewY(y));
//
//                glTexCoord2f(Renderer.getTextureFloat(240+16, texture.getImageWidth()), Renderer.getTextureFloat(240, texture.getImageHeight()));  // Upper-Right
//                glVertex2i(ViewPort.getViewX(x)+SIZE, ViewPort.getViewY(y));
//
//                glTexCoord2f(Renderer.getTextureFloat(240+16, texture.getImageWidth()), Renderer.getTextureFloat(240+16, texture.getImageHeight()));  // Lower-Right
//                glVertex2i(ViewPort.getViewX(x)+SIZE, ViewPort.getViewY(y)+SIZE);
//
//                glTexCoord2f(Renderer.getTextureFloat(240, texture.getImageWidth()), Renderer.getTextureFloat(240+16, texture.getImageHeight()));  // Lower-Left
//                glVertex2i(ViewPort.getViewX(x), ViewPort.getViewY(y)+SIZE);
                glTexCoord2f(Renderer.getTextureFloat(256, texture.getImageWidth()), Renderer.getTextureFloat(128, texture.getImageHeight()));  // Upper-Left
                glVertex2i(ViewPort.getViewX(x), ViewPort.getViewY(y));

                glTexCoord2f(Renderer.getTextureFloat(256+64, texture.getImageWidth()), Renderer.getTextureFloat(128, texture.getImageHeight()));  // Upper-Right
                glVertex2i(ViewPort.getViewX(x)+SIZE, ViewPort.getViewY(y));

                glTexCoord2f(Renderer.getTextureFloat(256+64, texture.getImageWidth()), Renderer.getTextureFloat(128+64, texture.getImageHeight()));  // Lower-Right
                glVertex2i(ViewPort.getViewX(x)+SIZE, ViewPort.getViewY(y)+SIZE);

                glTexCoord2f(Renderer.getTextureFloat(256, texture.getImageWidth()), Renderer.getTextureFloat(128+64, texture.getImageHeight()));  // Lower-Left
                glVertex2i(ViewPort.getViewX(x), ViewPort.getViewY(y)+SIZE);
            }
            glEnd();
            
//            if (this.hover) {
//                glDisable(GL_TEXTURE_2D);
//                glColor4f(0.3f,0.3f,0.0f,0.3f);
//                glBegin(GL_QUADS);
//                glVertex2i(ViewPort.getViewX(rect.x), ViewPort.getViewY(rect.y));
//                glVertex2i(ViewPort.getViewX(rect.x)+rect.width, ViewPort.getViewY(rect.y));
//                glVertex2i(ViewPort.getViewX(rect.x)+rect.width, ViewPort.getViewY(rect.y)+rect.height);
//                glVertex2i(ViewPort.getViewX(rect.x), ViewPort.getViewY(rect.y)+rect.height);
//                glEnd();
//                glColor4f(1.0f,1.0f,1.0f,1.0f);
//                glEnable(GL_TEXTURE_2D);
//                hover = false;
//            }
        }
    }
    
}
