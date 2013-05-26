package com.marbl.administration.backend.service;

//<editor-fold defaultstate="collapsed" desc="Imports">
import com.marbl.administration.backend.dao.EmployeeDAO;
import com.marbl.administration.domain.Employee;
import java.io.Serializable;
import java.util.ArrayList;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
//</editor-fold>

@Stateless
@Path("/employees")
public class EmployeeService implements Serializable {
    
    @Inject
    private EmployeeDAO employeeDAO;

    @POST
    @Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Response create(Employee employee) {
        employeeDAO.create(employee);
        return Response.ok().build();
    }

    @PUT
    @Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Employee edit(Employee employee) {
        return employeeDAO.edit(employee);
    }

    @DELETE
    @Path("{id}")
    public Response remove(@PathParam("id") String name) {
        employeeDAO.remove(employeeDAO.find(name));
        return Response.ok().build();
    }

    @GET
    @Path("{id}")
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Employee find(@PathParam("id") String name) {
        return employeeDAO.find(name);
    }

    @GET
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public ArrayList<Employee> findAll() {
        return employeeDAO.findAll();
    }

    @GET
    @Path("count")
    @Produces(MediaType.TEXT_PLAIN)
    public String count() {
        return String.valueOf(employeeDAO.count());
    }
}
