/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package verplaatsingensysteem;

import domain.Movement;
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
public class XMLParser
{

    SAXParser parser;
    DefaultHandler handler;
    File xmlToRead;

    public XMLParser(String fileName) throws SAXException, IOException, ParserConfigurationException
    {
        SAXParserFactory factory = SAXParserFactory.newInstance();
        parser = factory.newSAXParser();
        handler = new DefaultHandler();
        xmlToRead = new File(fileName);
    }

    public String readXML()
    {
        try
        {
            parser.parse(xmlToRead, handler);
        } catch (SAXException | IOException ex)
        {
            Logger.getLogger(XMLParser.class.getName()).log(Level.SEVERE, null, ex);
        }

        //handler.readList();

        return null;
    }

    public ArrayList<Movement> readMovementXML()
    {
        try
        {
            parser.parse(xmlToRead, handler);
        } catch (SAXException | IOException ex)
        {
            Logger.getLogger(XMLParser.class.getName()).log(Level.SEVERE, null, ex);
        }

        //handler.readList();

        return null;
    }

    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException
    {
        if(qName.equalsIgnoreCase("timestep"))
        {
            Movement m = new Movement();
        }
    }
}
