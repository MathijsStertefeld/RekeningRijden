/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.marbl.verplaatsingsysteem.service;

import com.marbl.autosysteem.Movement;
import com.marbl.autosysteem.Session;
import com.marbl.autosysteem.TimeStep;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

/**
 *
 * @author Leslie Aerts
 */
@Path("/session")
@Stateless
public class RestfulReceiverService
{    
    @Inject
    VerplaatsingSysteemService vpService;

    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String test()
    {
        return "Hello, this is a test. Restful connection is working, atleast.";
    }

    @POST
    @Consumes(MediaType.APPLICATION_XML)
    public void sendXML(Session s)
    {
        System.out.println(s);
        System.out.println("Accepting file...");
        vpService.createSession(s);
        for (TimeStep ts : s.getTimesteps())
        {
            System.out.println("Timestep");
            vpService.createTimeStep(ts);
            System.out.println(ts.getMovements().size());
            for (Movement m : ts.getMovements())
            {
                System.out.println(m);
                vpService.createMovement(m);
            }
        }
        System.out.println("Done.");
    }
    
    @POST
    @Consumes(MediaType.APPLICATION_XML)
    @Path("send_timestep")
    public void sendXML(TimeStep s)
    {
        System.out.println(s);
        System.out.println("Accepting file...");
        vpService.createTimeStep(s);
        
        for(Movement m : s.getMovements())
        {
            vpService.createMovement(m);
        }
 
        System.out.println("Done.");
    }
}
