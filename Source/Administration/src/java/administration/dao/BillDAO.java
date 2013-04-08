package administration.dao;

import administration.domain.Bill;
import java.util.Collection;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.ws.rs.*;

@Stateless
@Path("/bill")
public class BillDAO extends AbstractDAO<Bill, Integer> {

    @PersistenceContext(unitName = "AdministrationPU")
    private EntityManager em;

    public BillDAO() {
        super(Bill.class);
    }

    @POST
    @Override
    @Consumes({"application/xml", "application/json"})
    public void create(Bill entity) {
        super.create(entity);
    }

    @PUT
    @Override
    @Consumes({"application/xml", "application/json"})
    public Bill edit(Bill entity) {
        return super.edit(entity);
    }

    @DELETE
    @Path("{id}")
    public void remove(@PathParam("id") Integer id) {
        super.remove(super.find(id));
    }

    @GET
    @Override
    @Path("{id}")
    @Produces({"application/xml", "application/json"})
    public Bill find(@PathParam("id") Integer id) {
        return super.find(id);
    }

    @GET
    @Override
    @Produces({"application/xml", "application/json"})
    public Collection<Bill> findAll() {
        return super.findAll();
    }

    @GET
    @Path("{from}/{to}")
    @Produces({"application/xml", "application/json"})
    public Collection<Bill> findRange(@PathParam("from") Integer from, @PathParam("to") Integer to) {
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
