
package com.challengercity.datura.server;

import com.esotericsoftware.kryonet.Connection;

/**
 *
 * @author Ben Sergent V at http://sergenttech.net/
 */
public class ConnectionWrapper {

    public Connection c;
    public String r;
    public int entId;

    public ConnectionWrapper(Connection c) {
        this.c = c;
    }
    
}
