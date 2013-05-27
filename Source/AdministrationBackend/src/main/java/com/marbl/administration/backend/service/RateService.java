package com.marbl.administration.backend.service;

//<editor-fold defaultstate="collapsed" desc="Imports">
import com.marbl.administration.backend.dao.RateDAO;
import com.marbl.administration.domain.Rate;
import java.io.Serializable;
import java.util.ArrayList;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
//</editor-fold>

// Use this service to create, edit, delete or find rates.

@Stateless
@Path("rates")
public class RateService implements Serializable {

    //<editor-fold defaultstate="collapsed" desc="Fields">
    @Inject
    private RateDAO rateDAO;
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="Methods">
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response create(Rate rate) {
        rateDAO.create(rate);
        return Response.status(Response.Status.CREATED).entity(rate).build();
    }

    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response edit(Rate rate) {
        rate = rateDAO.edit(rate);
        return Response.status(Response.Status.OK).entity(rate).build();
    }

    @DELETE
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response remove(Rate rate) {
        rateDAO.remove(rate);
        return Response.status(Response.Status.OK).entity(rate).build();
    }

    @DELETE
    @Path("{name}")
    @Consumes(MediaType.TEXT_PLAIN)
    @Produces(MediaType.APPLICATION_JSON)
    public Response remove(@PathParam("name") String name) {
        Rate rate = rateDAO.find(name);
        return remove(rate);
    }

    @GET
    @Path("{name}")
    @Consumes(MediaType.TEXT_PLAIN)
    @Produces(MediaType.APPLICATION_JSON)
    public Response find(@PathParam("name") String name) {
        Rate rate = rateDAO.find(name);
        return Response.status(Response.Status.OK).entity(rate).build();
    }

    @GET
    @Consumes(MediaType.TEXT_PLAIN)
    @Produces(MediaType.APPLICATION_JSON)
    public Response findAll(
            @QueryParam("name") String name) {

        ArrayList<Rate> rates = new ArrayList();

        for (Rate rate : rateDAO.findAll()) {
            if (true
                    && (name == null || name.equals(rate.getName()))) {
                rates.add(rate);
            }
        }

        return Response.status(Response.Status.OK).entity(rates).build();
    }

    @GET
    @Path("count")
    @Consumes(MediaType.TEXT_PLAIN)
    @Produces(MediaType.TEXT_PLAIN)
    public Response count() {
        String s = String.valueOf(rateDAO.count());
        return Response.status(Response.Status.OK).entity(s).build();
    }
    //</editor-fold>
}
