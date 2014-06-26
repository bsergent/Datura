
package com.challengercity.datura;

/**
 *
 * @author Ben Sergent V/ha1fBit
 */
public class ScreenConnecting extends Screen {

    public ScreenConnecting() {
        startup();
    }
    
    private void startup() {
        GUI b;
        b = new GUIButton(Datura.screenWidth/2-50, Datura.screenHeight-60, 100, 40, 99, StringHandler.getString("menu_main_cancel"), 24);
        addToRenderList(b);
        b = new GUIText(Datura.screenWidth/2, Datura.screenHeight/2-100, StringHandler.getString("server_connecting"), 24, true);
        addToRenderList(b);
        MPClient.connectMP();
    }
    
    @Override
    public void actionPerformed(int actionId) {
        if (actionId==99) {
            Datura.setScreen(new ScreenMenu());
        }
    }

}
