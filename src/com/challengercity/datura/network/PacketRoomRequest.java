
package com.challengercity.datura.network;

import com.challengercity.datura.Door;

/**
 *
 * @author Ben Sergent V/ha1fBit
 */
public class PacketRoomRequest extends Packet {

    public Door door;

    public PacketRoomRequest() {
    }

    public PacketRoomRequest(Door door) {
        this.door = door;
    }
    
}
