package com.marbl.administration.backend.service;

import com.marbl.administration.backend.dao.CarDAO;
import com.marbl.administration.domain.Car;
import java.io.Serializable;
import java.util.Collection;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.ws.rs.*;

@Stateless
@Path("/car")
public class CarService implements Serializable {

    @Inject
    private CarDAO carDAO;

    @POST
    @Consumes({"application/xml", "application/json"})
    public Car create(Car entity) {
        carDAO.create(entity);
        return entity;
    }

    @PUT
    @Consumes({"application/xml", "application/json"})
    public Car edit(Car entity) {
        return carDAO.edit(entity);
    }

    @DELETE
    @Path("{carTrackerId}")
    public void remove(@PathParam("carTrackerId") String carTrackerId) {
        carDAO.remove(carDAO.find(carTrackerId));
    }

    @GET
    @Path("{carTrackerId}")
    @Produces({"application/xml", "application/json"})
    public Car find(@PathParam("carTrackerId") String carTrackerId) {
        return carDAO.find(carTrackerId);
    }

    @GET
    @Produces({"application/xml", "application/json"})
    public Collection<Car> findAll() {
        return carDAO.findAll();
    }

    @GET
    @Path("{from}/{to}")
    @Produces({"application/xml", "application/json"})
    public Collection<Car> findRange(@PathParam("from") Integer from, @PathParam("to") Integer to) {
        return carDAO.findRange(new int[]{from, to});
    }

    @GET
    @Path("count")
    @Produces("text/plain")
    public String count() {
        return String.valueOf(carDAO.count());
    }
}