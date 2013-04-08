package administration.dao;

import administration.domain.Employee;
import java.util.Collection;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.ws.rs.*;

@Stateless
@Path("/employee")
public class EmployeeDAO extends AbstractDAO<Employee, String> {

    @PersistenceContext(unitName = "AdministrationPU")
    private EntityManager em;

    public EmployeeDAO() {
        super(Employee.class);
    }

    @POST
    @Override
    @Consumes({"application/xml", "application/json"})
    public void create(Employee entity) {
        super.create(entity);
    }

    @PUT
    @Override
    @Consumes({"application/xml", "application/json"})
    public Employee edit(Employee entity) {
        return super.edit(entity);
    }

    @DELETE
    @Path("{id}")
    public void remove(@PathParam("id") String name) {
        super.remove(super.find(name));
    }

    @GET
    @Override
    @Path("{id}")
    @Produces({"application/xml", "application/json"})
    public Employee find(@PathParam("id") String name) {
        return super.find(name);
    }

    @GET
    @Override
    @Produces({"application/xml", "application/json"})
    public Collection<Employee> findAll() {
        return super.findAll();
    }

    @GET
    @Path("{from}/{to}")
    @Produces({"application/xml", "application/json"})
    public Collection<Employee> findRange(@PathParam("from") Integer from, @PathParam("to") Integer to) {
        return super.findRange(new int[]{from, to});
    }

    @GET
    @Path("count")
    @Produces("text/plain")
    public String countREST() {
        return String.valueOf(super.count());
    }

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }
}
