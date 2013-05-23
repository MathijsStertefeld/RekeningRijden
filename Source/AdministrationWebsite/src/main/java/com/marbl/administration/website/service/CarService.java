package com.marbl.administration.website.service;

import com.marbl.administration.domain.Car;
import com.sun.jersey.api.client.*;
import com.sun.jersey.api.client.config.*;
import java.io.Serializable;
import java.util.Collection;
import javax.annotation.PostConstruct;
import javax.ejb.Stateless;

@Stateless
public class CarService implements Serializable {

    private WebResource webResource;

    @PostConstruct
    public void postConstruct() {
        ClientConfig config = new DefaultClientConfig();
        Client client = Client.create(config);
        webResource = client.resource("http://localhost:8080/AdministrationBackend/");
    }

    public void create(Car car) {
        webResource.path("resources").path("car").post(Car.class, car);
    }

    public Car edit(Car car) {
        return webResource.path("resources").path("car").put(Car.class, car);
    }

    public void remove(String carTrackerId) {
        webResource.path("resources").path("car").path(carTrackerId).delete();
    }

    public Car find(String carTrackerId) {
        return webResource.path("resources").path("car").path(carTrackerId).get(Car.class);
    }

    public Collection<Car> findAll() {
        return webResource.path("resources").path("car").get(new GenericType<Collection<Car>>() { });
    }

    public Collection<Car> findRange(Integer from, Integer to) {
        return webResource.path("resources").path("car").path(from.toString()).path(to.toString()).get(new GenericType<Collection<Car>>() { });
    }

    public int count() {
        return Integer.parseInt(webResource.path("resources").path("car").path("count").get(String.class));
    }
}
