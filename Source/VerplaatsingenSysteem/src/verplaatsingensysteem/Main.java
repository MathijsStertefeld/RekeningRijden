/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package verplaatsingensysteem;

import domain.Edge;
import domain.Lane;
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

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args)
    {
        XMLParser parser = null;
        try
        {
            parser = new XMLParser("testverplaatsing.xml");
        } catch (SAXException | IOException | ParserConfigurationException ex)
        {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }


        for (TimeStep ts : parser.readMovementXML())
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
                        System.out.println("                         Car" + pos.getCarPos() +" " + pos.getCarTrackerId() + " " + pos.getCarSpeed());
                    }
                }
            }
        }

    }
}
