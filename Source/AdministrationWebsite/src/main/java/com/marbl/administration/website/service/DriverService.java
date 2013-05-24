package com.marbl.administration.website.service;

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
        webResource = client.resource("http://192.168.30.185:8080/AdministrationBackend/resources/");
    }

    public Driver edit(Driver driver) {
        return webResource.path("drivers").put(Driver.class, driver);
    }

    public Driver find(Integer bsn) {
        return webResource.path("drivers").path(bsn.toString()).get(Driver.class);
    }

    public Collection<Driver> findAll() {
        return webResource.path("drivers").get(new GenericType<Collection<Driver>>() { });
    }
}
