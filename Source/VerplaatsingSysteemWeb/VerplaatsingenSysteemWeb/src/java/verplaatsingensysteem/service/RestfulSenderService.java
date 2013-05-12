/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package verplaatsingensysteem.service;

import java.util.List;
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
@Path("/getInfo")
@Stateless
public class RestfulSenderService
{

    @Inject
    VerplaatsingSysteemService verplaatsingSysteemService;

    @Path("test")
    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String test()
    {
        return "Hallo, dit is een test";
    }
    
    
    @Path("getVehiclePosition/{cartrackerId}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<VehiclePosition> getVehiclePosition(@PathParam("cartrackerId") String cartrackerId)
    {
        System.out.println("CALLED: GetVehiclePositions");
        List<VehiclePosition> positions = verplaatsingSysteemService.getVehiclePositions(cartrackerId);
                System.out.println("LIST: " + positions);
        if (positions != null)
        {
            return positions;
        }
        else
        {
            throw new NullPointerException("VehiclePositions with cartrackerId " + cartrackerId + " cannot be found.");
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
            System.out.println(timeStep.getEdges());
            return timeStep;
        }
        else
        {
            throw new NullPointerException("Timestep with time " + time + " cannot be found.");
        }
    }
}
