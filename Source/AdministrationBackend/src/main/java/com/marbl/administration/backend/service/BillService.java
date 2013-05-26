package com.marbl.administration.backend.service;

//<editor-fold defaultstate="collapsed" desc="Imports">
import com.marbl.administration.backend.dao.BillDAO;
import com.marbl.administration.domain.Bill;
import java.io.Serializable;
import java.util.ArrayList;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
//</editor-fold>

@Stateless
@Path("/bills")
public class BillService implements Serializable {

    //<editor-fold defaultstate="collapsed" desc="Fields">
    @Inject
    private BillDAO billDAO;
    //</editor-fold>

    @POST
    @Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Response create(Bill bill) {
        billDAO.create(bill);
        return Response.ok().build();
    }

    @PUT
    @Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Bill edit(Bill bill) {
        return billDAO.edit(bill);
    }

    @DELETE
    @Path("{id}")
    public Response remove(@PathParam("id") Long id) {
        billDAO.remove(billDAO.find(id));
        return Response.ok().build();
    }

    @GET
    @Path("{id}")
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Bill find(@PathParam("id") Long id) {
        return billDAO.find(id);
    }

    @GET
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public ArrayList<Bill> findAll(
            @QueryParam("carTrackerId") String carTrackerId,
            @QueryParam("driverBSN") Integer driverBSN,
            @QueryParam("id") Long id) {

        ArrayList<Bill> bills = new ArrayList();

        for (Bill bill : billDAO.findAll()) {
            if (true
                    && (carTrackerId == null || carTrackerId.equals(bill.getCarTrackerId()))
                    && (driverBSN == null || driverBSN == bill.getDriverBSN())
                    && (id == null || id == bill.getID())) {
                bills.add(bill);
            }
        }

        return bills;
    }

    @GET
    @Path("count")
    @Produces(MediaType.TEXT_PLAIN)
    public String count() {
        return String.valueOf(billDAO.count());
    }
}
