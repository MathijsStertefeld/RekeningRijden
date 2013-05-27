/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package domain;

import autosysteem.Frame;
import com.marbl.autosysteem.GeoPosition;
import com.marbl.autosysteem.Movement;
import com.marbl.autosysteem.Session;
import com.marbl.autosysteem.TimeStep;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.api.client.config.ClientConfig;
import com.sun.jersey.api.client.config.DefaultClientConfig;
import java.util.ArrayList;
import java.util.Date;
import javax.swing.JOptionPane;
import org.openstreetmap.osmosis.core.domain.v0_6.Node;

/**
 *
 * @author Leslie Aerts
 */
public class Simulator implements Runnable
{

    private long timestepInterval;
    private ArrayList<TimeStep> timesteps;
    private Thread t1;
    private Date sessionDate;
    //private TimeStepSimulator tsSim;
    private CarSimulator carSim;
    private CarHolder garage;
    private boolean isRunning = false;
    private Frame tempframe;

    public Simulator(long timestepInterval, long carInterval, Frame f)
    {
        this.timestepInterval = timestepInterval;
        timesteps = new ArrayList<TimeStep>();
        garage = new CarHolder();
        carSim = new CarSimulator(carInterval);
        this.sessionDate = new Date();
        System.out.println(sessionDate);
        tempframe = f;
    }

    public void start()
    {
        t1 = new Thread(this);
        isRunning = true;
        t1.start();
        carSim.start();
    }

    public void stop()
    {
        isRunning = false;
        t1.interrupt();
        carSim.stop();
    }

    public void addCar(Vehicle c, ArrayList<Node> route)
    {
        garage.addCar(c, route);
    }

    public CarHolder getGarage()
    {
        return garage;
    }

    public Date getSessionDate()
    {
        return sessionDate;
    }

    public boolean isRunning()
    {
        return isRunning;
    }

    public void changeTimestepInterval(long interval)
    {
        // tsSim.changeInterval(interval);
        this.timestepInterval = interval;
    }

    @Override
    public void run()
    {
        while (t1.isAlive())
        {
            try
            {
                double time = (double) timesteps.size();
                TimeStep ts = new TimeStep(time);

                tempframe.setOutputText("Timestep " + ts.getTimestepTime() + "\n");
                for (Vehicle v : CarHolder.getCars())
                {
                    //Add movement to ts
                    //System.out.println("Timestep: " + timestepInterval);
                    double x = 1000.0000 / timestepInterval;
                    System.out.println(x);
                    double distance = (v.getCarSpeedInKM() / 3600) * 1000 / x;

                    String wayName = "empty";
                    if (v.getCurrentEdge().getName().length() > 0)
                    {
                        wayName = v.getCurrentEdge().getName();
                    }

                    Movement m = new Movement(v.getDriverBSN(), v.getCarTrackerId(), new Date(), wayName, distance);
                    ts.addMovement(m);


                    //System.out.println("distance since last is in meters " + distance);

                    tempframe.setOutputText("Car " + v.getCarTrackerId() + " pos is " + v.getCarGraphic().getLat() + "," + v.getCarGraphic().getLon() + "\n");
                }

                tempframe.setOutputText("\n\n");

                tempframe.getMap().repaint();
                timesteps.add(ts);
                //sendSession(ts);
                try
                {
                    t1.sleep(timestepInterval);
                } catch (InterruptedException e)
                {
                    t1.join();
                    return;
                }
            } catch (Exception ex)
            {
                ex.printStackTrace();
            }
        }
    }

    public ArrayList<TimeStep> getTimesteps()
    {
        return timesteps;
    }

    public void setTimesteps(ArrayList<TimeStep> timesteps)
    {
        this.timesteps = timesteps;
    }

    public Session generateSession()
    {
        Session s = new Session(this.sessionDate, timesteps);
        for (TimeStep ts : timesteps)
        {
            ts.setParentSession(s);
        }
        return s;
    }

    public boolean sendSession(TimeStep s)
    {

        if (CarHolder.getCars().size() > 0)
        {
            //this.outputPane.removeAll();

            JOptionPane.showMessageDialog(null, "Simulatie gestopt. Verplaatsingen worden verstuurd naar Verplaatsingsysteem.");
            ClientConfig config = new DefaultClientConfig();
            Client client = Client.create(config);

            //tempframe.setOutputText("Verbinding maken met verplaatsingsysteem...\n\n");
            //De URL klopt natuurlijk nog niet
            WebResource service = client.resource("http://192.168.30.187/VerplaatsingSysteemWeb/");
            //tempframe.setOutputText("Versturen gegevens...\n\n");
            service.path("resources").path("session").post(s);
            tempframe.setOutputText("Timestep verzonden.\n\n");

            return true;

        } else
        {
            return false;
        }

    }
}
