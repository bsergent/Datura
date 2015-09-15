
package com.challengercity.datura.server.saves;

import com.challengercity.datura.Item;

/**
 *
 * @author Ben Sergent V at http://sergenttech.net/
 */
public class DataPlayer {

    public String username;
    public int posX;
    public int posY;
    public String room;
    public Item[] inv;

    public DataPlayer() {
    }

    public DataPlayer(String username, int posX, int posY, String room, Item[] inv) {
        this.username = username;
        this.posX = posX;
        this.posY = posY;
        this.inv = inv;
        this.room = room;
    }
    
}
