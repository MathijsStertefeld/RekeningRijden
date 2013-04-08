package administration.dao;

import administration.domain.Car;
import java.util.Collection;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.ws.rs.*;

@Stateless
@Path("/car")
public class CarDAO extends AbstractDAO<Car, String> {

    @PersistenceContext(unitName = "AdministrationPU")
    private EntityManager em;

    public CarDAO() {
        super(Car.class);
    }

    @POST
    @Override
    @Consumes({"application/xml", "application/json"})
    public void create(Car entity) {
        super.create(entity);
    }

    @PUT
    @Override
    @Consumes({"application/xml", "application/json"})
    public Car edit(Car entity) {
        return super.edit(entity);
    }

    @DELETE
    @Path("{id}")
    public void remove(@PathParam("id") String carTrackerId) {
        super.remove(super.find(carTrackerId));
    }

    @GET
    @Override
    @Path("{id}")
    @Produces({"application/xml", "application/json"})
    public Car find(@PathParam("id") String carTrackerId) {
        return super.find(carTrackerId);
    }

    @GET
    @Override
    @Produces({"application/xml", "application/json"})
    public Collection<Car> findAll() {
        return super.findAll();
    }

    @GET
    @Path("{from}/{to}")
    @Produces({"application/xml", "application/json"})
    public Collection<Car> findRange(@PathParam("from") Integer from, @PathParam("to") Integer to) {
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
