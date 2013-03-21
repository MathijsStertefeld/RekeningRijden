/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package domain;

/**
 *
 * @author Leslie Aerts
 */
public class TimeStep
{

    private double time;
    private Edge currentEdge;

    public TimeStep(int timestep, Edge edge)
    {
        this.time = timestep;
        this.currentEdge = edge;
    }
    
    public TimeStep()
    {
    }

    public double getTime()
    {
        return time;
    }

    public void setTime(double time)
    {
        this.time = time;
    }

    public Edge getCurrentEdge()
    {
        return currentEdge;
    }

    public void setCurrentEdge(Edge currentEdge)
    {
        this.currentEdge = currentEdge;
    }
    
    
    
    
}
