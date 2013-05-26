/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.marbl.verplaatsingsysteem.service;

import com.marbl.autosysteem.Movement;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import com.marbl.verplaatsingsysteem.XMLParser;
import com.marbl.autosysteem.Session;
import com.marbl.autosysteem.TimeStep;

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

    @GET
    @Produces(MediaType.APPLICATION_XML)
    @Path("get_session")
    public Session test2()
    {
        Session s = null;
        try
        {
            //Werkt niet meteen. Bestand moet in glassfish folder. Zie exception output of vraag naar Leslie.
            XMLParser parser = new XMLParser("verplaatsing_19901218.xml");
            System.out.println("Server: File found.");
            System.out.println("Server: Starting XML to POJO.");
            s = parser.readMovementXML();
            System.out.println("Server: Done. Succesfully parsed.");
            //System.out.println("Server: Test; parent session of timestep 1 is" + s.getTimeStep(1).getParentSession());
        } catch (Exception e)
        {
            e.printStackTrace();
        }
        return s;
    }

    @POST
    @Consumes(MediaType.APPLICATION_XML)
    public void sendXML(Session s)
    {
        System.out.println(s);
        System.out.println("Accepting file...");

        
        vpService.createSession(s);
        for(TimeStep ts : s.getTimesteps())
        {
            vpService.createTimeStep(ts);
            for(Movement m : ts.getMovements())
            {
                vpService.createMovement(m);
            }
        }
        System.out.println("Done.");
    }
}
