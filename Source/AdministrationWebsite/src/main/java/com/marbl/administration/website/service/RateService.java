package com.marbl.administration.website.service;

import com.marbl.administration.domain.Rate;
import com.sun.jersey.api.client.*;
import com.sun.jersey.api.client.config.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import javax.annotation.PostConstruct;
import javax.ejb.Stateless;

@Stateless
public class RateService implements Serializable {

    private WebResource webResource;

    @PostConstruct
    public void postConstruct() {
        ClientConfig config = new DefaultClientConfig();
        Client client = Client.create(config);
        webResource = client.resource("http://localhost:8080/AdministrationBackend/");
    }

    public void create(Rate rate) {
        webResource.path("resources").path("rate").post(Rate.class, rate);
    }

    public Rate edit(Rate rate) {
        return webResource.path("resources").path("rate").put(Rate.class, rate);
    }

    public void remove(String name) {
        webResource.path("resources").path("rate").path(name).delete();
    }

    public Rate find(String name) {
        return webResource.path("resources").path("rate").path(name).get(Rate.class);
    }

    public Collection<Rate> findAll() {
        return webResource.path("resources").path("rate").get(new GenericType<Collection<Rate>>() { });
    }
    
    public Collection<Rate> findAllHighwayRates()
    {
        Collection<Rate> rates = new ArrayList<>();
        for (Rate r : webResource.path("resources").path("rate").get(new GenericType<Collection<Rate>>() { }))
        {
            if (r.getClass() == Rate.class) {
                rates.add((Rate)r);
            }
        }
        return rates;
    }
    
    public Collection<Rate> findAllCityRates()
    {
        Collection<Rate> rates = new ArrayList<>();
        for (Rate r : webResource.path("resources").path("rate").get(new GenericType<Collection<Rate>>() { }))
        {
            if (r.getClass() == CityRate.class) {
                rates.add((CityRate)r);
            }
        }
        return rates;
    }
    
    public Collection<RegionRate> findAllRegionRates()
    {
        Collection<RegionRate> rates = new ArrayList<>();
        for (Rate r : webResource.path("resources").path("rate").get(new GenericType<Collection<Rate>>() { }))
        {
            if (r.getClass() == RegionRate.class) {
                rates.add((RegionRate)r);
            }
        }
        return rates;
    }
    
    public Collection<VehicleRate> findAllVehicleRates()
    {
        Collection<VehicleRate> rates = new ArrayList<>();
        for (Rate r : webResource.path("resources").path("rate").get(new GenericType<Collection<Rate>>() { }))
        {
            if (r.getClass() == VehicleRate.class) {
                rates.add((VehicleRate)r);
            }
        }
        return rates;
    }
    
    public MassRate findMassRate()
    {
        MassRate rate = new MassRate();
        for (Rate r : webResource.path("resources").path("rate").get(new GenericType<Collection<Rate>>() { }))
        {
            if (r.getClass() == MassRate.class) {
                rate = (MassRate)r;
            }
        }
        return rate;
    }

    public Collection<Rate> findRange(Integer from, Integer to) {
        return webResource.path("resources").path("rate").path(from.toString()).path(to.toString()).get(new GenericType<Collection<Rate>>() { });
    }

    public int count() {
        return Integer.parseInt(webResource.path("resources").path("rate").path("count").get(String.class));
    }
}
