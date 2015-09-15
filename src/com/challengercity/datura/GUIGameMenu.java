
package com.challengercity.datura;

import java.util.ArrayList;
import static org.lwjgl.opengl.GL11.*;
/**
 *
 * @author Ben Sergent V at http://sergenttech.net/
 */
public class GUIGameMenu extends GUI {
    
    private ArrayList<GUITab> tabs = new ArrayList<GUITab>();
    private int currentTab = 0;

    public GUIGameMenu(int x, int y) {
        super(x, y, 400, 250, 0, 0, 0, 0);
        tabs.add(new GUITabInventory(x+50,y,400-50,250));
        tabs.add(new GUITabLogout(x+50,y,400-50,250));
        Controller.addListenerMouse(new ListenerMouse() {

            @Override
            public void buttonDown(int key, int x, int y) {
                if (key==0&&x>=posX&&x<posX+50&&y>=posY&&y<=posY+height) { // If within tab bar
                    int tabSize = height/(tabs.isEmpty()?1:tabs.size());
                    for (int i = 0; i < tabs.size(); i++) {
                        if (x>=posX&&x<posX+50&&y>=posY+(i*tabSize)&&y<=posY+((i+1)*tabSize)) {
                            setTab(i);
                        }
                    }
                }
                if (key==0&&!visible&&x>=Datura.screenWidth-20&&x<Datura.screenWidth&&y>=Datura.screenHeight-50&&y<=Datura.screenHeight) { // If within hider when closed
                    visible = true;
                } else if (key==0&&visible&&x>=posX-20&&x<posX&&y>=posY+height-50&&y<=posY+height) { // If within hider when open
                    visible = false;
                }
            }
            
        });
    }

    @Override
    public void checkMouse() {
        for (int i = 0; i<tabs.size(); i++) {
            tabs.get(i).checkMouse();
        }
    }

    @Override
    public void draw() {
        if (visible) {
            glDisable(GL_TEXTURE_2D);

            // Background
            glColor4f(0,0,0,0.5f);
            glBegin(GL_QUADS);
                glVertex3f(posX,posY,0);
                glVertex3f(posX+width,posY,0);
                glVertex3f(posX+width,posY+height,0);
                glVertex3f(posX,posY+height,0);
            glEnd();
            glBegin(GL_QUADS);
                glVertex3f(posX-20,posY+height-50,0);
                glVertex3f(posX,posY+height-50,0);
                glVertex3f(posX,posY+height,0);
                glVertex3f(posX-20,posY+height,0);
            glEnd();

            // Main Outline
            glColor4f(0.235f,0.875f,0.204f,1.0f);
            glLineWidth(2.0f); 
            glBegin(GL_LINES);
                glVertex3f(posX, posY+height-50, 0);
                glVertex3f(posX-20, posY+height-50, 0);
            glEnd();
            glBegin(GL_LINES);
                glVertex3f(posX-20, posY+height-50, 0);
                glVertex3f(posX-20, posY+height, 0);
            glEnd();
            glBegin(GL_LINES);
                glVertex3f(posX-20, posY+height, 0);
                glVertex3f(posX, posY+height, 0);
            glEnd();

            glBegin(GL_LINES);
                glVertex3f(posX, posY, 0);
                glVertex3f(posX+width, posY, 0);
            glEnd();
            glBegin(GL_LINES);
                glVertex3f(posX+width, posY, 0);
                glVertex3f(posX+width, posY+height, 0);
            glEnd();
            glBegin(GL_LINES);
                glVertex3f(posX+width, posY+height, 0);
                glVertex3f(posX, posY+height, 0);
            glEnd();
            glBegin(GL_LINES);
                glVertex3f(posX, posY+height, 0);
                glVertex3f(posX, posY, 0);
            glEnd();

            // Tab Dividers
            glLineWidth(1.0f);
            glBegin(GL_LINES);
                glVertex3f(posX+50, posY, 0);
                glVertex3f(posX+50, posY+height, 0);
            glEnd();
            int tabSize = height/(tabs.isEmpty()?1:tabs.size());
            for (int i = 0; i < tabs.size(); i++) {
                if (currentTab == i) {
                    glColor4f(0.235f,0.875f,0.204f,0.2f);
                    glBegin(GL_QUADS);
                        glVertex3f(posX,posY+(tabSize*i),0);
                        glVertex3f(posX+50,posY+(tabSize*i),0);
                        glVertex3f(posX+50,posY+(tabSize*(i+1)),0);
                        glVertex3f(posX,posY+(tabSize*(i+1)),0);
                    glEnd();
                }
                glColor4f(0.235f,0.875f,0.204f,1.0f);
                glBegin(GL_LINES);
                    glVertex3f(posX, posY+(tabSize*i), 0);
                    glVertex3f(posX+50, posY+(tabSize*i), 0);
                glEnd();
                if (tabs.get(i) != null) {
                    glEnable(GL_TEXTURE_2D);
                    glColor4f(1,1,1,1);
                    int iconWidth = 48;
                    int iconHeight = 48;
                    int iconX = (50/2)-(iconWidth/2);
                    int iconY = ((i*tabSize)/2)+(((i+1)*tabSize)/2)-(iconHeight/2);
                    tabs.get(i).drawIcon(posX+iconX, posY+iconY, iconWidth, iconHeight);
                    glDisable(GL_TEXTURE_2D);
                }
            }

            glEnable(GL_TEXTURE_2D);
            glColor4f(1,1,1,1);
            if (currentTab < tabs.size() && tabs.get(currentTab) != null) {
                tabs.get(currentTab).draw();
            }
        } else {
            glDisable(GL_TEXTURE_2D);
            glColor4f(0,0,0,0.5f);
            glBegin(GL_QUADS);
                glVertex3f(Datura.screenWidth-20,Datura.screenHeight-50,0);
                glVertex3f(Datura.screenWidth,Datura.screenHeight-50,0);
                glVertex3f(Datura.screenWidth,Datura.screenHeight,0);
                glVertex3f(Datura.screenWidth-20,Datura.screenHeight,0);
            glEnd();
            glColor4f(0.235f,0.875f,0.204f,1.0f);
            glLineWidth(2.0f); 
            glBegin(GL_LINES);
                glVertex3f(Datura.screenWidth, Datura.screenHeight-50, 0);
                glVertex3f(Datura.screenWidth-20, Datura.screenHeight-50, 0);
            glEnd();
            glBegin(GL_LINES);
                glVertex3f(Datura.screenWidth-20, Datura.screenHeight-50, 0);
                glVertex3f(Datura.screenWidth-20, Datura.screenHeight, 0);
            glEnd();
            glBegin(GL_LINES);
                glVertex3f(Datura.screenWidth-20, Datura.screenHeight, 0);
                glVertex3f(Datura.screenWidth, Datura.screenHeight, 0);
            glEnd();
            glEnable(GL_TEXTURE_2D);
            glColor4f(1,1,1,1);
        }
    }

    @Override
    public void delete() {
        // Do nothing
    }
    
    public void setTab(int i) {
        currentTab = i;
    }

}
