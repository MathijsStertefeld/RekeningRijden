/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import javax.persistence.*;

/**
 *
 * @author Leslie Aerts
 */
@Entity
public class TimeStep implements Serializable
{

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @OneToMany(cascade= CascadeType.PERSIST)
    private Collection<Edge> edges;
    private double timestepTime;

    public TimeStep(int timestep)
    {
        this.timestepTime = timestep;
        edges = new ArrayList<Edge>();
    }

    public TimeStep()
    {
        edges = new ArrayList<Edge>();
    }

    public double getTime()
    {
        return timestepTime;
    }

    public void setTime(double time)
    {
        this.timestepTime = time;
    }

    public Collection<Edge> getEdges()
    {
        return edges;
    }

    public void setEdges(Collection<Edge> edges)
    {
        this.edges = edges;
    }

    public void addEdge(Edge e)
    {
        edges.add(e);
    }

    public Long getId()
    {
        return id;
    }

    public void setId(Long id)
    {
        this.id = id;
    }
}
