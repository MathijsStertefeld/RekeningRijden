/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package domain;

/**
 *
 * @author Leslie Aerts
 */
public class Movement
{

    private int timestep;
    private Lane currentLane;

    public Movement(int timestep, Lane lane)
    {
        this.timestep = timestep;
        this.currentLane = lane;
    }
    
    public Movement()
    {
        
    }

    public int getTimestep()
    {
        return timestep;
    }

    public void setTimestep(int timestep)
    {
        this.timestep = timestep;
    }

    public Lane getCurrentLane()
    {
        return currentLane;
    }

    public void setCurrentLane(Lane currentLane)
    {
        this.currentLane = currentLane;
    }
    
    
}
