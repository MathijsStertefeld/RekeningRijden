/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package domain_simulation;

import domain_simulation.Car;

/**
 *
 * @author Leslie Aerts
 */
public class CarSimulator implements Runnable
{

    private long interval;
    private Thread t1;
    private boolean stop = false;

    public CarSimulator(long interval)
    {
        this.interval = interval;
        t1 = new Thread(this);
    }

    public void start()
    {
        t1.start();
    }

    public void stop()
    {
        stop = true;
        //t1.interrupt();
    }

    @Override
    public void run()
    {
        while (t1.isAlive())
        {
            try
            {

                for (Car c : Garage.getCars())
                {
                    c.move(5);
                }
                try
                {
                    Thread.sleep(interval);
                } catch (InterruptedException e)
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
