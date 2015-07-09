
package com.challengercity.datura;

import static com.challengercity.datura.GUIText.font48;
import org.newdawn.slick.TrueTypeFont;

/**
 *
 * @author Ben Sergent V/ha1fBit
 */
public class ScreenGame extends Screen {

    private Room currentRoom;
    public static boolean requestingRoom = false;
    public GUIChat chatWindow;
    public static boolean paused = false;
    
    public ScreenGame() {
        startup();
    }
    
    public void setCurrentRoom(Room rm) {
        this.currentRoom = rm;
    }
    
    public Room getCurrentRoom() {
        return this.currentRoom;
    }
    
    private void startup() {
        GUI b;
        b = new GUIButton(Datura.screenWidth-100, 0, 100, 40, 99, StringHandler.getString("menu_game_leave"), 24);
        addToRenderList(b);
        b = new GUITextFPS(1, 1, 12, false);
        addToRenderList(b);
        b = new GUITextPing(1, 16, 12, false);
        addToRenderList(b);
        b = new GUITextRoom(1, 31, 12, false);
        addToRenderList(b);
        b = new GUITextX(1, 46, 12, false);
        addToRenderList(b);
        b = new GUITextY(1, 61, 12, false);
        addToRenderList(b);
        b = new GUITextDelta(1, 76, 12, false);
        addToRenderList(b);
        chatWindow = new GUIChat(Datura.screenWidth/2-440,0,880,160);
        addToRenderList(chatWindow);
        b = new GUIChatInput(0, Datura.screenHeight-16, 860, 16, 75, 1);
        addToRenderList(b);
        b = new GUIGameMenu(Datura.screenWidth-400, Datura.screenHeight-250);
        addToRenderList(b);
        MPClient.connectMP();
    }
    
    @Override
    public void actionPerformed(int actionId) {
        if (actionId==99) {
            MPClient.disconnectMP();
            Datura.setScreen(new ScreenDisconnected(StringHandler.getString("server_disconnect")));
        }
    }
    
    @Override
    public void render() {
        if (currentRoom != null) {
            currentRoom.draw();
        }
        super.render(); // Render GUI after
    }

    @Override
    public void tick(long delta) {
        if (currentRoom != null) {
            currentRoom.tick(delta);
        }
    }
    
    public class GUITextFPS extends GUIText {
        public GUITextFPS (int x, int y, int fontSize, boolean centered) {
            super(x, y, "Blank", fontSize, centered);
        }
        
        public TrueTypeFont font;
        
        @Override
        public void draw() {
            if (visible) {
                label = "FPS: "+Datura.currentFPS;
                if (font48 == null || font36 == null || font24 == null || font16 == null || font12 == null) {
                    loadFonts();
                }
                switch (fontSize) {
                    case 48:
                        font = font48;
                        break;
                    case 36:
                        font = font36;
                        break;
                    case 24:
                        font = font24;
                        break;
                    case 16:
                        font = font16;
                        break;
                    case 12:
                        font = font12;
                        break;
                }
                int strPosX = posX;
                int strPosY = posY;
                if (centered) {
                    strPosX = posX+width/2-(font.getWidth(label)/2);
                    strPosY = posY+height/2-(font.getHeight(label)/2);
                }
                font.drawString(strPosX, strPosY, label);
            }
        }
    }
    
    public class GUITextPing extends GUIText {
        public GUITextPing (int x, int y, int fontSize, boolean centered) {
            super(x, y, "Blank", fontSize, centered);
        }
        
        public TrueTypeFont font;
        
        @Override
        public void draw() {
            if (visible) {
                label = "Ping: "+Datura.lastPing+"ms";
                if (font48 == null || font36 == null || font24 == null || font16 == null || font12 == null) {
                    loadFonts();
                }
                switch (fontSize) {
                    case 48:
                        font = font48;
                        break;
                    case 36:
                        font = font36;
                        break;
                    case 24:
                        font = font24;
                        break;
                    case 16:
                        font = font16;
                        break;
                    case 12:
                        font = font12;
                        break;
                }
                int strPosX = posX;
                int strPosY = posY;
                if (centered) {
                    strPosX = posX+width/2-(font.getWidth(label)/2);
                    strPosY = posY+height/2-(font.getHeight(label)/2);
                }
                font.drawString(strPosX, strPosY, label);
            }
        }
    }
    
    public class GUITextRoom extends GUIText {
        public GUITextRoom (int x, int y, int fontSize, boolean centered) {
            super(x, y, "Blank", fontSize, centered);
        }
        
