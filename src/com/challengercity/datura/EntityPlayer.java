
package com.challengercity.datura;

import com.challengercity.datura.network.PacketEntityPosition;
import com.challengercity.datura.network.PacketRoomRequest;
import org.lwjgl.input.Keyboard;
import static org.lwjgl.opengl.GL11.*;
import org.newdawn.slick.opengl.Texture;
/**
 *
 * @author Ben Sergent V at http://sergenttech.net/
 */
public class EntityPlayer extends EntityLiving {

    private String username;
    private int playerId;
    private byte speed = 4;
    private byte ani = 0;
    private byte aniDelay;
    private Item[] inventory; // 23 Long
    private static byte maxAniDelay = 28;
    private static Texture texture;
    private long lastNetworkUpdate = 0;
    private static final long NETUPINT = 100; // 10xSecond
    //private int targetX;
    //private int targetY;

    public EntityPlayer() {
    }
    
    public EntityPlayer(int x, int y, int playerId, int entityId, String username) {
        super(x, y, entityId);
        this.playerId = playerId;
        this.username = username;
    }
    
    public int getPlayerId() {
        return playerId;
    }

    public String getUsername() {
        return username;
    }

    public Item[] getInventory() {
        return inventory;
    }
    
    @Override
    public void tick(long delta) { // TODO Make possible for dif speeds by applying to grid with no keyboard and within 1 speed increment
        for (int i = 0; i < delta/(1000/60); i++) { // TODO Make deltas more precise
            if(posX % Tile.SIZE == 0 && posY % Tile.SIZE == 0) { // If on the grid, update movement
                if (playerId == MPClient.getOwnId()) {
                    motionX = 0;
                    motionY = 0;
                }
                if (playerId == MPClient.getOwnId()) { // If this is the client, allow input
                    if (Controller.isKeyDown(Keyboard.KEY_LSHIFT) && !ScreenGame.paused) {
                        speed = 8; // TODO Add sprint cooldown
                    } else {
                        speed = 4;
                    }
                    if ((Controller.isKeyDown(Keyboard.KEY_A) || Controller.isKeyDown(Keyboard.KEY_LEFT)) && !ScreenGame.paused) {
                        motionX = (byte) -speed;
                        motionY = 0;
                        facing=0;
                    }
                    if ((Controller.isKeyDown(Keyboard.KEY_D) || Controller.isKeyDown(Keyboard.KEY_RIGHT)) && !ScreenGame.paused) {
                        motionX = speed;
                        motionY = 0;
                        facing=2;
                    }
                    if ((Controller.isKeyDown(Keyboard.KEY_W) || Controller.isKeyDown(Keyboard.KEY_UP)) && !ScreenGame.paused) {
                        motionX = 0;
                        motionY = (byte) -speed;
                        facing=1;
                    }
                    if ((Controller.isKeyDown(Keyboard.KEY_S) || Controller.isKeyDown(Keyboard.KEY_DOWN)) && !ScreenGame.paused) {
                        motionX = 0;
                        motionY = speed;
                        facing=3;
                    }
                }
            }
            Tile t = ((ScreenGame)Datura.currentScreen).getCurrentRoom().getTileAt(
                        motionX>0?posX+width-1+motionX:posX+motionX, 
                        motionY>0?posY+height-1+motionY:posY+motionY);
            Door d = ((ScreenGame)Datura.currentScreen).getCurrentRoom().getDoorAt(
                        motionX>0?posX+width-1+motionX:posX+motionX, 
                        motionY>0?posY+height-1+motionY:posY+motionY);
            if (d != null && playerId == MPClient.getOwnId() && !ScreenGame.requestingRoom) {
                MPClient.sendTCPPacket(new PacketRoomRequest(d));
                ScreenGame.requestingRoom = true;
    //            if (Datura.currentScreen instanceof ScreenGame) {
    //                ((ScreenGame) Datura.currentScreen).setCurrentRoom(null);
    //            }
            }
            if (t != null && !t.isCollidable()) {
                posX += motionX;
                posY += motionY;
            }
            if (playerId == MPClient.getOwnId()) {
                ViewPort.centerView(posX+(width/2), posY+(height/2));
                if (System.currentTimeMillis()-lastNetworkUpdate > NETUPINT) {
                    MPClient.sendUDPPacket(new PacketEntityPosition(getEntityId(), posX, posY, facing, motionX, motionY));
                    lastNetworkUpdate = System.currentTimeMillis();
                }
            }
        }
    }

    @Override
    public void draw() {
        if (ViewPort.checkOnScreen(posX, posY, width, height)) { // Only renders one onScreen at a time?
            if (texture == null) {
                texture = ResourcePool.getTexture("defaultSkin", ".png");
            }
            texture.bind();

            glBegin(GL_QUADS);
                byte texX = motionX!=0||motionY!=0?ani:0;
                byte texY = facing;

                /* Draw Texture */
                glTexCoord2f(Renderer.getTextureFloat((texX*64), texture.getImageWidth()), Renderer.getTextureFloat((texY*64), texture.getImageHeight()));  // Upper-Left
                glVertex2i(ViewPort.getViewX(posX), ViewPort.getViewY(posY));

                glTexCoord2f(Renderer.getTextureFloat((texX*64)+64, texture.getImageWidth()), Renderer.getTextureFloat((texY*64), texture.getImageHeight()));  // Upper-Right
                glVertex2i(ViewPort.getViewX(posX)+width, ViewPort.getViewY(posY));

                glTexCoord2f(Renderer.getTextureFloat((texX*64)+64, texture.getImageWidth()), Renderer.getTextureFloat((texY*64)+64, texture.getImageHeight()));  // Lower-Right
                glVertex2i(ViewPort.getViewX(posX)+width, ViewPort.getViewY(posY)+height);

                glTexCoord2f(Renderer.getTextureFloat((texX*64), texture.getImageWidth()), Renderer.getTextureFloat((texY*64)+64, texture.getImageHeight()));  // Lower-Left
                glVertex2i(ViewPort.getViewX(posX), ViewPort.getViewY(posY)+height);
            glEnd();
            
            /* Cycle Animation */
            if (aniDelay<=0) {
                aniDelay=(byte) (maxAniDelay/speed);
                if (ani>=3) {
                    ani=0;
                }
                ani++;
            }
            aniDelay--;
            
    //            glBegin(GL_QUADS); // Always fails if above if-statement is removed
    //                glColor4f(1,0,0,1);
    //                glVertex3f(ViewPort.getViewX(posX),ViewPort.getViewY(posY),0);
    //                glVertex3f(ViewPort.getViewX(posX)+width,ViewPort.getViewY(posY),0);
    //                glVertex3f(ViewPort.getViewX(posX)+width,ViewPort.getViewY(posY)+height,0);
    //                glVertex3f(ViewPort.getViewX(posX),ViewPort.getViewY(posY)+height,0);
    //            glEnd();

            /* Name String */
            int strPosX = ViewPort.getViewX(posX)+width/2-(ResourcePool.getFont("Courier", 16).getWidth(username)/2);
            int strPosY = ViewPort.getViewY(posY)+height+2;
            ResourcePool.getFont("Courier", 16).drawString(strPosX, strPosY, username);
        }
    }

    @Override
    public void delete() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

}
