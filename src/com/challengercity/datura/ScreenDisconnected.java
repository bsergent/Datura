
package com.challengercity.datura;

import java.awt.Desktop;
import java.net.URL;

/**
 *
 * @author Ben Sergent V/ha1fBit
 */
public class ScreenDisconnected extends Screen {

    public ScreenDisconnected(String reason) {
        startup(reason);
    }
    
    private void startup(String reason) {
        GUI b;
        b = new GUIButton(Datura.screenWidth/2-50, Datura.screenHeight-60, 100, 40, 99, StringHandler.getString("menu_main_back"), 24);
        addToRenderList(b);
        b = new GUIText(Datura.screenWidth/2, Datura.screenHeight/2-100, reason, 24, true);
        addToRenderList(b);
        if (reason.toLowerCase().contains("outdated")) {
            b = new GUIButton(Datura.screenWidth/2-100, Datura.screenHeight/2, 200, 40, 0, "Download latest", 24);
            addToRenderList(b);
        }
    }
    
    @Override
    public void actionPerformed(int actionId) {
        if (actionId==0) {
            try {
                Desktop.getDesktop().browse(new URL("http://datura.challengercity.com/").toURI());
            } catch (Exception e) {
                // Do nothing
            }
        }
        if (actionId==99) {
            Datura.setScreen(new ScreenMenu());
        }
    }

}
