
package com.challengercity.datura;

import java.util.ArrayList;
import static org.lwjgl.opengl.GL11.*;
import org.newdawn.slick.opengl.Texture;

/**
 *
 * @author Ben Sergent V/ha1fBit
 */
public class GUITab extends GUI {
    
    private static Texture texture;
    private ArrayList<GUI> renderList = new ArrayList<GUI>();

    public GUITab(int x, int y, int width, int height, int picX, int picY) {
        super(x, y, width, height, picX*64, picY*64, 64, 64);
    }

    @Override
    public void checkMouse() {
        for (int i = 0; i<renderList.size(); i++) {
            renderList.get(i).checkMouse();
        }
    }

    @Override
    public void draw() {
        for (int i = 0; i<renderList.size(); i++) {
            glColor4f(1.0f,1.0f,1.0f,1.0f);
            renderList.get(i).draw();
        }
    }
    
    public void drawIcon(int x, int y, int width, int height) {
        if (texture == null) {
            texture = ResourcePool.getTexture("gui_tab_icons", ".png");
        }
        texture.bind();

        glBegin(GL_QUADS);
            /* Draw Texture */
            glTexCoord2f(Renderer.getTextureFloat(picX, texture.getImageWidth()), Renderer.getTextureFloat(picY, texture.getImageHeight()));  // Upper-Left
            glVertex2i(x, y);

            glTexCoord2f(Renderer.getTextureFloat(picX+picWidth, texture.getImageWidth()), Renderer.getTextureFloat(picY, texture.getImageHeight()));  // Upper-Right
            glVertex2i(x+width, y);

            glTexCoord2f(Renderer.getTextureFloat(picX+picWidth, texture.getImageWidth()), Renderer.getTextureFloat(picY+picHeight, texture.getImageHeight()));  // Lower-Right
            glVertex2i(x+width, y+height);

            glTexCoord2f(Renderer.getTextureFloat(picX, texture.getImageWidth()), Renderer.getTextureFloat(picY+picHeight, texture.getImageHeight()));  // Lower-Left
            glVertex2i(x, y+height);
        glEnd();
    }
    
    public ArrayList getRenderList() {
        return renderList;
    }
    
    public void addToRenderList(RenderableObject ro) {
        if (ro instanceof GUI) {
            renderList.add((GUI) ro);
        } else {
            Datura.log(GUITab.class, "Failed to add GUI to render list, "+ro.getClass());
        }
    }
    
    public void removeFromRenderList(RenderableObject ro) {
        renderList.remove((GUI) ro);
    }

    @Override
    public void delete() {
        // Do nothing
    }

}
