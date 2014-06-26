
package com.challengercity.datura;

/**
 *
 * @author Ben Sergent V/ha1fBit
 */
public class ScreenOptions extends Screen {

    public ScreenOptions() {
        startup();
    }
    
    private void startup() {
        GUI b;
        b = new GUIImage(Datura.screenWidth/2-256, 20, 512, 147, 0, 0, 512, 147, "gui_logo");
        addToRenderList(b);
        b = new GUIText(Datura.screenWidth/2-2, 20+96, "v"+Datura.getVersion()+" - Build "+Datura.getBuild(), 12, false);
        addToRenderList(b);
        b = new GUIText(Datura.screenWidth/2, 170, "Options", 24, true);
        addToRenderList(b);
        b = new GUIButton(Datura.screenWidth/2-100, 200, 200, 40, 0, StringHandler.getString("menu_options_fullscreen"), 24);
        addToRenderList(b);
        b = new GUIButton(Datura.screenWidth/2-50, Datura.screenHeight-60, 100, 40, 99, StringHandler.getString("menu_main_back"), 24);
        addToRenderList(b);
    }
    
    @Override
    public void actionPerformed(int actionId) {
        if (actionId==0) {
            if (!Datura.fullscreen) {
                try {
                    Datura.setDisplayMode(Datura.screenWidth, Datura.screenHeight, true); // 1280, 720  |  2560, 1440 | 640, 480
                    Datura.fullscreen=true;
                    Datura.prefsNode.putBoolean(Datura.PREF_FULLSCREEN, true);
                } catch (Exception ex) {
                    Datura.log(ScreenOptions.class,"Could not exit fullscreen");
                }
            } else {
                try {
                    Datura.setDisplayMode(Datura.screenWidth, Datura.screenHeight, false); // 1280, 720  |  2560, 1440 | 640, 480
                    Datura.fullscreen=false;
                    Datura.prefsNode.putBoolean(Datura.PREF_FULLSCREEN, false);
                } catch (Exception ex) {
                    Datura.log(ScreenOptions.class,"Could not enter fullscreen");
                }
            }
            ViewPort.updateView();
        }
        if (actionId==99) {
            Datura.setScreen(new ScreenMenu());
        }
    }

}
