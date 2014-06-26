
package com.challengercity.datura.network;

import com.challengercity.datura.Room;

/**
 *
 * @author Ben Sergent V/ha1fBit
 */
public class PacketRoom extends Packet {

    public Room room;

    public PacketRoom() {
    }
    
    public PacketRoom(Room room) {
        this.room = room;
    }
    
}
