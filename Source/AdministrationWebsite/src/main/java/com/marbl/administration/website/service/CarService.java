package com.marbl.administration.website.service;

import com.marbl.administration.domain.Car;
import com.sun.jersey.api.client.*;
import com.sun.jersey.api.client.config.*;
import java.io.Serializable;
import java.util.Collection;
import javax.annotation.PostConstruct;
import javax.ejb.Stateless;
import javax.ws.rs.core.MediaType;

@Stateless
public class CarService implements Serializable {

    private WebResource webResource;

    @PostConstruct
    public void postConstruct() {
        ClientConfig config = new DefaultClientConfig();
        Client client = Client.create(config);
        webResource = client.resource("http://localhost:8080/AdministrationBackend/");
    }

    public Car edit(Car car) {
        return webResource.path("resources").path("cars")
                .accept(MediaType.APPLICATION_JSON).put(Car.class, car);
    }

    public Car find(String carTrackerId) {
        return webResource.path("resources").path("cars").path(carTrackerId)
                .accept(MediaType.APPLICATION_JSON).get(Car.class);
    }

    public Collection<Car> findAll() {
        return webResource.path("resources").path("cars")
                .accept(MediaType.APPLICATION_JSON).get(new GenericType<Collection<Car>>() { });
    }
}
