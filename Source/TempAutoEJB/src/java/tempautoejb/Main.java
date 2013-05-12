/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package tempautoejb;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.api.client.config.ClientConfig;
import com.sun.jersey.api.client.config.DefaultClientConfig;
import domain.Session;
import java.io.File;

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
        File f = new File("verplaatsing_19901218.xml");
        System.out.println("File found: " + f);

        System.out.println("Normally, you would start with java objects, but we're going to create them now with the reader.");
        try
        {
            Session s = null;

            System.out.println("Reading XML...");
            XMLParser parser = new XMLParser(f);
            s = parser.readMovementXML();
            System.out.println("Done. Session: " + s.getSessionDate().toString());


            System.out.println("Starting webservice...");
            ClientConfig config = new DefaultClientConfig();
            Client client = Client.create(config);
            WebResource service = client.resource("http://localhost:8080/VerplaatsingSysteemWeb/");

//        System.out.println("Method 2...");
//        service.path("resources").path("xml").path("alt").type(MediaType.APPLICATION_XML).post(xml);
            System.out.println("Test Getting...");
            Session s2 = service.path("resources").path("xml").path("get_session").get(Session.class);  
            
            System.out.println("Now I'm posting...");
            System.out.println("Method 1...");
            service.path("resources").path("xml").post(s);
            System.out.println("Done. Check Glassfish output.");
        } catch (Exception e)
        {
            e.printStackTrace();
        }




    }
}
