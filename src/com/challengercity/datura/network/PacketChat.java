
package com.challengercity.datura.network;

/**
 *
 * @author Ben Sergent V at http://sergenttech.net/
 */
public class PacketChat extends Packet{

    public String msg;

    public PacketChat() {
    }
    
    public PacketChat(String msg) {
        this.msg = msg;
    }
    
}
