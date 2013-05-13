/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package verplaatsingensysteem.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import javax.persistence.*;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;


/**
 *
 * @author Leslie Aerts
 */
@Entity
@XmlRootElement
public class TimeStep implements Serializable
{

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private double timestepTime;
    @Transient
    private Collection<Edge> edges;
    @ManyToOne(cascade = CascadeType.MERGE)
    private Session parentSession;

    public TimeStep()
    {
        edges = new ArrayList<Edge>();
    }

    public TimeStep(double timestep, Session parent)
    {
        this.timestepTime = timestep;
        this.parentSession = parent;
        edges = new ArrayList<Edge>();

    }

    @XmlAttribute(name = "time")
    public double getTime()
    {
        return timestepTime;
    }

    public void setTime(double time)
    {
        this.timestepTime = time;
    }

    @XmlElement(name = "edge")
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

    public long getId()
    {
        return id;
    }

    public void setId(long id)
    {
        this.id = id;
    }

    public void setParentSession(Session s)
    {
        this.parentSession = s;
    }

//    @XmlAttribute(name = "parent_id")
//    public long getParentSessionId()
//    {
//        return this.parentSession.getId();
//    }
    
    @XmlTransient
    public Session getParentSession()
    {
        return this.parentSession;
    }
}
