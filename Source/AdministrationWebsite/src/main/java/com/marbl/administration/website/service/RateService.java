package com.marbl.administration.website.service;

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
        webResource = client.resource("http://192.168.30.185:8080/AdministrationBackend/resources/");
    }

    public Rate edit(Rate rate) {
        return webResource.path("rates").put(Rate.class, rate);
    }

    public Rate find(String name) {
        return webResource.path("rates").path(name).get(Rate.class);
    }

    public Collection<Rate> findAll() {
        return webResource.path("rates").get(new GenericType<Collection<Rate>>() { });
    }
}
