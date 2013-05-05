/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package verplaatsingensysteem.service;

import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Leslie Aerts
 */
public class SocketReceiver
{

    public SocketReceiver()
    {
    }

    public void receive()
    {
        try
        {
            Socket sock = new Socket("127.0.0.1", 13267);

            String fileName = "";
            long fileSize;
            //Temp ip

            //Receiving filename
            BufferedReader nameReader = new BufferedReader(new InputStreamReader(sock.getInputStream()));
            fileName = nameReader.toString(); //TODO: char length
            System.out.println("Filename is " + fileName);
            
            //Receiving filesize
            DataInputStream dIn = new DataInputStream(sock.getInputStream());
            fileSize = dIn.readLong();
            System.out.println("Filesize is " + fileSize);

            //Receiving file
            InputStream is = sock.getInputStream();

            byte[] bytes = new byte[(int) fileSize];
            FileOutputStream fos = new FileOutputStream(fileName);
            BufferedOutputStream bos = new BufferedOutputStream(fos);
            System.out.println("Transfering file...");

            int bytesRead;
            int current = 0;
            do
            {
                bytesRead = is.read(bytes, current, (bytes.length - current));
                if (bytesRead >= 0)
                {
                    current += bytesRead;
                }
            } while (bytesRead > -1);

            bos.write(bytes, 0, current);
            
            System.out.println("File received.");
            bos.flush();
            bos.close();
            sock.close();
            
            System.out.println("Closing down.");

        } catch (UnknownHostException ex)
        {
            Logger.getLogger(SocketReceiver.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex)
        {
            Logger.getLogger(SocketReceiver.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
