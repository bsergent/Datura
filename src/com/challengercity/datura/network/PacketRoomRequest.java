
package com.challengercity.datura.network;

import com.challengercity.datura.Door;

/**
 *
 * @author Ben Sergent V at http://sergenttech.net/
 */
public class PacketRoomRequest extends Packet {

    public Door door;

    public PacketRoomRequest() {
    }

    public PacketRoomRequest(Door door) {
        this.door = door;
    }
    
}
