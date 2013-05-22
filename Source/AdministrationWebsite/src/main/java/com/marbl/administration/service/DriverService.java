package com.marbl.administration.service;

import com.marbl.administration.domain.Driver;
import com.sun.jersey.api.client.*;
import com.sun.jersey.api.client.config.*;
import java.io.Serializable;
import java.util.Collection;
import javax.annotation.PostConstruct;
import javax.ejb.Stateless;

@Stateless
public class DriverService implements Serializable {

    private WebResource webResource;

    @PostConstruct
    public void postConstruct() {
        ClientConfig config = new DefaultClientConfig();
        Client client = Client.create(config);
        webResource = client.resource("http://localhost:8080/AdministrationService/");
    }

    public void create(Driver driver) {
        webResource.path("resources").path("driver").post(Driver.class, driver);
    }

    public Driver edit(Driver driver) {
        return webResource.path("resources").path("driver").put(Driver.class, driver);
    }

    public void remove(int bsn) {
        webResource.path("resources").path("driver").path(Integer.toString(bsn)).delete();
    }

    public Driver find(int bsn) {
        return webResource.path("resources").path("driver").path(Integer.toString(bsn)).get(Driver.class);
    }

    public Collection<Driver> findAll() {
        return webResource.path("resources").path("driver").get(new GenericType<Collection<Driver>>() { });
    }

    public Collection<Driver> findRange(Integer from, Integer to) {
        return webResource.path("resources").path("driver").path(from.toString()).path(to.toString()).get(new GenericType<Collection<Driver>>() { });
    }

    public int count() {
        return Integer.parseInt(webResource.path("resources").path("driver").path("count").get(String.class));
    }
}
