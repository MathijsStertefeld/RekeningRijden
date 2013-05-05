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
        VehiclePosition vp = verplaatsingSysteemService.getVehiclePosition(cartrackerId);

        if (vp != null)
        {
            return vp;
        }
        else
        {
            throw new NullPointerException("VehiclePosition with cartrackerId " + cartrackerId + " cannot be found.");
        }
    }
    
    @Path("getEdge/{edgeId}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Edge getEdge(@PathParam("edgeId") String edgeId)
    {
        Edge edge = verplaatsingSysteemService.getEdge(edgeId);
        
        if (edge != null)
        {
            return edge;
        }
        else
        {
            throw new NullPointerException("Edge with id " + edgeId + " cannot be found.");     
        }
        
    }
    
    @Path("getLane/{laneId}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Lane getLane(@PathParam("laneId") String laneId)
    {
        Lane lane = verplaatsingSysteemService.getLane(laneId);

        if (lane != null)
        {
            return lane;
        }
        else
        {
            throw new NullPointerException("Lane with id " + laneId + " cannot be found.");
        }
    }

    @Path("getTimeStep/{time}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public TimeStep getTimeStep(@PathParam("time") double time)
    {
        TimeStep timeStep = verplaatsingSysteemService.getTimeStep(time);

        if (timeStep != null)
        {
            return timeStep;
        }
        else
        {
            throw new NullPointerException("Timestep with time " + time + " cannot be found.");
        }
    }
}
