/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.marbl.autosysteem;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Transient;
import javax.xml.bind.annotation.XmlAttribute;
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
    private Collection<Movement> movements;
    @ManyToOne
    private Session parentSession;

    public TimeStep()
    {
        movements = new ArrayList<Movement>();
    }

    public TimeStep(double time)
    {
        this.timestepTime = time;
        movements = new ArrayList<Movement>();
    }

    public long getId()
    {
        return id;
    }

    public void setId(long id)
    {
        this.id = id;
    }

    @XmlTransient
    public Session getParentSession()
    {
        return parentSession;
    }

    public void setParentSession(Session parentSession)
    {
        this.parentSession = parentSession;
    }

    @XmlAttribute(name = "time")
    public double getTimestepTime()
    {
        return timestepTime;
    }

    public void setTimestepTime(double timestepTime)
    {
        this.timestepTime = timestepTime;
    }

    public Collection<Movement> getMovements()
    {
        return movements;
    }

    public void setMovements(Collection<Movement> movements)
    {
        this.movements = movements;
    }

    public void addMovement(Movement m)
    {
        m.setParentTimestep(this);
        movements.add(m);
    }
}
