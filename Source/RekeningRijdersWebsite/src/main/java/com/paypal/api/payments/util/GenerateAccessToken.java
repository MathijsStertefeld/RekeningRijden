package com.paypal.api.payments.util;

//<editor-fold defaultstate="collapsed" desc="Imports">
import com.paypal.core.ConfigManager;
import com.paypal.core.rest.OAuthTokenCredential;
import com.paypal.core.rest.PayPalRESTException;
//</editor-fold>

public class GenerateAccessToken {

    public static String getAccessToken() throws PayPalRESTException {

        // ###AccessToken
        // Retrieve the access token from
        // OAuthTokenCredential by passing in
        // ClientID and ClientSecret
        String clientID = ConfigManager.getInstance().getValue("clientID");
        String clientSecret = ConfigManager.getInstance().getValue(
                "clientSecret");

        return new OAuthTokenCredential(clientID, clientSecret)
                .getAccessToken();
    }
}
