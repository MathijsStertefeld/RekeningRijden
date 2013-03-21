/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package verplaatsingensysteem;

import domain.Edge;
import domain.Lane;
import domain.TimeStep;
import domain.VehiclePosition;
import java.awt.Label;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

/**
 *
 * @author Leslie Aerts
 */
public class XMLParser extends DefaultHandler
{

    SAXParser parser;
    private File xmlToRead;
    private ArrayList<TimeStep> timesteps;
    private TimeStep currentTimeStep;
    private Lane currentLane;

    public XMLParser(String fileName) throws SAXException, IOException, ParserConfigurationException
    {
        SAXParserFactory factory = SAXParserFactory.newInstance();
        parser = factory.newSAXParser();
        xmlToRead = new File(fileName);
    }

    public ArrayList<TimeStep> readMovementXML()
    {
        try
        {
            parser.parse(xmlToRead, this);
        } catch (SAXException | IOException ex)
        {
            Logger.getLogger(XMLParser.class.getName()).log(Level.SEVERE, null, ex);
        }

        return null;
    }

    @Override
    public void startElement(String s, String s1, String elementName, Attributes attributes) throws SAXException
    {
        currentTimeStep = new TimeStep();

        //If it gets to a new main element (Which is a timestep) create a new movement.
        if (elementName.equalsIgnoreCase("timestep"))
        {
            double timeStep = Double.parseDouble(attributes.getValue("time"));
            currentTimeStep.setTime(timeStep);
        }

        Edge e = null;
        if (elementName.equalsIgnoreCase("edge"))
        {
            e = new Edge(attributes.getValue("id"));
            currentTimeStep.setCurrentEdge(e);
        }

        if (elementName.equalsIgnoreCase("lane"))
        {
            String laneId = attributes.getValue("id");
            currentLane = new Lane(laneId);
        }

        if (elementName.equalsIgnoreCase("vehicle"))
        {
            String vehicleId = attributes.getValue("id");
            double vehiclePos = Double.parseDouble(attributes.getValue("pos"));
            double vehicleSpeed = Double.parseDouble(attributes.getValue("speed"));
            VehiclePosition vehPos = new VehiclePosition(vehicleId, vehiclePos, vehicleSpeed);
            currentLane.addVehicle(vehPos);
            // currentTimeStep.getCurrentEdge().getLanes().get(index)
            //currentTimeStep`.getCurrentEdge().();
        }
    }

    @Override
    public void endElement(String s, String s1, String element)
    {
        //This means the main node (timestep) comes to an end
        if (element.equalsIgnoreCase("timestep"))
        {
            timesteps.add(currentTimeStep);
        }

        if (element.equalsIgnoreCase("lane"))
        {
            currentTimeStep.getCurrentEdge().addLane(currentLane);
        }
    }

    public void testPrint()
    {
    }
}
