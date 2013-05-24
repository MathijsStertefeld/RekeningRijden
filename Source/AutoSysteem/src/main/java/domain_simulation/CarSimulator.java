    /*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package domain_simulation;

import domain_simulation.Car;
import org.openstreetmap.osmosis.core.domain.v0_6.Node;

/**
 *
 * @author Leslie Aerts
 */
public class CarSimulator implements Runnable
{

    private long threadInterval;
    private Thread t1;

    /**
     * CarSimulator constructor. Creates a new Thread that can be started with
     * start();
     *
     * @param interval The interval each time this thread's run gets called.
     */
    public CarSimulator(long interval)
    {
        this.threadInterval = interval;
    }

    /**
     * Starts the thread.
     */
    public void start()
    {
        t1 = new Thread(this);
        t1.start();
    }

    /**
     * Stops the thread and can be recalled with start();
     */
    public void stop()
    {
        t1.interrupt();
    }

    /**
     * Runs the thread and updates each car in CarHolder on their route.
     */
    @Override
    public void run()
    {
        while (t1.isAlive())
        {
            try
            {
                for (Car c : CarHolder.getCars())
                {
                    
                    //WHILE distance to target < speed/step
                    //  speed(remainingDistance) = speed/step - distance to target
                    //  route.increaseTargetIndex
                    //  
                    double carSpeed = 0.1;
                    double remainingDistance = carSpeed; //amount of movement
                    double distanceToTarget = WorldMath.DistanceInKM(c.getPosition(), c.getRoute().getTargetNodePosition());
                       
                    while(distanceToTarget < remainingDistance)
                    {
                        remainingDistance = remainingDistance - distanceToTarget;
                        c.setPosition(c.getRoute().getTargetNodePosition());
                        c.getRoute().increaseTargetIndex();
                        distanceToTarget = WorldMath.DistanceInKM(c.getPosition(), c.getRoute().getTargetNodePosition());
                    }
                    
                    System.out.println("PrePos: " + c.getPosition().getLatitudeInDegrees() + " - " + c.getPosition().getLongitudeInDegrees());
                    System.out.println("TargetNodePos: " + c.getRoute().getTargetNodePosition().getLatitudeInDegrees() + " - " + c.getRoute().getTargetNodePosition().getLongitudeInDegrees());
                    double bearing = WorldMath.AngleToInRadians(c.getPosition(), c.getRoute().getTargetNodePosition());
                                                                                                           ///Was speed hieronder                 
                    GeoPosition newPosition = WorldMath.NewPositionWithAngleAndDistance(c.getPosition(), remainingDistance, bearing);
                    System.out.println("new Position: " + newPosition.getLatitudeInDegrees() + " - " + newPosition.getLongitudeInDegrees());
                    c.setPosition(newPosition);
                    System.out.println(" ");
                }

                try
                {
                    Thread.sleep(threadInterval);
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
