package com.marbl.administration.backend.service;

//<editor-fold defaultstate="collapsed" desc="Imports">
import com.marbl.administration.backend.dao.CarDAO;
import com.marbl.administration.domain.Car;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
//</editor-fold>

@Stateless
@Path("/cars")
public class CarService implements Serializable {

    //<editor-fold defaultstate="collapsed" desc="Fields">
    @Inject
    private CarDAO carDAO;
    //</editor-fold>

    @POST
    @Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Response create(Car car) {
        carDAO.create(car);
        return Response.ok().build();
    }

    @PUT
    @Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Car edit(Car car) {
        return carDAO.edit(car);
    }

    @DELETE
    @Path("{carTrackerId}")
    public Response remove(@PathParam("carTrackerId") String carTrackerId) {
        carDAO.remove(carDAO.find(carTrackerId));
        return Response.ok().build();
    }

    @GET
    @Path("{carTrackerId}")
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Car find(@PathParam("carTrackerId") String carTrackerId) {
        return carDAO.find(carTrackerId);
    }

    @GET
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Collection<Car> findAll(
            @QueryParam("carTrackerId") String carTrackerId,
            @QueryParam("driverBsn") Integer driverBsn,
            @QueryParam("licensePlate") String licensePlate) {

        Collection<Car> cars = new ArrayList<>();

        for (Car car : carDAO.findAll()) {
            if (true
                    && (carTrackerId == null || carTrackerId.equals(car.getCarTrackerId()))
                    && (driverBsn == null || driverBsn == car.getDriverBsn())
                    && (licensePlate == null || licensePlate.equals(car.getLicensePlate()))) {
                cars.add(car);
            }
        }

        return cars;
    }

    @GET
    @Path("count")
    @Produces("text/plain")
    public String count() {
        return String.valueOf(carDAO.count());
    }
}
