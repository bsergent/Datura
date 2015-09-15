/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.challengercity.datura;

import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryo.io.Input;
import com.esotericsoftware.kryo.io.Output;
import java.util.ArrayList;
import org.newdawn.slick.SlickException;

/**
 *
 * @author Ben Sergent V at http://sergenttech.net/
 */
public class Region extends org.newdawn.slick.tiled.TiledMap implements com.esotericsoftware.kryo.KryoSerializable {
    
    private String name;
    private int entityLayerIndex;

    public Region(String ref) throws SlickException {
        super(ref);
    }
    
    public void tick(long delta) {
        for (Entity e : (ArrayList<Entity>) layers.get(entityLayerIndex)) {
            if (e != null) {
                e.tick(delta);
            }
        }
    }

    @Override
    public void write(Kryo kryo, Output output) {
//        output.writeString(name);
//        kryo.getSerializer(ArrayList.class).write(kryo, output, doors);
//        kryo.getSerializer(ArrayList.class).write(kryo, output, entities);
//        int[][] serMap = new int[map.length][map[0].length];
//        for (int x = 0; x < map.length; x++) {
//            for (int y = 0; y < map[0].length; y++) {
//                serMap[x][y] = map[x][y]!=null?map[x][y].getTypeId():0;
//            }
//        }
//        kryo.getSerializer(int[][].class).write(kryo, output, serMap);
    }

    @Override
    public void read(Kryo kryo, Input input) {
//        name = input.readString();
//        doors = kryo.readObject(input, ArrayList.class);
//        entities = kryo.readObject(input, ArrayList.class);
//        int[][] serMap = kryo.readObject(input, int[][].class);
//        map = new Tile[serMap.length][serMap[0].length];
//        for (int x = 0; x < serMap.length; x++) {
//            for (int y = 0; y < serMap[0].length; y++) {
//                map[x][y] = Tile.getTileById(serMap[x][y]);
//            }
//        }
    }
    
}
