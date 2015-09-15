
package com.challengercity.datura.network;

import com.challengercity.datura.Entity;

/**
 *
 * @author Ben Sergent V at http://sergenttech.net/
 */
public class PacketEntity extends Packet {

    public Entity entity;

    public PacketEntity() {
    }

    public PacketEntity(Entity entity) {
        this.entity = entity;
    }
    
}
