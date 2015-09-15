
package com.challengercity.datura.network;

/**
 *
 * @author Ben Sergent V at http://sergenttech.net/
 */
public class PacketLogin extends Packet {

    public String username;
    public String gameType;
    public String version;

    public PacketLogin() {
    }
    
    public PacketLogin(String username, String gameType, String version) {
        this.username = username;
        this.gameType = gameType;
        this.version = version;
    }
    
}
