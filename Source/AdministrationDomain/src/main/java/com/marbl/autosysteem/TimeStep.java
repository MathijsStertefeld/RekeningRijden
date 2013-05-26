/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.marbl.autosysteem;

import java.util.ArrayList;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Leslie Aerts
 */
@Entity
@XmlRootElement
public class TimeStep
{

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private double time;
    @OneToMany(cascade = CascadeType.MERGE, mappedBy = "parentTimestep")
    private ArrayList<Movement> movements;
    @ManyToOne
    private Session parentSession;

    public TimeStep()
    {
    }

    public TimeStep(double time)
    {
        this.time = time;
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

    public Session getParentSession()
    {
        return parentSession;
    }

    public void setParentSession(Session parentSession)
    {
        this.parentSession = parentSession;
    }
    
    @XmlAttribute(name = "time")
    public double getTime()
    {
        return time;
    }

    public void setTime(double time)
    {
        this.time = time;
    }

    public ArrayList<Movement> getMovements()
    {
        return movements;
    }

    public void setMovements(ArrayList<Movement> movements)
    {
        this.movements = movements;
    }

    public void addMovement(Movement m)
    {
        m.setParentTimestep(this);
        movements.add(m);
    }

    public void setSession(Session s)
    {
        this.parentSession = s;
    }
}
