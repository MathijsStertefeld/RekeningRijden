/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package verplaatsingensysteem.service;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import verplaatsingensysteem.domain.*;


/**
 *
 * @author Eagle
 */
@Path("getInfo")
@Stateless
public class RestfulSenderService
{

    @Inject
    VerplaatsingSysteemService verplaatsingSysteemService;
    
    @Path("getVehiclePosition/{cartrackerId}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public VehiclePosition getVehiclePosition(@PathParam("cartrackerId") String cartrackerId)
    {
                
        
        return new VehiclePosition();
    }
    
    @Path("")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public TimeStep getTimeStep()
    {
        
        
        return new TimeStep();
    }
    
}
