package administration.dao;

import administration.domain.Driver;
import java.util.Collection;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.ws.rs.*;

@Stateless
@Path("/driver")
public class DriverDAO extends AbstractDAO<Driver, Integer> {

    @PersistenceContext(unitName = "AdministrationPU")
    private EntityManager em;

    public DriverDAO() {
        super(Driver.class);
    }

    @POST
    @Override
    @Consumes({"application/xml", "application/json"})
    public void create(Driver entity) {
        super.create(entity);
    }

    @PUT
    @Override
    @Consumes({"application/xml", "application/json"})
    public Driver edit(Driver entity) {
        return super.edit(entity);
    }

    @DELETE
    @Path("{id}")
    public void remove(@PathParam("id") Integer bsn) {
        super.remove(super.find(bsn));
    }

    @GET
    @Override
    @Path("{id}")
    @Produces({"application/xml", "application/json"})
    public Driver find(@PathParam("id") Integer bsn) {
        return super.find(bsn);
    }

    @GET
    @Override
    @Produces({"application/xml", "application/json"})
    public Collection<Driver> findAll() {
        return super.findAll();
    }

    @GET
    @Path("{from}/{to}")
    @Produces({"application/xml", "application/json"})
    public Collection<Driver> findRange(@PathParam("from") Integer from, @PathParam("to") Integer to) {
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
