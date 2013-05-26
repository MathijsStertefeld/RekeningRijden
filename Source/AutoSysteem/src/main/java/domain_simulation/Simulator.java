/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package domain_simulation;

import domain_simulation.Vehicle;
import java.util.ArrayList;
import java.util.Date;
import autosysteem.Frame;
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
                    ts.addVehicle(v);
                    //HIER MOET IK IETS RETURNEN ZODAT HET ER UIT KOMT
                    tempframe.setOutputText("Car " + v.getCarTrackerId() + " pos is " + v.getCarGraphic().getLat() + "," + v.getCarGraphic().getLon() + "\n");
                }
                tempframe.getMap().repaint();
                timesteps.add(ts);

                tempframe.setOutputText("\n\n");
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
    
    
}
