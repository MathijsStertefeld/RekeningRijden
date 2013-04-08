package administration.dao;

import administration.domain.Rate;
import java.util.Collection;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.ws.rs.*;

@Stateless
@Path("/rate")
public class RateDAO extends AbstractDAO<Rate, String> {

    @PersistenceContext(unitName = "AdministrationPU")
    private EntityManager em;

    public RateDAO() {
        super(Rate.class);
    }

    @POST
    @Override
    @Consumes({"application/xml", "application/json"})
    public void create(Rate entity) {
        super.create(entity);
    }

    @PUT
    @Override
    @Consumes({"application/xml", "application/json"})
    public Rate edit(Rate entity) {
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
    public Rate find(@PathParam("id") String id) {
        return super.find(id);
    }

    @GET
    @Override
    @Produces({"application/xml", "application/json"})
    public Collection<Rate> findAll() {
        return super.findAll();
    }

    @GET
    @Path("{from}/{to}")
    @Produces({"application/xml", "application/json"})
    public Collection<Rate> findRange(@PathParam("from") Integer from, @PathParam("to") Integer to) {
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
