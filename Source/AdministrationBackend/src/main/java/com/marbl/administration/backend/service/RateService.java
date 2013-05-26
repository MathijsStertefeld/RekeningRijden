package com.marbl.administration.backend.service;

//<editor-fold defaultstate="collapsed" desc="Imports">
import com.marbl.administration.backend.dao.RateDAO;
import com.marbl.administration.domain.Rate;
import java.io.Serializable;
import java.util.Collection;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
//</editor-fold>

@Stateless
@Path("/rates")
public class RateService implements Serializable {
    
    @Inject
    private RateDAO rateDAO;

    @POST
    @Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Response create(Rate rate) {
        rateDAO.create(rate);
        return Response.ok().build();
    }

    @PUT
    @Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Rate edit(Rate rate) {
        return rateDAO.edit(rate);
    }

    @DELETE
    @Path("{id}")
    public Response remove(@PathParam("id") String name) {
        rateDAO.remove(rateDAO.find(name));
        return Response.ok().build();
    }

    @GET
    @Path("{id}")
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Rate find(@PathParam("id") String id) {
        return rateDAO.find(id);
    }

    @GET
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Collection<Rate> findAll() {
        return rateDAO.findAll();
    }

    @GET
    @Path("{from}/{to}")
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Collection<Rate> findRange(@PathParam("from") Integer from, @PathParam("to") Integer to) {
        return rateDAO.findRange(new int[]{from, to});
    }

    @GET
    @Path("count")
    @Produces(MediaType.TEXT_PLAIN)
    public String count() {
        return String.valueOf(rateDAO.count());
    }
}