        public TrueTypeFont font;
        
        @Override
        public void draw() {
            if (visible) {
                label = "Room: "+(Datura.getCurrentRoom()!=null?Datura.getCurrentRoom().getName():"None");
                if (font48 == null || font36 == null || font24 == null || font16 == null || font12 == null) {
                    loadFonts();
                }
                switch (fontSize) {
                    case 48:
                        font = font48;
                        break;
                    case 36:
                        font = font36;
                        break;
                    case 24:
                        font = font24;
                        break;
                    case 16:
                        font = font16;
                        break;
                    case 12:
                        font = font12;
                        break;
                }
                int strPosX = posX;
                int strPosY = posY;
                if (centered) {
                    strPosX = posX+width/2-(font.getWidth(label)/2);
                    strPosY = posY+height/2-(font.getHeight(label)/2);
                }
                font.drawString(strPosX, strPosY, label);
            }
        }
    }
    
    public class GUITextX extends GUIText {
        public GUITextX (int x, int y, int fontSize, boolean centered) {
            super(x, y, "Blank", fontSize, centered);
        }
        
        public TrueTypeFont font;
        
        @Override
        public void draw() {
            if (visible) {
                try {
                    label = "X: "+(Datura.getCurrentRoom()!=null?Datura.getCurrentRoom().getPlayerFromId(MPClient.getOwnId()).posX:0);
                } catch (Exception ex) {
                    label = "X: 0";
                }
                if (font48 == null || font36 == null || font24 == null || font16 == null || font12 == null) {
                    loadFonts();
                }
                switch (fontSize) {
                    case 48:
                        font = font48;
                        break;
                    case 36:
                        font = font36;
                        break;
                    case 24:
                        font = font24;
                        break;
                    case 16:
                        font = font16;
                        break;
                    case 12:
                        font = font12;
                        break;
                }
                int strPosX = posX;
                int strPosY = posY;
                if (centered) {
                    strPosX = posX+width/2-(font.getWidth(label)/2);
                    strPosY = posY+height/2-(font.getHeight(label)/2);
                }
                font.drawString(strPosX, strPosY, label);
            }
        }
    }
    
    public class GUITextY extends GUIText {
        public GUITextY (int x, int y, int fontSize, boolean centered) {
            super(x, y, "Blank", fontSize, centered);
        }
        
        public TrueTypeFont font;
        
        @Override
        public void draw() {
            if (visible) {
                try {
                    label = "Y: "+(Datura.getCurrentRoom()!=null?Datura.getCurrentRoom().getPlayerFromId(MPClient.getOwnId()).posY:0);
                } catch (Exception ex) {
                    label = "Y: 0";
                }
                if (font48 == null || font36 == null || font24 == null || font16 == null || font12 == null) {
                    loadFonts();
                }
                switch (fontSize) {
                    case 48:
                        font = font48;
                        break;
                    case 36:
                        font = font36;
                        break;
                    case 24:
                        font = font24;
                        break;
                    case 16:
                        font = font16;
                        break;
                    case 12:
                        font = font12;
                        break;
                }
                int strPosX = posX;
                int strPosY = posY;
                if (centered) {
                    strPosX = posX+width/2-(font.getWidth(label)/2);
                    strPosY = posY+height/2-(font.getHeight(label)/2);
                }
                font.drawString(strPosX, strPosY, label);
            }
        }
    }
    
    public class GUITextDelta extends GUIText {
        public GUITextDelta (int x, int y, int fontSize, boolean centered) {
            super(x, y, "Blank", fontSize, centered);
        }
        
        public TrueTypeFont font;
        
        @Override
        public void draw() {
            if (visible) {
                try {
                    label = "Delta: "+Datura.lastDelta;
                } catch (Exception ex) {
                    label = "Delta: 0";
                }
                if (font48 == null || font36 == null || font24 == null || font16 == null || font12 == null) {
                    loadFonts();
                }
                switch (fontSize) {
                    case 48:
                        font = font48;
                        break;
                    case 36:
                        font = font36;
                        break;
                    case 24:
                        font = font24;
                        break;
                    case 16:
                        font = font16;
                        break;
                    case 12:
                        font = font12;
                        break;
                }
                int strPosX = posX;
                int strPosY = posY;
                if (centered) {
                    strPosX = posX+width/2-(font.getWidth(label)/2);
                    strPosY = posY+height/2-(font.getHeight(label)/2);
                }
                font.drawString(strPosX, strPosY, label);
            }
        }
    }
    
}
