/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import com.paypal.api.payments.Payment;
import com.paypal.core.ConfigManager;
import com.paypal.core.rest.OAuthTokenCredential;
import com.paypal.core.rest.PayPalRESTException;
import java.io.File;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Stateless;


/**
 *
 * @author Bas
 */
@Stateless
public class PayPalService {
    
    public static void main(String[] args) {
        try {
            String s = getAccessToken();
            System.out.println("AccessToken: " + s);
        } catch (PayPalRESTException ex) {
            Logger.getLogger(PayPalService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    private static String getAccessToken() throws PayPalRESTException
    {
            Payment.initConfig(new File("sdk_config.properties"));
            
            String clientID = ConfigManager.getInstance().getValue("clientID");
            String clientSecret = ConfigManager.getInstance().getValue("clientSecret");
            System.out.println("id: " + clientID);
            System.out.println("secret: " + clientSecret);
            
            String accessToken = new OAuthTokenCredential(clientID, clientSecret).getAccessToken();
            
            return accessToken;
    }
}
