/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package verplaatsingensysteem.service;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.xml.transform.sax.SAXSource;
import verplaatsingensysteem.XMLParser;
import verplaatsingensysteem.domain.Session;

/**
 *
 * @author Leslie Aerts
 */
@Path("/xml")
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

    
    //DEZE WERKT.
    @GET
    @Produces(MediaType.APPLICATION_XML)
    @Path("get_session")
    public Session test2()
    {
        Session s = null;
        try
        {
            XMLParser parser = new XMLParser("verplaatsing_19901218.xml");
             s = parser.readMovementXML();
          
            System.out.println("Timesteps are " + s.getTimesteps());
        } catch (Exception e)
        {
            
        }
        return s;
    }

    @POST
    @Consumes(MediaType.APPLICATION_XML)
    public Session sendXML(Session s)
    {
        System.out.println("Accepting file...");
        System.out.println(s);
        System.out.println("Done.");
        return s;
    }

    @POST
    @Consumes(MediaType.APPLICATION_XML)
    @Path("alt")
    public SAXSource sendXML(SAXSource s)
    {

        return s;
    }
}
