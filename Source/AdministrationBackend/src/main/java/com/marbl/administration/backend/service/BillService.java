package com.marbl.administration.backend.service;

import com.marbl.administration.backend.dao.BillDAO;
import com.marbl.administration.domain.Bill;
import java.io.Serializable;
import java.util.Collection;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.ws.rs.*;

@Stateless
@Path("/bill")
public class BillService implements Serializable {

    @Inject
    private BillDAO billDAO;

    @POST
    @Consumes({"application/xml", "application/json"})
    public Bill create(Bill entity) {
        billDAO.create(entity);
        return entity;
    }

    @PUT
    @Consumes({"application/xml", "application/json"})
    public Bill edit(Bill entity) {
        return billDAO.edit(entity);
    }

    @DELETE
    @Path("{id}")
    public void remove(@PathParam("id") Long id) {
        billDAO.remove(billDAO.find(id));
    }

    @GET
    @Path("{id}")
    @Produces({"application/xml", "application/json"})
    public Bill find(@PathParam("id") Long id) {
        return billDAO.find(id);
    }

    @GET
    @Produces({"application/xml", "application/json"})
    public Collection<Bill> findAll() {
        return billDAO.findAll();
    }

    @GET
    @Path("{from}/{to}")
    @Produces({"application/xml", "application/json"})
    public Collection<Bill> findRange(@PathParam("from") Integer from, @PathParam("to") Integer to) {
        return billDAO.findRange(new int[]{from, to});
    }

    @GET
    @Path("count")
    @Produces("text/plain")
    public String count() {
        return String.valueOf(billDAO.count());
    }
}
