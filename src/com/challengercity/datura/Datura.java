
package com.challengercity.datura;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.URL;
import java.net.URLConnection;
import java.util.MissingResourceException;
import java.util.ResourceBundle;
import java.util.prefs.Preferences;
import org.lwjgl.LWJGLException;
import org.lwjgl.Sys;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;
import static org.lwjgl.opengl.GL11.glViewport;

/**
 *
 * @author Sgrt.
 */
public class Datura {

    private Renderer renderer;
    private static Controller control;
    private static final String gameName = "Datura";
    private static final String version = "0.7.8A";
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
    public static long lastDelta;
    public static int guiCooldown = 0;
    private static Datura ug;
    public static final boolean DEBUG = true;
    
    public static Preferences prefsNode = Preferences.userNodeForPackage(Datura.class);
    public static final String PREF_FULLSCREEN = "Fullscreen";
    public static final String PREF_USERNAME = "Username";
    public static final String PREF_SKINADDRESS = "SkinAddress";

    public Datura(String[] args) {
        if (DEBUG) username = "Debugger";
        run(args);
    }
    
    public static Datura getGameInstance() {
        return ug;
    }
    
    public static String getVersion() {
        return version;
    }
    
    public static String getBuild() {
        return build;
    }
    
    public static String getGameName() {
        return gameName;
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
    
    public int getDelta() {
        long time = getTime();
        int delta = (int) (time - lastFrame);
        lastFrame = time;

        if (delta > (1000/60)*3) {
            delta = (1000/60)*3;
        }
        if (delta < (1000/60)) {
            delta = 1000/60;
        }
        lastDelta = delta;
        return delta;
    }
    
    public void updateFPS() {
        if (getTime() - lastFPS > 1000) { 
            currentFPS=fps;
            fps = 0; //reset the FPS counter
            lastFPS += 1000; //add one second
        }
        fps++;
    }
    
    public static void setDisplayMode(int width, int height, boolean fullscreen) {

        // return if requested DisplayMode is already set
        if ((Display.getDisplayMode().getWidth() == width) && 
            (Display.getDisplayMode().getHeight() == height) && 
            (Display.isFullscreen() == fullscreen)) {
                return;
        }

        try {
            DisplayMode targetDisplayMode = null;

            if (fullscreen) {
                DisplayMode[] modes = Display.getAvailableDisplayModes();
                int freq = 0;

                for (int i=0;i<modes.length;i++) {
                    DisplayMode current = modes[i];

                    if ((current.getWidth() == width) && (current.getHeight() == height)) {
                        if ((targetDisplayMode == null) || (current.getFrequency() >= freq)) {
                            if ((targetDisplayMode == null) || (current.getBitsPerPixel() > targetDisplayMode.getBitsPerPixel())) {
                                targetDisplayMode = current;
                                freq = targetDisplayMode.getFrequency();
                            }
                        }
                        
                        if ((current.getBitsPerPixel() == Display.getDesktopDisplayMode().getBitsPerPixel()) &&
                            (current.getFrequency() == Display.getDesktopDisplayMode().getFrequency())) {
                                targetDisplayMode = current;
                                break;
                        }
                    }
                }
            } else {
                targetDisplayMode = new DisplayMode(width,height);
            }

            if (targetDisplayMode == null) {
                System.out.println("Failed to find value mode: "+width+"x"+height+" fs="+fullscreen);
                return;
            }

            Display.setDisplayMode(targetDisplayMode);
            Display.setFullscreen(fullscreen);

        } catch (LWJGLException e) {
            System.out.println("Unable to setup mode "+width+"x"+height+" fullscreen="+fullscreen + e);
        }
    }
    
    private void run(String[] args) {
        Datura.log(getClass(), "Initialized - v"+version+" - b"+build);
        
        if (args.length > 0) {
            sessionID = args[0];
            log(Datura.class, "SessionID: "+sessionID);
            
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
        
        try {
            setDisplayMode(screenWidth, screenHeight, prefsNode.getBoolean(PREF_FULLSCREEN, false));
            Display.setTitle(gameName+" - v"+version);
            Display.create();
            glViewport(0, 0, Display.getWidth(), Display.getHeight());
            screenHeight=Display.getHeight();
            screenWidth=Display.getWidth();
            ViewPort.updateView();
        } catch(Exception ex) {
            Datura.log(getClass(), "Could not setup display");
            System.exit(1);
        }
        
        ResourcePool.loadFrequentResources();
        
        renderer = new Renderer(this);
        currentScreen = new ScreenMenu();
        control = new Controller(); // Listen for input
        
        lastFPS = getTime();
        getDelta();
        
        while(!Display.isCloseRequested()) { // Game Loop
            Controller.checkInput();
            currentScreen.tick(getDelta());
            currentScreen.mouseUpdate();
            updateFPS();
            renderer.render();
            Display.sync(60);
        }
        
        Display.destroy();
        System.exit(0);
    }

}
