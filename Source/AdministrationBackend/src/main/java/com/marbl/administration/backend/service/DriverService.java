package com.marbl.administration.backend.service;

//<editor-fold defaultstate="collapsed" desc="Imports">
import com.marbl.administration.backend.dao.DriverDAO;
import com.marbl.administration.domain.Driver;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import javax.ejb.Stateless;
import javax.faces.application.FacesMessage;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
//</editor-fold>

@Stateless
@Path("/drivers")
public class DriverService implements Serializable {
    
    //<editor-fold defaultstate="collapsed" desc="Fields">
    @Inject
    private DriverDAO driverDAO;
    //</editor-fold>

    @POST
    @Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Response create(Driver driver) {
        driverDAO.create(driver);
        return Response.ok().build();
    }

    @PUT
    @Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Driver edit(Driver driver) {
        return driverDAO.edit(driver);
    }

    @DELETE
    @Path("{id}")
    public Response remove(@PathParam("id") Integer bsn) {
        driverDAO.remove(driverDAO.find(bsn));
        return Response.ok().build();
    }

    @GET
    @Path("{id}")
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Driver find(@PathParam("id") Integer bsn) {
        return driverDAO.find(bsn);
    }

    @GET
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Collection<Driver> findAll(
            @QueryParam("bsn") Integer bsn,
            @QueryParam("email") String email,
            @QueryParam("firstName") String firstName,
            @QueryParam("lastName") String lastName,
            @QueryParam("residence") String residence) {
        
        Collection<Driver> drivers = new ArrayList<>();

        for (Driver driver : driverDAO.findAll()) {
            if (true
                    && (bsn == null || bsn == driver.getBSN())
                    && (email == null || email.equals(driver.getEmail()))
                    && (firstName == null || firstName.equals(driver.getFirstName()))
                    && (lastName == null || lastName.equals(driver.getLastName()))
                    && (residence == null || residence.equals(driver.getResidence()))) {
                drivers.add(driver);
            }
        }

        return drivers;
    }

    @GET
    @Path("count")
    @Produces(MediaType.TEXT_PLAIN)
    public String count() {
        return String.valueOf(driverDAO.count());
    }
    
    @POST
    @Path("login")
    @Consumes(MediaType.TEXT_PLAIN)
    @Produces(MediaType.APPLICATION_JSON)
    public Driver login(String email, String password) {
        FacesContext facesContext = FacesContext.getCurrentInstance();
        ExternalContext externalContext = facesContext.getExternalContext();
        HttpServletRequest request = (HttpServletRequest) externalContext.getRequest();
        
        for (Driver driver : driverDAO.findAll()) {
            if (driver.getEmail().equals(email)) {
                try {
                    String username = driver.getBSN().toString();
                    request.login(username, password);
                    return driver;
                } catch (ServletException ex) {
                    facesContext.addMessage(null, new FacesMessage(ex.getMessage()));
                }
                
                break;
            }
        }
        
        return null;
    }
}
