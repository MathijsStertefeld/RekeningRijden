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
    }

    public void start()
    {
        t1 = new Thread(this);
        t1.start();
    }

    public void stop()
    {

        t1.interrupt();
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

                    //FULL CAR SIMULATION GOES HERE. AI???
                    c.move(0.1, 0);
                }
                try
                {
                    Thread.sleep(interval);
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
