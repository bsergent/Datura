
package com.challengercity.datura;

import com.challengercity.datura.network.PacketChat;
import org.lwjgl.input.Keyboard;
import static org.lwjgl.opengl.GL11.*;
import org.newdawn.slick.Color;
import org.newdawn.slick.TrueTypeFont;

/**
 *
 * @author Ben Sergent V at http://sergenttech.net/
 */
public class GUIChatInput extends GUI {
    
    private static TrueTypeFont font;
    private int fontSize = 16;
    public int actionId;
    private int cursorCoolDown = 20;
    private int charLimit = 20;
    public String text = "";
    
    public GUIChatInput(int x, int y, int width, int height, int charLimit, int actionId) {
        super(x, y, width, height, 0, 0, 0, 0);
        this.visible = false;
        this.charLimit = charLimit;
        this.fontSize = height-2;
        this.actionId = actionId;
        Controller.addListenerKeyboard(new ListenerKeyboard() {

            @Override
            public void keyDown(int key) {
                if (GUIChatInput.this != null) {
                    GUIChatInput.this.keyDown(key);
                }
            }
            
        });
    }

    @Override
    public void draw() {
        if (visible) {
            if (font == null) {
                font = ResourcePool.getFont("Courier", fontSize);
            }
            /* Draw Left Section */
            glDisable(GL_TEXTURE_2D);
            glColor4f(0,0,0,0.5f);
            glBegin(GL_QUADS);
                glVertex3f(posX,posY,0);
                glVertex3f(posX+width,posY,0);
                glVertex3f(posX+width,posY+height,0);
                glVertex3f(posX,posY+height,0);
            glEnd();
            glEnable(GL_TEXTURE_2D);
            if (cursorCoolDown <= 0) {
                //int strPosX = posX+width/2-(font.getWidth(text+"_")/2);
                //int strPosY = posY+height/2-(font.getHeight(text+"_")/2);
                font.drawString(posX+1, posY+1, ">"+text+"_", new Color(60,223,52));
                if (cursorCoolDown <= -30) {
                    cursorCoolDown = 30;
                }
            } else {
                //int strPosX = posX+width/2-(font.getWidth(text+"  ")/2);
                //int strPosY = posY+height/2-(font.getHeight(text+"  ")/2);
                font.drawString(posX+1, posY+1, ">"+text+"  ", new Color(60,223,52));
            }
            glColor4f(1,1,1,1);
        }
    }

    @Override
    public void checkMouse() {
        Datura.guiCooldown--;
        cursorCoolDown--;
    }
    
