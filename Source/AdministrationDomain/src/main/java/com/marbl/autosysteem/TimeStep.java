/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.marbl.autosysteem;

import java.util.ArrayList;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Leslie Aerts
 */
@XmlRootElement
public class TimeStep
{

    private double time;
    private ArrayList<Movement> movements;
//    private ArrayList<Vehicle> vehicles;

    public TimeStep(double time)
    {
        this.time = time;
        movements = new ArrayList<Movement>();
//        vehicles = new ArrayList<Vehicle>();
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

//    public ArrayList<Vehicle> getVehicles()
//    {
//        return vehicles;
//    }
//
//    public void setVehicles(ArrayList<Vehicle> vehicles)
//    {
//        this.vehicles = vehicles;
//    }
//
//    public void addVehicle(Vehicle v)
//    {
//        this.vehicles.add(v);
//    }
}
