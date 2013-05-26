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
@Path("employees")
public class EmployeeService implements Serializable {

    //<editor-fold defaultstate="collapsed" desc="Fields">
    @Inject
    private EmployeeDAO employeeDAO;
    //</editor-fold>

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response create(Employee employee) {
        employeeDAO.create(employee);
        return Response.status(Response.Status.CREATED).entity(employee).build();
    }

    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response edit(Employee employee) {
        employee = employeeDAO.edit(employee);
        return Response.status(Response.Status.OK).entity(employee).build();
    }

    @DELETE
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response remove(Employee employee) {
        employeeDAO.remove(employee);
        return Response.status(Response.Status.OK).entity(employee).build();
    }

    @DELETE
    @Path("{username}")
    @Consumes(MediaType.TEXT_PLAIN)
    @Produces(MediaType.APPLICATION_JSON)
    public Response remove(@PathParam("username") String username) {
        Employee employee = employeeDAO.find(username);
        return remove(employee);
    }

    @GET
    @Path("{username}")
    @Consumes(MediaType.TEXT_PLAIN)
    @Produces(MediaType.APPLICATION_JSON)
    public Response find(@PathParam("username") String username) {
        Employee employee = employeeDAO.find(username);
        return Response.status(Response.Status.OK).entity(employee).build();
    }

    @GET
    @Consumes(MediaType.TEXT_PLAIN)
    @Produces(MediaType.APPLICATION_JSON)
    public Response findAll(
            @QueryParam("username") String username) {

        ArrayList<Employee> employees = new ArrayList();

        for (Employee employee : employeeDAO.findAll()) {
            if (true
                    && (username == null || username.equals(employee.getUsername()))) {
                employees.add(employee);
            }
        }

        return Response.status(Response.Status.OK).entity(employees).build();
    }

    @GET
    @Path("count")
    @Consumes(MediaType.TEXT_PLAIN)
    @Produces(MediaType.TEXT_PLAIN)
    public Response count() {
        String s = String.valueOf(employeeDAO.count());
        return Response.status(Response.Status.OK).entity(s).build();
    }
    
    @POST
    @Path("login")
    @Consumes(MediaType.TEXT_PLAIN)
    @Produces(MediaType.APPLICATION_JSON)
    public Response login(
            @QueryParam("username") String username,
            @QueryParam("password") String password) {
        
        for (Employee employee : employeeDAO.findAll()) {
            if (employee.getUsername().equals(username)) {
                if (employee.getPassword().equals(password)) {
                    return Response.status(Response.Status.OK).entity(employee).build();
                } else {
                    break;
                }
            }
        }
        
        return Response.status(Response.Status.NOT_FOUND).build();
    }
}
