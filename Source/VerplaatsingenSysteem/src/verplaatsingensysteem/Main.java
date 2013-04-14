/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package verplaatsingensysteem;

import service.VerplaatsingSysteemService;
import domain.Edge;
import domain.Lane;
import domain.Session;
import domain.TimeStep;
import domain.VehiclePosition;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.parsers.ParserConfigurationException;
import org.xml.sax.SAXException;

/**
 *
 * @author Leslie Aerts
 */
public class Main
{

    private static VerplaatsingSysteemService vpService;

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args)
    {
        System.out.println("Creating service...");
        vpService = new VerplaatsingSysteemService();
        XMLParser parser = null;
        try
        {
            parser = new XMLParser("verplaatsing_19901218.xml");

        } catch (SAXException | IOException | ParserConfigurationException ex)
        {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }

        //Test read
        System.out.println("Reading XML...");
        Session s = parser.readMovementXML();
        System.out.println("Done reading XML.  Simulation started on " + s.getSessionDate().toString());
        try
        {
            testDatabasePersistMethod1(s);
        } catch (SAXException ex)
        {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);

        } catch (IOException | ParserConfigurationException ex)
        {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }
        try
        {
            //       System.out.println("Test Database persist.");
            //       vpService.createSession(s);

            //       System.out.println("Persisting timesteps...");
    //        for (TimeStep ts : s.getTimesteps())
    //        {
    //            vpService.createTimeStep(ts);
    //        }
            printXML();
        } catch (SAXException | IOException | ParserConfigurationException ex)
        {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }
        System.out.println("All done!");
    }

    public static void printXML() throws SAXException, IOException, ParserConfigurationException
    {
        System.out.println("Printing entire XML.");
        XMLParser parser = new XMLParser("verplaatsing_19901218.xml");

        Session s = parser.readMovementXML();

        for (TimeStep ts : s.getTimesteps())
        {
            System.out.println("Timestep " + ts.getTime());
            for (Edge e : ts.getEdges())
            {
                System.out.println("     Edge " + e.getId());
                for (Lane l : e.getLanes())
                {

                    System.out.println("          Lane " + l.getId());
                    for (VehiclePosition pos : l.getPositions())
                    {
                        System.out.println("                         Car" + pos.getCarPos() + " " + pos.getCarTrackerId() + " " + pos.getCarSpeed());
                    }
                }
            }
        }
    }

    public static void testDatabasePersistMethod1(Session s) throws SAXException, SAXException, IOException, ParserConfigurationException
    {
        vpService.createSession(s);

        for (TimeStep step : s.getTimesteps())
        {
            vpService.createTimeStep(step);

            for (Edge e : step.getEdges())
            {
                vpService.createEdge(e);
                for (Lane l : e.getLanes())
                {
                    vpService.createLane(l);
                    for(VehiclePosition p : l.getPositions())
                    {
                        vpService.createVehiclePosition(p);
                    }
                }
            }
        }
    }
}