    public void keyDown(int key) {
        if (visible) {
            if (key == Keyboard.KEY_ESCAPE) {
                visible = false;
                ScreenGame.paused = false;
                text = "";
            }
            if (key == Keyboard.KEY_RETURN) {
                visible = false;
                ScreenGame.paused = false;
                if (!text.equals("")) {
                    MPClient.sendTCPPacket(new PacketChat("["+Datura.getUsername()+"] "+text));
                    text = "";
                }
            }
            if (key == Keyboard.KEY_BACK) {
                if (text.length() > 0) {
                    text = text.subSequence(0, text.length()-1).toString();
                }
            }
            if (text.length() < charLimit) {
                if (key == Keyboard.KEY_A) {
                    if (Keyboard.isKeyDown(Keyboard.KEY_LSHIFT) || Keyboard.isKeyDown(Keyboard.KEY_RSHIFT)) {
                        text = text+"A";
                    } else {
                        text = text+"a";
                    }
                }
                if (key == Keyboard.KEY_B) {
                    if (Keyboard.isKeyDown(Keyboard.KEY_LSHIFT) || Keyboard.isKeyDown(Keyboard.KEY_RSHIFT)) {
                        text = text+"B";
                    } else {
                        text = text+"b";
                    }
                }
                if (key == Keyboard.KEY_C) {
                    if (Keyboard.isKeyDown(Keyboard.KEY_LSHIFT) || Keyboard.isKeyDown(Keyboard.KEY_RSHIFT)) {
                        text = text+"C";
                    } else {
                        text = text+"c";
                    }

                }
                if (key == Keyboard.KEY_D) {
                    if (Keyboard.isKeyDown(Keyboard.KEY_LSHIFT) || Keyboard.isKeyDown(Keyboard.KEY_RSHIFT)) {
                        text = text+"D";
                    } else {
                        text = text+"d";
                    }

                }
                if (key == Keyboard.KEY_E) {
                    if (Keyboard.isKeyDown(Keyboard.KEY_LSHIFT) || Keyboard.isKeyDown(Keyboard.KEY_RSHIFT)) {
                        text = text+"E";
                    } else {
                        text = text+"e";
                    }

                }
                if (key == Keyboard.KEY_F) {
                    if (Keyboard.isKeyDown(Keyboard.KEY_LSHIFT) || Keyboard.isKeyDown(Keyboard.KEY_RSHIFT)) {
                        text = text+"F";
                    } else {
                        text = text+"f";
                    }

                }
                if (key == Keyboard.KEY_G) {
                    if (Keyboard.isKeyDown(Keyboard.KEY_LSHIFT) || Keyboard.isKeyDown(Keyboard.KEY_RSHIFT)) {
                        text = text+"G";
                    } else {
                        text = text+"g";
                    }

                }
                if (key == Keyboard.KEY_H) {
                    if (Keyboard.isKeyDown(Keyboard.KEY_LSHIFT) || Keyboard.isKeyDown(Keyboard.KEY_RSHIFT)) {
                        text = text+"H";
                    } else {
                        text = text+"h";
                    }

                }
                if (key == Keyboard.KEY_I) {
                    if (Keyboard.isKeyDown(Keyboard.KEY_LSHIFT) || Keyboard.isKeyDown(Keyboard.KEY_RSHIFT)) {
                        text = text+"I";
                    } else {
                        text = text+"i";
                    }

                }
                if (key == Keyboard.KEY_J) {
                    if (Keyboard.isKeyDown(Keyboard.KEY_LSHIFT) || Keyboard.isKeyDown(Keyboard.KEY_RSHIFT)) {
                        text = text+"J";
                    } else {
                        text = text+"j";
                    }

                }
                if (key == Keyboard.KEY_K) {
                    if (Keyboard.isKeyDown(Keyboard.KEY_LSHIFT) || Keyboard.isKeyDown(Keyboard.KEY_RSHIFT)) {
                        text = text+"K";
                    } else {
                        text = text+"k";
                    }

                }
                if (key == Keyboard.KEY_L) {
                    if (Keyboard.isKeyDown(Keyboard.KEY_LSHIFT) || Keyboard.isKeyDown(Keyboard.KEY_RSHIFT)) {
                        text = text+"L";
                    } else {
                        text = text+"l";
                    }

                }
                if (key == Keyboard.KEY_M) {
                    if (Keyboard.isKeyDown(Keyboard.KEY_LSHIFT) || Keyboard.isKeyDown(Keyboard.KEY_RSHIFT)) {
                        text = text+"M";
                    } else {
                        text = text+"m";
                    }

                }
                if (key == Keyboard.KEY_N) {
                    if (Keyboard.isKeyDown(Keyboard.KEY_LSHIFT) || Keyboard.isKeyDown(Keyboard.KEY_RSHIFT)) {
                        text = text+"N";
                    } else {
                        text = text+"n";
                    }

                }
                if (key == Keyboard.KEY_O) {
                    if (Keyboard.isKeyDown(Keyboard.KEY_LSHIFT) || Keyboard.isKeyDown(Keyboard.KEY_RSHIFT)) {
                        text = text+"O";
                    } else {
                        text = text+"o";
                    }

                }
                if (key == Keyboard.KEY_P) {
                    if (Keyboard.isKeyDown(Keyboard.KEY_LSHIFT) || Keyboard.isKeyDown(Keyboard.KEY_RSHIFT)) {
                        text = text+"P";
                    } else {
                        text = text+"p";
                    }

                }
                if (key == Keyboard.KEY_Q) {
                    if (Keyboard.isKeyDown(Keyboard.KEY_LSHIFT) || Keyboard.isKeyDown(Keyboard.KEY_RSHIFT)) {
                        text = text+"Q";
                    } else {
                        text = text+"q";
                    }

                }
                if (key == Keyboard.KEY_R) {
                    if (Keyboard.isKeyDown(Keyboard.KEY_LSHIFT) || Keyboard.isKeyDown(Keyboard.KEY_RSHIFT)) {
                        text = text+"R";
                    } else {
                        text = text+"r";
                    }

                }
                if (key == Keyboard.KEY_S) {
                    if (Keyboard.isKeyDown(Keyboard.KEY_LSHIFT) || Keyboard.isKeyDown(Keyboard.KEY_RSHIFT)) {
                        text = text+"S";
                    } else {
                        text = text+"s";
                    }

                }
                if (key == Keyboard.KEY_T) {
                    if (Keyboard.isKeyDown(Keyboard.KEY_LSHIFT) || Keyboard.isKeyDown(Keyboard.KEY_RSHIFT)) {
                        text = text+"T";
                    } else {
                        text = text+"t";
                    }

                }
                if (key == Keyboard.KEY_U) {
                    if (Keyboard.isKeyDown(Keyboard.KEY_LSHIFT) || Keyboard.isKeyDown(Keyboard.KEY_RSHIFT)) {
                        text = text+"U";
                    } else {
                        text = text+"u";
                    }

                }
                if (key == Keyboard.KEY_V) {
                    if (Keyboard.isKeyDown(Keyboard.KEY_LSHIFT) || Keyboard.isKeyDown(Keyboard.KEY_RSHIFT)) {
                        text = text+"V";
                    } else {
                        text = text+"v";
                    }

                }
                if (key == Keyboard.KEY_W) {
                    if (Keyboard.isKeyDown(Keyboard.KEY_LSHIFT) || Keyboard.isKeyDown(Keyboard.KEY_RSHIFT)) {
                        text = text+"W";
                    } else {
                        text = text+"w";
                    }

                }
                if (key == Keyboard.KEY_X) {
                    if (Keyboard.isKeyDown(Keyboard.KEY_LSHIFT) || Keyboard.isKeyDown(Keyboard.KEY_RSHIFT)) {
                        text = text+"X";
                    } else {
                        text = text+"x";
                    }

                }
                if (key == Keyboard.KEY_Y) {
                    if (Keyboard.isKeyDown(Keyboard.KEY_LSHIFT) || Keyboard.isKeyDown(Keyboard.KEY_RSHIFT)) {
                        text = text+"Y";
                    } else {
                        text = text+"y";
                    }

                }
                if (key == Keyboard.KEY_Z) {
                    if (Keyboard.isKeyDown(Keyboard.KEY_LSHIFT) || Keyboard.isKeyDown(Keyboard.KEY_RSHIFT)) {
                        text = text+"Z";
                    } else {
                        text = text+"z";
                    }

                }
                if (key == Keyboard.KEY_0) {
                    if (Keyboard.isKeyDown(Keyboard.KEY_LSHIFT) || Keyboard.isKeyDown(Keyboard.KEY_RSHIFT)) {
                        text = text+")";
                    } else {
                        text = text+"0";
                    }

                }
                if (key == Keyboard.KEY_1) {
                    if (Keyboard.isKeyDown(Keyboard.KEY_LSHIFT) || Keyboard.isKeyDown(Keyboard.KEY_RSHIFT)) {
                        text = text+"!";
                    } else {
                        text = text+"1";
                    }

                }
                if (key == Keyboard.KEY_2) {
                    if (Keyboard.isKeyDown(Keyboard.KEY_LSHIFT) || Keyboard.isKeyDown(Keyboard.KEY_RSHIFT)) {
                        text = text+"@";
                    } else {
                        text = text+"2";
                    }

                }
                if (key == Keyboard.KEY_3) {
                    if (Keyboard.isKeyDown(Keyboard.KEY_LSHIFT) || Keyboard.isKeyDown(Keyboard.KEY_RSHIFT)) {
                        text = text+"#";
                    } else {
                        text = text+"3";
                    }

                }
                if (key == Keyboard.KEY_4) {
                    if (Keyboard.isKeyDown(Keyboard.KEY_LSHIFT) || Keyboard.isKeyDown(Keyboard.KEY_RSHIFT)) {
                        text = text+"$";
                    } else {
                        text = text+"4";
                    }

                }
                if (key == Keyboard.KEY_5) {
                    if (Keyboard.isKeyDown(Keyboard.KEY_LSHIFT) || Keyboard.isKeyDown(Keyboard.KEY_RSHIFT)) {
                        text = text+"%";
                    } else {
                        text = text+"5";
                    }

                }
                if (key == Keyboard.KEY_6) {
                    if (Keyboard.isKeyDown(Keyboard.KEY_LSHIFT) || Keyboard.isKeyDown(Keyboard.KEY_RSHIFT)) {
                        text = text+"^";
                    } else {
                        text = text+"6";
                    }

                }
                if (key == Keyboard.KEY_7) {
                    if (Keyboard.isKeyDown(Keyboard.KEY_LSHIFT) || Keyboard.isKeyDown(Keyboard.KEY_RSHIFT)) {
                        text = text+"&";
                    } else {
                        text = text+"7";
                    }

                }
                if (key == Keyboard.KEY_8) {
                    if (Keyboard.isKeyDown(Keyboard.KEY_LSHIFT) || Keyboard.isKeyDown(Keyboard.KEY_RSHIFT)) {
                        text = text+"*";
                    } else {
                        text = text+"8";
                    }

                }
                if (key == Keyboard.KEY_9) {
                    if (Keyboard.isKeyDown(Keyboard.KEY_LSHIFT) || Keyboard.isKeyDown(Keyboard.KEY_RSHIFT)) {
                        text = text+"(";
                    } else {
                        text = text+"9";
                    }

                }
                if (key == Keyboard.KEY_PERIOD || key == Keyboard.KEY_DECIMAL) {
                    if (Keyboard.isKeyDown(Keyboard.KEY_LSHIFT) || Keyboard.isKeyDown(Keyboard.KEY_RSHIFT)) {
                        text = text+">";
                    } else {
                        text = text+".";
                    }

                }
                if (key == Keyboard.KEY_COMMA) {
                    if (Keyboard.isKeyDown(Keyboard.KEY_LSHIFT) || Keyboard.isKeyDown(Keyboard.KEY_RSHIFT)) {
                        text = text+"<";
                    } else {
                        text = text+",";
                    }

                }
                if (key == 0 || key == Keyboard.KEY_APOSTROPHE) {
                    if (Keyboard.isKeyDown(Keyboard.KEY_LSHIFT) || Keyboard.isKeyDown(Keyboard.KEY_RSHIFT)) {
                        text = text+"\"";
                    } else {
                        text = text+"'";
                    }

                }
                if (key == Keyboard.KEY_SEMICOLON) {
                    if (Keyboard.isKeyDown(Keyboard.KEY_LSHIFT) || Keyboard.isKeyDown(Keyboard.KEY_RSHIFT)) {
                        text = text+":";
                    } else {
                        text = text+";";
                    }

                }
                if (key == Keyboard.KEY_SPACE) {
                    text = text+" ";

                }
                if (key == Keyboard.KEY_SLASH) {
                    if (Keyboard.isKeyDown(Keyboard.KEY_LSHIFT) || Keyboard.isKeyDown(Keyboard.KEY_RSHIFT)) {
                        text = text+"?";
                    } else {
                        text = text+"/";
                    }

                }
                if (key == Keyboard.KEY_MINUS) {
                    if (Keyboard.isKeyDown(Keyboard.KEY_LSHIFT) || Keyboard.isKeyDown(Keyboard.KEY_RSHIFT)) {
                        text = text+"_";
                    } else {
                        text = text+"-";
                    }

                }
                if (key == Keyboard.KEY_EQUALS) {
                    if (Keyboard.isKeyDown(Keyboard.KEY_LSHIFT) || Keyboard.isKeyDown(Keyboard.KEY_RSHIFT)) {
                        text = text+"+";
                    } else {
                        text = text+"=";
                    }

                }
                if (key == Keyboard.KEY_LBRACKET) {
                    if (Keyboard.isKeyDown(Keyboard.KEY_LSHIFT) || Keyboard.isKeyDown(Keyboard.KEY_RSHIFT)) {
                        text = text+"{";
                    } else {
                        text = text+"[";
                    }

                }
                if (key == Keyboard.KEY_RBRACKET) {
                    if (Keyboard.isKeyDown(Keyboard.KEY_LSHIFT) || Keyboard.isKeyDown(Keyboard.KEY_RSHIFT)) {
                        text = text+"}";
                    } else {
                        text = text+"]";
                    }

                }
                if (key == Keyboard.KEY_BACKSLASH) {
                    if (Keyboard.isKeyDown(Keyboard.KEY_LSHIFT) || Keyboard.isKeyDown(Keyboard.KEY_RSHIFT)) {
                        text = text+"|";
                    } else {
                        text = text+"\\";
                    }

                }
            }
            Datura.currentScreen.actionPerformed(actionId);
        } else {
            if (key == Keyboard.KEY_T || key == Keyboard.KEY_RETURN) {
                visible = true;
                ScreenGame.paused = true;
            }
        }
    }

    @Override
    public void delete() {
        // Do nothing
    }
    
}
