/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package domain_simulation;

import domain_simulation.Vehicle;
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
    }

    public void start()
    {
        t1 = new Thread(this);
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

    }
}
