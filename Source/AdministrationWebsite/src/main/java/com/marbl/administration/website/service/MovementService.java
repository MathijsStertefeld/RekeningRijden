package com.marbl.administration.website.service;

//<editor-fold defaultstate="collapsed" desc="Imports">
import com.marbl.autosysteem.Movement;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.GenericType;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.api.client.config.ClientConfig;
import com.sun.jersey.api.client.config.DefaultClientConfig;
import java.io.Serializable;
import java.util.ArrayList;
import javax.annotation.PostConstruct;
import javax.ejb.Stateless;
import javax.ws.rs.core.MediaType;
//</editor-fold>

// This service calls the backend's service to manipulate movements.

@Stateless
public class MovementService implements Serializable {

    //<editor-fold defaultstate="collapsed" desc="Fields">
    private WebResource wr;
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="Methods">
    @PostConstruct
    public void postConstruct() {
        ClientConfig config = new DefaultClientConfig();
        Client client = Client.create(config);
        wr = client.resource("http://192.168.30.187:8080/VerplaatsingSysteemWeb/resources/");
    }

    public ArrayList<Movement> findAll(Integer driverBsn, String carTrackerId) {
        ClientResponse cr = wr.path("movement")
                .queryParam("driverBsn", driverBsn.toString())
                .queryParam("carTrackerId", carTrackerId)
                .accept(MediaType.APPLICATION_JSON)
                .get(ClientResponse.class);

        GenericType<ArrayList<Movement>> gt = new GenericType<ArrayList<Movement>>() {
        };

        switch (cr.getClientResponseStatus()) {
            case OK:
                if (cr.hasEntity()) {
                    return cr.getEntity(gt);
                }
            default:
                return null;
        }
    }
    //</editor-fold>
}
