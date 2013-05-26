package com.marbl.administration.website.service;

import com.marbl.administration.domain.Rate;
import com.sun.jersey.api.client.*;
import com.sun.jersey.api.client.config.*;
import com.sun.jersey.api.json.JSONConfiguration;
import java.io.Serializable;
import java.util.ArrayList;
import javax.annotation.PostConstruct;
import javax.ejb.Stateless;

@Stateless
public class RateService implements Serializable {

    private WebResource resource;

    @PostConstruct
    public void postConstruct() {
        ClientConfig config = new DefaultClientConfig();
        config.getFeatures().put(JSONConfiguration.FEATURE_POJO_MAPPING, Boolean.TRUE);
        Client client = Client.create(config);
        resource = client.resource("http://localhost:8080/AdministrationBackend/resources/rates/");
    }

    public void create(Rate rate) {
        resource.post(Rate.class, rate);
    }

    public Rate edit(Rate rate) {
        return resource.put(Rate.class, rate);
    }

    public void remove(String name) {
        resource.path(name).delete();
    }

    public Rate find(String name) {
        return resource.path(name).get(Rate.class);
    }

    public ArrayList<Rate> findAll() {
        return resource.get(new GenericType<ArrayList<Rate>>() { });
    }
    
    public ArrayList<Rate> findAllHighwayRates()
    {
        ArrayList<Rate> rates = new ArrayList<>();
        for (Rate r : resource.get(new GenericType<ArrayList<Rate>>() { }))
        {
            if (r.getType() == Rate.Type.HIGHWAY) {
                rates.add(r);
            }
        }
        return rates;
    }
    
    public ArrayList<Rate> findAllCityRates()
    {
        ArrayList<Rate> rates = new ArrayList<>();
        for (Rate r : resource.get(new GenericType<ArrayList<Rate>>() { }))
        {
            if (r.getType() == Rate.Type.CITY)
            {
                rates.add(r);
            }
        }
        return rates;
    }
    
    public ArrayList<Rate> findAllRegionRates()
    {
        ArrayList<Rate> rates = new ArrayList<>();
        for (Rate r : resource.get(new GenericType<ArrayList<Rate>>() { }))
        {
            if (r.getType() == Rate.Type.REGION) {
                rates.add(r);
            }
        }
        return rates;
    }
    
    public ArrayList<Rate> findAllVehicleRates()
    {
        ArrayList<Rate> rates = new ArrayList<>();
        for (Rate r : resource.get(new GenericType<ArrayList<Rate>>() { }))
        {
            if (r.getType() == Rate.Type.VEHICLE) {
                rates.add(r);
            }
        }
        return rates;
    }
    
    public Rate findMassRate()
    {
        Rate rate = new Rate();
        for (Rate r : resource.get(new GenericType<ArrayList<Rate>>() { }))
        {
            if (r.getType() == Rate.Type.MASS) {
                rate = r;
            }
        }
        return rate;
    }

    public ArrayList<Rate> findRange(Integer from, Integer to) {
        return resource.path(from.toString()).path(to.toString()).get(new GenericType<ArrayList<Rate>>() { });
    }

    public int count() {
        return Integer.parseInt(resource.path("count").get(String.class));
    }
}
