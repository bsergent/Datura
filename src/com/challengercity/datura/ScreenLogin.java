
package com.challengercity.datura;

import java.awt.Desktop;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.math.BigInteger;
import java.net.URL;
import java.net.URLConnection;
import java.security.MessageDigest;

/**
 *
 * @author Ben Sergent V at http://sergenttech.net/
 */
public class ScreenLogin extends Screen {

    private GUIText statusLabel;
    private GUITextBox userBox;
    private GUIPassBox passBox;
    
    public ScreenLogin() {
        startup();
    }
    
    private void startup() {
        GUI b;
        b = new GUIImage(Datura.screenWidth/2-256, 20, 512, 147, 0, 0, 512, 147, "gui_logo");
        addToRenderList(b);
        b = new GUIText(Datura.screenWidth/2-2, 20+96, "v"+Datura.getVersion()+" - Build "+Datura.getBuild(), 12, false);
        addToRenderList(b);
        b = new GUIText(Datura.screenWidth/2, 170, "Login", 24, true);
        addToRenderList(b);
        
        statusLabel = new GUIText(Datura.screenWidth/2, 220, StringHandler.getString("menu_login_enterInfo"), 16, true);
        addToRenderList(statusLabel);
        userBox = new GUITextBox(Datura.screenWidth/2-150, 240, 310, 30, Datura.prefsNode.get(Datura.PREF_USERNAME, ""), 24, 20, 2);
        addToRenderList(userBox);
        passBox = new GUIPassBox(Datura.screenWidth/2-150, 280, 310, 30, "", 16, 20, 2);
        addToRenderList(passBox);
        
        b = new GUIButton(Datura.screenWidth/2-155, Datura.screenHeight-120, 200, 40, 1, StringHandler.getString("menu_login_create"), 24);
        addToRenderList(b);
        b = new GUIButton(Datura.screenWidth/2+55, Datura.screenHeight-120, 100, 40, 0, StringHandler.getString("menu_login_submit"), 24);
        addToRenderList(b);
        b = new GUIButton(Datura.screenWidth/2-50, Datura.screenHeight-60, 100, 40, 99, StringHandler.getString("menu_main_back"), 24);
        addToRenderList(b);
    }
    
    @Override
    public void actionPerformed(int actionId) {
        if (actionId==0) {
            try {
                MessageDigest m = MessageDigest.getInstance("MD5");
                m.reset();
                m.update(passBox.text.getBytes());
                BigInteger hash = new BigInteger(1, m.digest());
                String result = hash.toString(16);
                while(result.length() < 32) { //40 for SHA-1
                    result = "0" + result;
                }
                URL getUrl = new URL("http://datura.challengercity.com/LoginHandler.php?usr="+userBox.text+"&pas="+result);
                URLConnection gc = getUrl.openConnection();
                BufferedReader getIn = new BufferedReader(new InputStreamReader(gc.getInputStream()));
                String getInputLine;
                String getResult = "";
                while ((getInputLine = getIn.readLine()) != null) 
                    getResult = getResult+getInputLine;
                getIn.close();
                if (getResult.equals("true")) {
                    Datura.setUsername(userBox.text);
                    Datura.prefsNode.put(Datura.PREF_USERNAME, userBox.text);
                    Datura.setScreen(new ScreenMenu());
                } else {
                    statusLabel.setLabel("Incorrect username-password combination");
                    passBox.text = "";
                }
            } catch (Exception ex) {
                statusLabel.setLabel("Error connecting to login server");
            }
        }
        if (actionId==1) {
            try {
                Desktop.getDesktop().browse(new URL("http://datura.challengercity.com/Register.php").toURI());
                statusLabel.setLabel("The account creation page has been opened in your default browser");
            } catch (Exception e) {
                statusLabel.setLabel("Go to http://datura.challengercity.com/Register.php");
            }
        }
        if (actionId==99) {
            Datura.setScreen(new ScreenMenu());
        }
    }

}

