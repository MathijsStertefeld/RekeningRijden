/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package verplaatsingensysteem;

import domain.Edge;
import domain.Lane;
import domain.Session;
import domain.TimeStep;
import domain.VehiclePosition;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.GregorianCalendar;
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
    private Edge currentEdge;

    //
    public XMLParser(String fileName) throws SAXException, IOException, ParserConfigurationException
    {
        SAXParserFactory factory = SAXParserFactory.newInstance();
        parser = factory.newSAXParser();
        xmlToRead = new File(fileName);
        timesteps = new ArrayList<TimeStep>();
    }

    public Session readMovementXML()
    {
        try
        {
            parser.parse(xmlToRead, this);
        } catch (SAXException | IOException ex)
        {
            Logger.getLogger(XMLParser.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        //Bestandsnaam MOET "verplaatsing_yyyymmdd.xml" zijn.
        String temp = xmlToRead.getName();
        String[] parts = temp.split("_");
        
        String dateName = parts[1].split("\\.")[0];
        //System.out.println("Date is " + dateName);
        int year = Integer.parseInt(dateName.substring(0, 4));
        int month = Integer.parseInt(dateName.substring(4,6));
        int day = Integer.parseInt(dateName.substring(6,8));
        
        GregorianCalendar gc = new GregorianCalendar(year, month-1, day);
        Date d = gc.getTime();
        
       // System.out.println(year + " " + month + " " + day);
        Session s = new Session(d, timesteps);
        return s;
    }

    @Override
    public void startElement(String s, String s1, String elementName, Attributes attributes) throws SAXException
    {

        //If it gets to a new main element (Which is a timestep) create a new movement.
        if (elementName.equalsIgnoreCase("timestep"))
        {
            currentTimeStep = new TimeStep();
            double timeStep = Double.parseDouble(attributes.getValue("time"));
            //System.out.println("Starting new Timestep..." + timeStep);
            currentTimeStep.setTime(timeStep);
        }

        if (elementName.equalsIgnoreCase("edge"))
        {
            currentEdge = new Edge(attributes.getValue("id"));
            System.out.println("Starting new edge..." + currentEdge.getId());

        }

        if (elementName.equalsIgnoreCase("lane"))
        {
            String laneId = attributes.getValue("id");
            System.out.println("Starting new Lane..." + laneId);
            currentLane = new Lane(laneId);
        }

        if (elementName.equalsIgnoreCase("vehicle"))
        {
            String vehicleId = attributes.getValue("id");
            double vehiclePos = Double.parseDouble(attributes.getValue("pos"));
            double vehicleSpeed = Double.parseDouble(attributes.getValue("speed"));
            VehiclePosition vehPos = new VehiclePosition(vehicleId, vehiclePos, vehicleSpeed);
            System.out.println("Starting new vehicle..." + vehicleId + " " + vehiclePos + " " + vehicleSpeed);
            currentLane.addVehicle(vehPos);
        }
    }

    @Override
    public void endElement(String s, String s1, String element)
    {
        //This means the main node (timestep) comes to an end
        if (element.equalsIgnoreCase("timestep"))
        {
            System.out.println("Ending timestep...");
            timesteps.add(currentTimeStep);
        }

        if (element.equalsIgnoreCase("edge"))
        {
            System.out.println("Ending edge...");
            currentTimeStep.addEdge(currentEdge);
        }


        if (element.equalsIgnoreCase("lane"))
        {
            System.out.println("Ending Lane...");
            currentEdge.addLane(currentLane);
        }
    }
}
