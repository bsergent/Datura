
package com.challengercity.datura.network;

import com.esotericsoftware.kryo.Kryo;
import java.util.ArrayList;

/**
 *
 * @author Ben Sergent V/ha1fBit
 */
public class ClassRegister {

    public static void registerClasses(Kryo kryo) {
        kryo.register(int[][].class);
        kryo.register(int[].class);
        kryo.register(ArrayList.class);
        kryo.register(java.awt.Rectangle.class);
        kryo.register(PacketLogin.class);
        kryo.register(PacketLoginResponse.class);
        kryo.register(PacketRoom.class);
        kryo.register(PacketChat.class);
        kryo.register(PacketEntity.class);
        kryo.register(PacketEntityPosition.class);
        kryo.register(PacketEntityRemove.class);
        kryo.register(PacketRoomRequest.class);
        kryo.register(com.challengercity.datura.Door.class);
        kryo.register(com.challengercity.datura.Room.class);
        kryo.register(com.challengercity.datura.RenderableObject.class);
        kryo.register(com.challengercity.datura.Entity.class);
        kryo.register(com.challengercity.datura.EntityLiving.class);
        kryo.register(com.challengercity.datura.EntityPlayer.class);
    }
    
}
