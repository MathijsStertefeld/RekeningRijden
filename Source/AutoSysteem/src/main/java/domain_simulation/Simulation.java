/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package domain_simulation;

import domain_simulation.Car;
import java.util.ArrayList;
import java.util.Date;
import openstreetmaps.Frame;

/**
 *
 * @author Leslie Aerts
 */
public class Simulation implements Runnable
{

    private long timestepInterval;
    private ArrayList<TimeStep> timesteps;
    private Thread t1;
    private Date sessionDate;
    //private TimeStepSimulator tsSim;
    private CarSimulator carSim;
    private Garage garage;
    private boolean isRunning = false;
    private Frame tempframe;

    public Simulation(long timestepInterval, long carInterval, Frame f)
    {
        this.timestepInterval = timestepInterval;
        timesteps = new ArrayList<TimeStep>();
        garage = new Garage();
        //tsSim = new TimeStepSimulator(timestepInterval);
        carSim = new CarSimulator(carInterval);
        this.sessionDate = new Date();

        tempframe = f;
    }

    public void start()
    {
        t1 = new Thread(this);
        isRunning = true;
        t1.start();
        //tsSim.start();
        carSim.start();
    }

    public void stop()
    {
        isRunning = false;
        t1.interrupt();
        carSim.stop();
    }

    public void addCar(Car c)
    {
        garage.addCar(c);
    }

    public Garage getGarage()
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
                timesteps.add(ts);
                tempframe.setOutputText("Timestep " + ts.getTime() + "\n");
                for (Car c : Garage.getCars())
                {
                    tempframe.getMap().repaint();
                    //HIER MOET IK IETS RETURNEN ZODAT HET ER UIT KOMT
                    tempframe.setOutputText("Car " + c.getCarTrackerId() + " pos is " + c.getCarGraphic().getLat() + "," + c.getCarGraphic().getLon() + "\n");
                }

                System.out.println("\n");
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
}
