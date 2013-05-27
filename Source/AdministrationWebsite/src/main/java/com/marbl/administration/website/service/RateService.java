package com.marbl.administration.website.service;

//<editor-fold defaultstate="collapsed" desc="Imports">
import com.marbl.administration.domain.Rate;
import com.marbl.administration.domain.RateType;
import com.sun.jersey.api.client.*;
import com.sun.jersey.api.client.config.*;
import com.sun.jersey.api.json.JSONConfiguration;
import java.io.Serializable;
import java.util.ArrayList;
import javax.annotation.PostConstruct;
import javax.ejb.Stateless;
import javax.ws.rs.core.MediaType;
//</editor-fold>

// This service calls the backend's service to manipulate rates.

@Stateless
public class RateService implements Serializable {

    //<editor-fold defaultstate="collapsed" desc="Fields">
    private WebResource wr;
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="Methods">
    @PostConstruct
    public void postConstruct() {
        ClientConfig config = new DefaultClientConfig();
        config.getFeatures().put(JSONConfiguration.FEATURE_POJO_MAPPING, Boolean.TRUE);
        Client client = Client.create(config);
        wr = client.resource("http://192.168.30.185:8080/AdministrationBackend/resources/rates/");
    }

    public Rate edit(Rate rate) {
        return wr.put(Rate.class, rate);
    }

    public Rate find(String name) {
        ClientResponse cr = wr.path(name)
                .accept(MediaType.APPLICATION_JSON)
                .type(MediaType.TEXT_PLAIN)
                .get(ClientResponse.class);

        switch (cr.getClientResponseStatus()) {
            case OK:
                if (cr.hasEntity()) {
                    return cr.getEntity(Rate.class);
                }
            default:
                return null;
        }
    }

    public ArrayList<Rate> findAll() {
        ClientResponse cr = wr
                .accept(MediaType.APPLICATION_JSON)
                .type(MediaType.TEXT_PLAIN)
                .get(ClientResponse.class);

        GenericType<ArrayList<Rate>> gt = new GenericType<ArrayList<Rate>>() {
        };

        switch (cr.getClientResponseStatus()) {
            case OK:
                return cr.getEntity(gt);
            default:
                return null;
        }
    }

    public ArrayList<Rate> findAllHighwayRates() {
        ArrayList<Rate> rates = new ArrayList();
        
        for (Rate r : findAll()) {
            if (r.getType() == RateType.HIGHWAY) {
                rates.add(r);
            }
        }
        
        return rates;
    }

    public ArrayList<Rate> findAllCityRates() {
        ArrayList<Rate> rates = new ArrayList();
        
        for (Rate r : findAll()) {
            if (r.getType() == RateType.CITY) {
                rates.add(r);
            }
        }
        
        return rates;
    }

    public ArrayList<Rate> findAllRegionRates() {
        ArrayList<Rate> rates = new ArrayList();
        
        for (Rate r : findAll()) {
            if (r.getType() == RateType.REGION) {
                rates.add(r);
            }
        }
        
        return rates;
    }

    public ArrayList<Rate> findAllVehicleRates() {
        ArrayList<Rate> rates = new ArrayList();
        
        for (Rate r : findAll()) {
            if (r.getType() == RateType.VEHICLE) {
                rates.add(r);
            }
        }
        
        return rates;
    }

    public Rate findMassRate() {
        Rate rate = new Rate();
        
        for (Rate r : findAll()) {
            if (r.getType() == RateType.MASS) {
                rate = r;
            }
        }
        
        return rate;
    }
    //</editor-fold>
}
