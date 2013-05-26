package com.marbl.administration.website.service;

import com.marbl.administration.domain.Car;
import com.sun.jersey.api.client.*;
import com.sun.jersey.api.client.config.*;
import java.io.Serializable;
import java.util.ArrayList;
import javax.annotation.PostConstruct;
import javax.ejb.Stateless;
import javax.ws.rs.core.MediaType;

@Stateless
public class CarService implements Serializable {

    private WebResource resource;

    @PostConstruct
    public void postConstruct() {
        ClientConfig config = new DefaultClientConfig();
        Client client = Client.create(config);
        resource = client.resource("http://192.168.30.185:8080/AdministrationBackend/resources/cars/");
    }

    public Car edit(Car car) {
        return resource.accept(MediaType.APPLICATION_JSON).put(Car.class, car);
    }

    public Car find(String carTrackerId) {
        return resource.path(carTrackerId).accept(MediaType.APPLICATION_JSON).get(Car.class);
    }

    public ArrayList<Car> findAll() {
        return resource.accept(MediaType.APPLICATION_JSON).get(new GenericType<ArrayList<Car>>() { });
    }
}
