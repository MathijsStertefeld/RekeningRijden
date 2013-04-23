package administration.service;

import administration.dao.EmployeeDAO;
import administration.domain.Employee;
import java.io.Serializable;
import java.util.Collection;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.ws.rs.*;

@Stateless
@Path("/employee")
public class EmployeeService implements Serializable {
    
    @Inject
    private EmployeeDAO employeeDAO;

    @POST
    @Consumes({"application/xml", "application/json"})
    public void create(Employee entity) {
        employeeDAO.create(entity);
    }

    @PUT
    @Consumes({"application/xml", "application/json"})
    public Employee edit(Employee entity) {
        return employeeDAO.edit(entity);
    }

    @DELETE
    @Path("{id}")
    public void remove(@PathParam("id") String name) {
        employeeDAO.remove(employeeDAO.find(name));
    }

    @GET
    @Path("{id}")
    @Produces({"application/xml", "application/json"})
    public Employee find(@PathParam("id") String name) {
        return employeeDAO.find(name);
    }

    @GET
    @Produces({"application/xml", "application/json"})
    public Collection<Employee> findAll() {
        return employeeDAO.findAll();
    }

    @GET
    @Path("{from}/{to}")
    @Produces({"application/xml", "application/json"})
    public Collection<Employee> findRange(@PathParam("from") Integer from, @PathParam("to") Integer to) {
        return employeeDAO.findRange(new int[]{from, to});
    }

    @GET
    @Path("count")
    @Produces("text/plain")
    public String count() {
        return String.valueOf(employeeDAO.count());
    }
}
