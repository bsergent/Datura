
package com.challengercity.datura.network;

/**
 *
 * @author Ben Sergent V at http://sergenttech.net/
 */
public class PacketEntityRemove extends Packet {

    public int entityId;

    public PacketEntityRemove() {
    }

    public PacketEntityRemove(int entityId) {
        this.entityId = entityId;
    }
    
}
