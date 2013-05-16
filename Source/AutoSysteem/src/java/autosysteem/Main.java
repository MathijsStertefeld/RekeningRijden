/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package autosysteem;

import domain_simulation.Simulation;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.api.client.config.ClientConfig;
import com.sun.jersey.api.client.config.DefaultClientConfig;
import domain.Session;
import domain_simulation.Car;


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
        test1();
//        try
//        {
//            Simulation sim = new Simulation(1000,10);
//            sim.start();
//            sim.addCar(new Car("1241-4124"));
//        } catch (Exception e)
//        {
//            e.printStackTrace();
//        }
    }

    
    
    public static void test1()
    {
        System.out.println("Starting webservice...");
        ClientConfig config = new DefaultClientConfig();
        Client client = Client.create(config);
        WebResource service = client.resource("http://localhost:8080/VerplaatsingSysteemWeb/");

        System.out.println("Getting Session from server...");
        Session s2 = service.path("resources").path("xml").path("get_session").get(Session.class);
        System.out.println("Session get. " + s2);
        System.out.println("I have all my child info. " + s2.getTimeStep(1).getParentSession());
        System.out.println("Sending session back from client to server...");
        service.path("resources").path("xml").post(s2);
        System.out.println("Done. Check Glassfish output.");
    }
}
