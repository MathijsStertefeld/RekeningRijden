package com.marbl.administration.service;

import com.marbl.administration.domain.Rate;
import com.sun.jersey.api.client.*;
import com.sun.jersey.api.client.config.*;
import java.io.Serializable;
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
        webResource = client.resource("http://localhost:8080/AdministrationService/");
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

    public Collection<Rate> findRange(Integer from, Integer to) {
        return webResource.path("resources").path("rate").path(from.toString()).path(to.toString()).get(new GenericType<Collection<Rate>>() { });
    }

    public int count() {
        return Integer.parseInt(webResource.path("resources").path("rate").path("count").get(String.class));
    }
}
