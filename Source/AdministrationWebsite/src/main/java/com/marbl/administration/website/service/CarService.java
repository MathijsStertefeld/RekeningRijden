package com.marbl.administration.website.service;

//<editor-fold defaultstate="collapsed" desc="Imports">
import com.marbl.administration.domain.Car;
import com.sun.jersey.api.client.*;
import com.sun.jersey.api.client.config.*;
import com.sun.jersey.api.json.JSONConfiguration;
import java.io.Serializable;
import java.util.ArrayList;
import javax.annotation.PostConstruct;
import javax.ejb.Stateless;
import javax.ws.rs.core.MediaType;
//</editor-fold>

// This service calls the backend's service to manipulate cars.

@Stateless
public class CarService implements Serializable {

    //<editor-fold defaultstate="collapsed" desc="Fields">
    private WebResource wr;
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="Methods">
    @PostConstruct
    public void postConstruct() {
        ClientConfig config = new DefaultClientConfig();
        config.getFeatures().put(JSONConfiguration.FEATURE_POJO_MAPPING, Boolean.TRUE);
        Client client = Client.create(config);
        wr = client.resource("http://192.168.30.185:8080/AdministrationBackend/resources/cars/");
    }

    public Car edit(Car car) {
        ClientResponse cr = wr
                .accept(MediaType.APPLICATION_JSON)
                .type(MediaType.APPLICATION_JSON)
                .put(ClientResponse.class, car);
        
        switch (cr.getClientResponseStatus()) {
            case OK:
                return cr.getEntity(Car.class);
            default:
                return null;
        }
    }

    public Car find(String carTrackerId) {
        ClientResponse cr = wr.path(carTrackerId)
                .accept(MediaType.APPLICATION_JSON)
                .type(MediaType.TEXT_PLAIN)
                .get(ClientResponse.class);
        
        switch (cr.getClientResponseStatus()) {
            case OK:
                return cr.getEntity(Car.class);
            default:
                return null;
        }
    }

    public ArrayList<Car> findAll() {
        ClientResponse cr = wr
                .accept(MediaType.APPLICATION_JSON)
                .type(MediaType.TEXT_PLAIN)
                .get(ClientResponse.class);
        
        GenericType<ArrayList<Car>> gt = new GenericType<ArrayList<Car>>() {
        };
        
        switch (cr.getClientResponseStatus()) {
            case OK:
                return cr.getEntity(gt);
            default:
                return null;
        }
    }
    //</editor-fold>
}
