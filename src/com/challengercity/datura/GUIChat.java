
package com.challengercity.datura;

import java.util.ArrayList;
import static org.lwjgl.opengl.GL11.*;
import org.newdawn.slick.Color;
import org.newdawn.slick.TrueTypeFont;

/**
 *
 * @author Ben Sergent V/ha1fBit
 */
public class GUIChat extends GUI {

    private ArrayList<String> pastChat = new ArrayList<String>();
    private ArrayList<Long> pastChatTimes = new ArrayList<Long>();
    private static final int MAXMSGS = 10;
    private static final int MSGTIMEOUT = 10000;
    protected static TrueTypeFont font12;

    public GUIChat(int x, int y, int width, int height) {
        super(x, y, width, height, 0, 0, 0, 0);
    }
    
    public void addChat(String str) {
        pastChat.add(str);
        pastChatTimes.add(System.currentTimeMillis());
        while (pastChat.size() >= MAXMSGS || pastChatTimes.size() >= MAXMSGS) {
            pastChat.remove(0);
            pastChatTimes.remove(0);
        }
    }

    @Override
    public void checkMouse() {
        for (int i = 0; i < pastChatTimes.size(); i++) {
            if (System.currentTimeMillis() - pastChatTimes.get(i) >= MSGTIMEOUT) {
                pastChatTimes.remove(i);
                pastChat.remove(i);
            }
        }
    }

    @Override
    public void draw() {
        if (font12 == null) {
            font12 = ResourcePool.getFont("Courier", 16);
        }
        glDisable(GL_TEXTURE_2D);
        glColor4f(0,0,0,0.5f);
        glBegin(GL_QUADS);
            glVertex3f(posX,
                    posY,
                    0);
            glVertex3f(posX+width,
                    posY,
                    0);
            glVertex3f(posX+width,
                    posY+((height/MAXMSGS)*(pastChat.size())),
                    0);
            glVertex3f(posX,
                    posY+((height/MAXMSGS)*(pastChat.size())),
                    0);
        glEnd();
        for (int i = 0; i < pastChat.size(); i++) {
            font12.drawString(posX,posY+((height/MAXMSGS)*i),pastChat.get(i), new Color(60,223,52));
        }
        glColor4f(1,1,1,1);
        glEnable(GL_TEXTURE_2D);
    }

    @Override
    public void delete() {
        //throw new UnsupportedOperationException("Not supported yet.");
    }
    
}
