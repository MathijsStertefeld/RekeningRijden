/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package tempautoejb;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.api.client.config.ClientConfig;
import com.sun.jersey.api.client.config.DefaultClientConfig;
import com.sun.jersey.multipart.FormDataMultiPart;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import javax.ws.rs.core.MediaType;

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
        ClientConfig config = new DefaultClientConfig();
        Client client = Client.create(config);
        WebResource service = client.resource("http://localhost:8080/VerplaatsingenSysteemWeb/");

        File f = new File("verplaatsing_19901218.xml");
        System.out.println("File found: " + f);

        System.out.println("Sending file...");
        ClientResponse response = service.path("resources").path("xml").path("plain").type(MediaType.TEXT_XML).accept(MediaType.TEXT_PLAIN).post(ClientResponse.class);
        System.out.println("Done. " + response);
       
        InputStream is = null;
        try
        {
            is = new FileInputStream(f);

        } catch (Exception e)
        {
            e.printStackTrace();
        }
        FormDataMultiPart part = new FormDataMultiPart().field("file", is, MediaType.TEXT_PLAIN_TYPE);
        response = service.path("resources").path("xml").path("xml").type(MediaType.MULTIPART_FORM_DATA_TYPE).accept(MediaType.MULTIPART_FORM_DATA).post(ClientResponse.class,part);
        System.out.println("Done. " + response);

    }
}
