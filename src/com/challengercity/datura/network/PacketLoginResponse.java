
package com.challengercity.datura.network;

/**
 *
 * @author Ben Sergent V at http://sergenttech.net/
 */
public class PacketLoginResponse extends Packet {

    public boolean accept;
    public String reason;

    public PacketLoginResponse() {
    }
    
    public PacketLoginResponse(boolean accept, String reason) {
        this.accept = accept;
        this.reason = reason;
    }
    
}
