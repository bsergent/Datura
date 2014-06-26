
package com.challengercity.datura;

import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryo.io.Input;
import com.esotericsoftware.kryo.io.Output;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import javax.imageio.ImageIO;
import org.newdawn.slick.opengl.Texture;

/**
 *
 * @author Ben Sergent V/ha1fBit
 */
public class Room implements com.esotericsoftware.kryo.KryoSerializable {

    private Texture bufferedMap;
    private boolean dirty = false;
    private String name;
    private Tile[][] map;
    private boolean[][] ventilationMap;
    private int nextEntityId = 0;
    private ArrayList<Door> doors = new ArrayList<Door>();
    private ArrayList<Entity> entities = new ArrayList<Entity>();
    
    private boolean dAlreadyDrawn = false;

    public Room() {
    }
    
    public Room(String name) {
        this.name = name;
        load(name);
    }
    
    public String getName() {
        return name;
    }
    
    public int[] getSize() {
        return new int[] {map.length, map[0].length};
    }
    
    private void load(String mapName) {
        try{
            
            InputStream in = ClassLoader.getSystemClassLoader().getResourceAsStream("com/challengercity/datura/resources/maps/"+mapName+".png");
            Image image = ImageIO.read(in);
            
            int height = image.getHeight(null);
            int width = image.getWidth(null);
            
            BufferedImage buffImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);

            Graphics g = buffImage.getGraphics();
            g.drawImage(image, 0, 0, null);
            
            map = new Tile[image.getWidth(null)][image.getHeight(null)];
            
            for (int a = 0;  a < height;  a++) {
                for (int b = 0;  b < width;  b++) {
                    int i = b;

                    int rgba = buffImage.getRGB(b, a);
//                    int red = (rgba >> 16) & 0xff;
//                    int green = (rgba >> 8) & 0xff;
//                    int blue = rgba & 0xff;
                    
                    map[b][a] = Tile.getTileByColor(new java.awt.Color(rgba));
                }
            }
            in.close();
            
            InputStream in2 = ClassLoader.getSystemClassLoader().getResourceAsStream("com/challengercity/datura/resources/maps/"+mapName+".doors");
            BufferedReader br = new BufferedReader(new InputStreamReader(in2));
            
            String strLine;
            while ((strLine = br.readLine()) != null) {
                if (!strLine.startsWith("//") && !strLine.equals("")) {
                    String[] ary = strLine.split(",");
                    doors.add(new Door(Integer.parseInt(ary[0]),Integer.parseInt(ary[1]),Integer.parseInt(ary[2]),Integer.parseInt(ary[3]),ary[4]));
                }
            }
            in2.close();
            
        } catch (Exception ex) {
            Datura.log(Room.class, "Failed to load room "+mapName);
        }
        
    }
    
    public void tick(long delta) {
        for (Entity e:entities) {
            if (e != null) {
                e.tick(delta);
            }
        }
    }
    
    public void updateBufferedMap() {
//        for (int x = 0; x < map.length; x++) {
//            for (int y = 0; y < map[x].length; y++) {
//                map[x][y].draw(x, y);
//            }
//        }
    }
    
    public void addEntity(Entity e) {
        entities.add(e);
    }
    
    public void removeEntity(Entity e) {
        entities.remove(e);
    }
    
    public Entity getEntityFromId(int id) {
        for (Entity e:entities) {
            if (e != null && e.getEntityId() == id) {
                return e;
            }
        }
        return null;
    }
    
    public EntityPlayer getPlayerFromId(int id) {
        for (Entity e : entities) {
            if (e instanceof EntityPlayer && ((EntityPlayer) e).getPlayerId() == id) {
                return (EntityPlayer) e;
            }
        }
        return null;
    }
    
    public int getNextEntityId() {
        return nextEntityId++;
    }
    
    public Tile getTileAt(int x, int y) {
        if (x/Tile.SIZE >= map.length || x/Tile.SIZE < 0 || y/Tile.SIZE >= map[0].length || y/Tile.SIZE < 0) {
            return null;
        }
        return map[x/Tile.SIZE][y/Tile.SIZE];
    }
    
    public Door getDoorAt(int x, int y) {
//        Rectangle r;
        for (Door d : doors) {
//            r = new Rectangle(d.curX*Tile.SIZE,d.curY*Tile.SIZE,Tile.SIZE,Tile.SIZE);
//            if (r.contains(x,y)) {
//                return d;
//            }
//            if (r.intersects(d.curX*Tile.SIZE, d.curY*Tile.SIZE, Tile.SIZE, Tile.SIZE)) {
//                return d;
//            }
//            if ((d.curX*Tile.SIZE) >= x && (d.curX*Tile.SIZE)+Tile.SIZE <= x && (d.curY*Tile.SIZE) >= y && (d.curY*Tile.SIZE)+Tile.SIZE <= y) {
//                return d;
//            }
            if (x >= (d.curX*Tile.SIZE) && x < (d.curX*Tile.SIZE)+Tile.SIZE && y >= (d.curY*Tile.SIZE) && y< (d.curY*Tile.SIZE)+Tile.SIZE) {
                return d;
            }
        }
        return null;
    }
    
    public void draw() {
        if (dirty) {
            updateBufferedMap();
            dirty = false;
        }
//        if (!dAlreadyDrawn) {
//            for (int x = 0; x < map.length; x++) {
//                for (int y = 0; y < map[x].length; y++) {
//                    System.out.print(map[x][y].getTypeId());
//                }
//                System.out.println("");
//            }
//            dAlreadyDrawn = true;
//        }
        for (int x = 0; x < map.length; x++) {
            for (int y = 0; y < map[x].length; y++) {
                map[x][y].draw(x*Tile.SIZE, y*Tile.SIZE, 
                        y-1>=0&&map[x][y-1]!=null?map[x][y-1]:Tile.AIR, 
                        y+1<map[x].length&&map[x][y+1]!=null?map[x][y+1]:Tile.AIR, 
                        x-1>=0&&map[x-1][y]!=null?map[x-1][y]:Tile.AIR, 
                        x+1<map.length&&map[x+1][y]!=null?map[x+1][y]:Tile.AIR);
            }
        }
        for (Door d : doors) {
            d.draw((d.curY)-1>=0&&map[(d.curX)][(d.curY)-1]!=null?map[(d.curX)][(d.curY)-1]:Tile.AIR, 
                        (d.curY)+1<map[(d.curX)].length&&map[(d.curX)][(d.curY)+1]!=null?map[(d.curX)][(d.curY)+1]:Tile.AIR, 
                        (d.curX)-1>=0&&map[(d.curX)-1][(d.curY)]!=null?map[(d.curX)-1][(d.curY)]:Tile.AIR, 
                        (d.curX)+1<map.length&&map[(d.curX)+1][(d.curY)]!=null?map[(d.curX)+1][(d.curY)]:Tile.AIR);
        }
        // TODO Draw buffered map
        try {
            for (Entity e:entities) {
                if (e != null) {
                    e.draw();
                }
            }
        } catch (Exception ex) {
            // Mourn for the dead entity that no longer needs to be drawn
        }
    }

    @Override
    public void write(Kryo kryo, Output output) {
        output.writeString(name);
        kryo.getSerializer(ArrayList.class).write(kryo, output, doors);
        kryo.getSerializer(ArrayList.class).write(kryo, output, entities);
        int[][] serMap = new int[map.length][map[0].length];
        for (int x = 0; x < map.length; x++) {
            for (int y = 0; y < map[0].length; y++) {
                serMap[x][y] = map[x][y]!=null?map[x][y].getTypeId():0;
            }
        }
        kryo.getSerializer(int[][].class).write(kryo, output, serMap);
    }

    @Override
    public void read(Kryo kryo, Input input) {
        name = input.readString();
        doors = kryo.readObject(input, ArrayList.class);
        entities = kryo.readObject(input, ArrayList.class);
        int[][] serMap = kryo.readObject(input, int[][].class);
        map = new Tile[serMap.length][serMap[0].length];
        for (int x = 0; x < serMap.length; x++) {
            for (int y = 0; y < serMap[0].length; y++) {
                map[x][y] = Tile.getTileById(serMap[x][y]);
            }
        }
    }
    
}
