
package com.challengercity.datura.network;

/**
 *
 * @author Ben Sergent V at http://sergenttech.net/
 */
public class PacketEntityPlayerMove extends Packet {

    public int playerId;
    public int motionX;
    public int motionY;

    public PacketEntityPlayerMove() {
    }

    public PacketEntityPlayerMove(int playerId, int motionX, int motionY) {
        this.playerId = playerId;
        this.motionX = motionX;
        this.motionY = motionY;
    }
    
}
