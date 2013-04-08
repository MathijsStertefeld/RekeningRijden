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
@Table (name = "TIMESTEP")
public class TimeStep implements Serializable
{
    
    
    private double time;
    
    @ManyToOne (cascade = { CascadeType.PERSIST
    })
    private Collection<Edge> edges;
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

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
        return (ArrayList<Edge>) edges;
    }

    public void setEdges(ArrayList<Edge> edges)
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
