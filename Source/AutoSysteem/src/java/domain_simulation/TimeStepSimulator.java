/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package domain_simulation;

import domain_simulation.Car;
import domain_simulation.TimeStep;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Leslie Aerts
 */
public class TimeStepSimulator implements Runnable
{

    private long timestepInterval;
    private ArrayList<TimeStep> timesteps;
    private Thread t1;
    private boolean stop = false;

    public TimeStepSimulator(long interval)
    {
        this.timestepInterval = interval;
        timesteps = new ArrayList<TimeStep>();
        t1 = new Thread(this);
    }

    public void start()
    {
        t1.start();
    }

    public void stop()
    {
        t1.interrupt();
        //stop = true;
    }

    public void changeInterval(long interval)
    {
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
                    System.out.println("Timestep " + ts.getTime() + "");
                    for (Car c : Garage.getCars())
                    {
                        System.out.println("Car " + c.getCarId() + " pos is " + c.getPos().getCarPos());
                    }

                    System.out.println("\n");
                    try{
                    t1.sleep(timestepInterval);
                    }
                    catch(InterruptedException e)
                    {
                        return;
                    }
                
            } catch (Exception ex)
            {
                ex.printStackTrace();
            }
        }
    }
}
