
package com.challengercity.datura;

import org.lwjgl.opengl.Display;

/**
 *
 * @author Ben Sergent V/ha1fBit
 */
public class ScreenMenu extends Screen {

    public ScreenMenu() {
        super();
        startup();
    }
    
    private void startup() { // TODO Add logout button
        GUI b;
        b = new GUIImage(Datura.screenWidth/2-256, 20, 512, 147, 0, 0, 512, 147, "gui_logo");
        addToRenderList(b);
        b = new GUIText(Datura.screenWidth/2-2, 20+96, "v"+Datura.getVersion()+" - Build "+Datura.getBuild(), 12, false);
        addToRenderList(b);
        b = new GUIText(5, 5, Datura.getUsername().equals("")?"Not logged in":"Logged in as "+Datura.getUsername(), 12, false);
        addToRenderList(b);
        b = new GUIText(Datura.screenWidth/2, 170, "Main Menu", 24, true);
        addToRenderList(b);
        if (!Datura.getUsername().equals("")) {
            b = new GUIButton(Datura.screenWidth/2-95, 200, 190, 40, 0, StringHandler.getString("menu_main_multiplayer"), 24);
            addToRenderList(b);
        } else {
            b = new GUIButton(Datura.screenWidth/2-95, 200, 190, 40, 2, StringHandler.getString("menu_main_login"), 24);
            addToRenderList(b);
        }
        b = new GUIButton(Datura.screenWidth/2-75, 250, 150, 40, 1, StringHandler.getString("menu_main_options"), 24);
        addToRenderList(b);
//        b = new GUIButton(Underground.screenWidth/2-125, 300, 250, 40, 2, StringHandler.getString("menu_main_options"), 24);
//        addToRenderList(b);
//        b = new GUIButton(Underground.screenWidth/2-125, 350, 250, 40, 5, "Change-Log", 24);
//        addToRenderList(b);
//        b = new GUIButton(Underground.screenWidth/2-125, 400, 250, 40, 3, "Credits", 24);
//        addToRenderList(b);
        b = new GUIButton(Datura.screenWidth/2-50, Datura.screenHeight-60, 100, 40, 99, StringHandler.getString("menu_main_exit"), 24);
        addToRenderList(b);
    }
    
    @Override
    public void actionPerformed(int actionId) {
        if (actionId==0) {
            Datura.setScreen(new ScreenConnecting());
        }
        if (actionId==1) {
            Datura.setScreen(new ScreenOptions());
        }
        if (actionId==2) {
            Datura.setScreen(new ScreenLogin());
        }
        if (actionId==99) {
            Display.destroy();
            System.exit(0);
        }
    }

}