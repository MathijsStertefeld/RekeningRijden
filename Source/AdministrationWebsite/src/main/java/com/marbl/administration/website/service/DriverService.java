package com.marbl.administration.website.service;

import com.marbl.administration.domain.Driver;
import com.sun.jersey.api.client.*;
import com.sun.jersey.api.client.config.*;
import java.io.Serializable;
import java.util.ArrayList;
import javax.annotation.PostConstruct;
import javax.ejb.Stateless;

@Stateless
public class DriverService implements Serializable {

    private WebResource resource;

    @PostConstruct
    public void postConstruct() {
        ClientConfig config = new DefaultClientConfig();
        Client client = Client.create(config);
        resource = client.resource("http://192.168.30.185:8080/AdministrationBackend/resources/drivers/");
    }

    public Driver edit(Driver driver) {
        return resource.put(Driver.class, driver);
    }

    public Driver find(Integer bsn) {
        return resource.path(bsn.toString()).get(Driver.class);
    }

    public ArrayList<Driver> findAll() {
        return resource.get(new GenericType<ArrayList<Driver>>() { });
    }
}
