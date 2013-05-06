/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package verplaatsingensysteem.service;

import com.sun.jersey.core.header.FormDataContentDisposition;
import com.sun.jersey.multipart.FormDataParam;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;

/**
 *
 * @author Leslie Aerts
 */
@Path("/xml")
@Stateless
public class RestfulReceiver
{

    @Inject
    VerplaatsingSysteemService vpService;

    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String test()
    {
        return "Hello, this is a test Restful.";
    }

    //Get the actual XML file in java.io.file
    @POST
    @Consumes(MediaType.TEXT_PLAIN)
    @Path("/plain")
    public void sendXML(String s)
    {
        System.out.println("Standard Text XML.");
        System.out.println(s);
    }

//    Get the actual XML file in java.io.file
    @POST
    @Consumes(MediaType.MULTIPART_FORM_DATA)
    @Path("xml")
    public void sendXML(@Context HttpServletRequest request,
            @FormDataParam("file") FormDataContentDisposition dispostion,
            @FormDataParam("file") InputStream attachmentFile)
    {
        int read = 0;
        byte[] bytes = new byte[1024];

        File f = new File(dispostion.getName());
        System.out.println("I got a file!");

        FileOutputStream fos;
        try
        {
            fos = new FileOutputStream(f);

            while ((read = attachmentFile.read(bytes)) != -1)
            {
                fos.write(bytes, 0, read);
            }
        } catch (Exception e)
        {
            e.printStackTrace();
        }
    }
}
