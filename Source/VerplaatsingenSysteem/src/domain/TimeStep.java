/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package domain;

import java.util.ArrayList;

/**
 *
 * @author Leslie Aerts
 */
public class TimeStep
{

    private double time;
    private ArrayList<Edge> edges;

    public TimeStep(int timestep)
    {
        this.time = timestep;
        edges = new ArrayList<Edge>();
    }

    public TimeStep()
    {
        edges = new ArrayList<Edge>();
    }

    public double getTime()
    {
        return time;
    }

    public void setTime(double time)
    {
        this.time = time;
    }

    public ArrayList<Edge> getEdges()
    {
        return edges;
    }

    public void setEdges(ArrayList<Edge> edges)
    {
        this.edges = edges;
    }

    public void addEdge(Edge e)
    {
        edges.add(e);
    }
}
