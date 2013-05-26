/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package domain;

import autosysteem.Frame;
import com.marbl.autosysteem.GeoPosition;
import com.marbl.autosysteem.Movement;
import com.marbl.autosysteem.TimeStep;
import java.util.ArrayList;
import java.util.Date;
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

                tempframe.setOutputText("Timestep " + ts.getTime() + "\n");
                for (Vehicle v : CarHolder.getCars())
                {
                    double distance = 5;
                    //Add movement to ts
//                    if (timesteps.size() > 0)
//                    {
//                        int lastTimeStepIndex = timesteps.size()-1;
//                        int lastVehicleIndex = timesteps.get(lastTimeStepIndex).getVehicles().indexOf(v);
//                        Vehicle previousVehicle = timesteps.get(lastTimeStepIndex).getVehicles().get(lastVehicleIndex);
//                        distance = this.getDistanceInMeters(previousVehicle.getPosition(), v.getPosition());
//                    }   
                    
                    Movement m = new Movement(v.getDriverBSN(), v.getCarTrackerId(), sessionDate, v.getCurrentEdge().getName(), distance);
                    ts.addMovement(m);
                    //ts.addVehicle(v);
                    System.out.println("distance since last is" + distance);

                    tempframe.setOutputText("Car " + v.getCarTrackerId() + " pos is " + v.getCarGraphic().getLat() + "," + v.getCarGraphic().getLon() + "\n");
                }
                tempframe.setOutputText("\n\n");

                tempframe.getMap().repaint();
                timesteps.add(ts);

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

    private double getDistanceInMeters(GeoPosition from, GeoPosition to)
    {
        return Navigation.getDistance(from.getLatitudeInDegrees(), from.getLongitudeInDegrees(), to.getLatitudeInDegrees(), to.getLongitudeInDegrees(), 'M');
    }

    public Session generateSession()
    {
        Session s = new Session(sessionDate, timesteps);

        return s;
    }
}
