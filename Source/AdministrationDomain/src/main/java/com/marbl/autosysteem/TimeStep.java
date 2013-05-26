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
    @OneToMany(cascade = CascadeType.MERGE)
    private ArrayList<Movement> movements;

    public TimeStep()
    {
    }

    public TimeStep()
    {
    }

    public TimeStep(double time)
    {
        this.time = time;
        movements = new ArrayList<Movement>();
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
        movements.add(m);
    }
}
