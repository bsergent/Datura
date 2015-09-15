
package com.challengercity.datura;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.URL;
import java.net.URLConnection;
import java.util.MissingResourceException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.prefs.Preferences;
import org.lwjgl.Sys;
import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.tiled.TiledMap;

/**
 *
 * @author Ben Sergent V at http://sergenttech.net/
 */
public class Datura extends org.newdawn.slick.BasicGame {

    private Renderer renderer;
    private static Controller control;
    private static final String GAMENAME = "Datura";
    private static final String VERSION = "0.7.8A";
    private static String build;
    private static final ResourceBundle rb = ResourceBundle.getBundle("version"); 
    private static String username = "";
    private static String sessionID;
    public static long lastPing = 0;
    public static Screen currentScreen;
    public static boolean fullscreen = false;
    public static int screenWidth = 1280; // 1280
    public static int screenHeight = 720; // 720
    private static long lastFrame;
    private static long lastFPS;
    private static int fps;
    public static int currentFPS;
    public static int guiCooldown = 0;
    private static Datura ug;
    public static final boolean DEBUG = true;
    
    public static Preferences prefsNode = Preferences.userNodeForPackage(Datura.class);
    public static final String PREF_FULLSCREEN = "fullscreen";
    public static final String PREF_USERNAME = "username";
    public static final String PREF_SKINADDRESS = "skinAddress";

    public Datura(String[] args) {
        super(GAMENAME+" - v"+VERSION);
        if (args.length > 0) {
            sessionID = args[0];
            log(Datura.class, "SessionID: "+sessionID);
        }
        
    }
    
    public static Datura getGameInstance() {
        return ug;
    }
    
    public static String getVersion() {
        return VERSION;
    }
    
    public static String getBuild() {
        return build;
    }
    
    public static String getGameName() {
        return GAMENAME;
    }
    
    public static String getUsername() {
        return username;
    }

    public static void setUsername(String username) {
        Datura.username = username;
    }
    
    public static void main(String[] args) {
        Datura.build = getRbTok("BUILD");
        ug = new Datura(args);
        try {
            ug = new Datura(args);
            AppGameContainer container = new AppGameContainer(ug);
            container.setDisplayMode(1280,720,false);
            container.start();
        } catch (SlickException ex) {
            ex.printStackTrace();
        }
    }
    
    public static String getRbTok(String propToken) { 
        String msg = ""; 
        try { 
            msg = rb.getString(propToken); 
        } catch (MissingResourceException e) { 
            System.err.println("Token ".concat(propToken).concat(" not in Propertyfile!")); 
        } 
        return msg; 
    }
    
    public static void log(Class cls, String str) {
        System.out.println("["+cls.getSimpleName()+"] "+str);
    }
    
    public static void setScreen(Screen scr) {
        Renderer.removeFromRenderList(currentScreen);
        currentScreen = scr;
    }
    
    public static Room getCurrentRoom() {
        if (currentScreen instanceof ScreenGame) {
            return ((ScreenGame) currentScreen).getCurrentRoom();
        }
        return null;
    }
    
    public long getTime() {
        return (Sys.getTime() * 1000) / Sys.getTimerResolution();
    }
    
    public void updateFPS() {
        if (getTime() - lastFPS > 1000) { 
            currentFPS=fps;
            fps = 0; //reset the FPS counter
            lastFPS += 1000; //add one second
        }
        fps++;
    }
    
    @Override
    public void init(GameContainer gc) {
        Datura.log(getClass(), "Initializing - v"+VERSION+" - b"+build);
        if (DEBUG) username = "Debugger";
        
        if (!"".equals(sessionID)) {
            try {
                URLConnection connection = new URL("http://challengercity.com/v4/account/getUsernameFromSession.php").openConnection();
                String query = "sessionId="+sessionID;
                connection.setDoOutput(true);
                OutputStream output = connection.getOutputStream();
                output.write(query.getBytes());
                BufferedReader postIn = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                String postInputLine;
                String postResult = "";
                while ((postInputLine = postIn.readLine()) != null) 
                    postResult = postResult + postInputLine;
                postIn.close();
                if (postResult.startsWith("1")) {
                    username = postResult.substring(2);
                    Datura.log(getClass(), "Username: "+username);
                } else {
                    Datura.log(getClass(), "Username: Failed to retrieve");
                }
            } catch (IOException ex) {
                Datura.log(getClass(), "Username: Failed to retrieve");
            }
        }
        
        ResourcePool.loadFrequentResources();
        
        renderer = new Renderer(this);
        currentScreen = new ScreenMenu();
        
        lastFPS = getTime();
        
        Datura.log(getClass(), "Initialized");
        
        try {
            defaultMap = new TiledMap("/src/com/challengercity/datura/resources/defaultMap.tmx", "/src/com/challengercity/datura/resources");
        } catch (SlickException ex) {
            Logger.getLogger(Datura.class.getName()).log(Level.SEVERE, null, ex);
            System.exit(1);
        }
    }

    @Override
    public void update(GameContainer container, int delta) throws SlickException {
        Input input = container.getInput();
        //Controller.checkInput();
        //currentScreen.tick(delta);
        //currentScreen.mouseUpdate();
    }

    @Override
    public void render(GameContainer container, Graphics g) throws SlickException {
        updateFPS();
        //renderer.render();
        defaultMap.render(0, 0);
    }
    
    private TiledMap defaultMap;

}
