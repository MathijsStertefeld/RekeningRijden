package administration.service;

import administration.dao.RateDAO;
import administration.domain.Rate;
import java.io.Serializable;
import java.util.Collection;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.ws.rs.*;

@Stateless
@Path("/rate")
public class RateService implements Serializable {
    
    @Inject
    private RateDAO rateDAO;

    @POST
    @Consumes({"application/xml", "application/json"})
    public void create(Rate entity) {
        rateDAO.create(entity);
    }

    @PUT
    @Consumes({"application/xml", "application/json"})
    public Rate edit(Rate entity) {
        return rateDAO.edit(entity);
    }

    @DELETE
    @Path("{id}")
    public void remove(@PathParam("id") String name) {
        rateDAO.remove(rateDAO.find(name));
    }

    @GET
    @Path("{id}")
    @Produces({"application/xml", "application/json"})
    public Rate find(@PathParam("id") String id) {
        return rateDAO.find(id);
    }

    @GET
    @Produces({"application/xml", "application/json"})
    public Collection<Rate> findAll() {
        return rateDAO.findAll();
    }

    @GET
    @Path("{from}/{to}")
    @Produces({"application/xml", "application/json"})
    public Collection<Rate> findRange(@PathParam("from") Integer from, @PathParam("to") Integer to) {
        return rateDAO.findRange(new int[]{from, to});
    }

    @GET
    @Path("count")
    @Produces("text/plain")
    public String count() {
        return String.valueOf(rateDAO.count());
    }
}
