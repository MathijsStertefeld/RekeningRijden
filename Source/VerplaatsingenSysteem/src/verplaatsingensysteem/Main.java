/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package verplaatsingensysteem;

import database.Database;
import domain.Edge;
import domain.Lane;
import domain.Session;
import domain.TimeStep;
import domain.VehiclePosition;
import java.io.IOException;
import java.sql.SQLException;
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

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args)
    {
        XMLParser parser = null;
        try
        {
            parser = new XMLParser("verplaatsing_19901218.xml");
        }
        catch (SAXException | IOException | ParserConfigurationException ex)
        {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }

        //Test read
        Session s = parser.readMovementXML();

        System.out.println("Session was started on " + s.getSessionDate().toString());

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

        for (TimeStep ts : s.getTimesteps())
        {
            try
            {
                Database.writeToDatabase(ts, s);
            }
            catch (ClassNotFoundException ex)
            {
                Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
            }
            catch (SQLException ex)
            {
                Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
}
