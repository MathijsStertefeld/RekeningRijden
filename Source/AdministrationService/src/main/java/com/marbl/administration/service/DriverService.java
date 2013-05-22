package com.marbl.administration.service;

import com.marbl.administration.dao.DriverDAO;
import com.marbl.administration.domain.Driver;
import java.io.Serializable;
import java.util.Collection;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.ws.rs.*;

@Stateless
@Path("/driver")
public class DriverService implements Serializable {
    
    @Inject
    private DriverDAO driverDAO;

    @POST
    @Consumes({"application/xml", "application/json"})
    public Driver create(Driver entity) {
        driverDAO.create(entity);
        return entity;
    }

    @PUT
    @Consumes({"application/xml", "application/json"})
    public Driver edit(Driver entity) {
        return driverDAO.edit(entity);
    }

    @DELETE
    @Path("{id}")
    public void remove(@PathParam("id") Integer bsn) {
        driverDAO.remove(driverDAO.find(bsn));
    }

    @GET
    @Path("{id}")
    @Produces({"application/xml", "application/json"})
    public Driver find(@PathParam("id") Integer bsn) {
        return driverDAO.find(bsn);
    }
    
    @GET
    @Path("email/{email}")
    @Produces({"application/xml", "application/json"})
    public Driver findByEmail(@PathParam("email") String email) {
        return driverDAO.findDriverByEmail(email);    
    }

    @GET
    @Produces({"application/xml", "application/json"})
    public Collection<Driver> findAll() {
        return driverDAO.findAll();
    }

    @GET
    @Path("{from}/{to}")
    @Produces({"application/xml", "application/json"})
    public Collection<Driver> findRange(@PathParam("from") Integer from, @PathParam("to") Integer to) {
        return driverDAO.findRange(new int[]{from, to});
    }

    @GET
    @Path("count")
    @Produces("text/plain")
    public String count() {
        return String.valueOf(driverDAO.count());
    }
}
