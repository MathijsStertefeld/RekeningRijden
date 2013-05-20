/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package domain_simulation;

import domain_simulation.Car;
import java.util.Date;

/**
 *
 * @author Leslie Aerts
 */
public class Simulation
{

    private Date sessionDate;
    private TimeStepSimulator tsSim;
    private CarSimulator carSim;
    private Garage garage;
    private boolean isRunning = false;

    public Simulation(long timestepInterval, long carInterval)
    {
        garage = new Garage();
        tsSim = new TimeStepSimulator(timestepInterval);
        carSim = new CarSimulator(carInterval);
        this.sessionDate = new Date();
    }

    public void start()
    {
        isRunning = true;
        tsSim.start();
        carSim.start();
    }

    public void stop()
    {
        isRunning = false;
        tsSim.stop();
        carSim.stop();
    }

    public void addCar(Car c)
    {
        garage.addCar(c);
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
        tsSim.changeInterval(interval);
    }
}
