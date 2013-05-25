/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package domain_simulation;

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
    private ArrayList<Vehicle> vehicles;

    public TimeStep(double time)
    {
        this.time = time;
        vehicles = new ArrayList<Vehicle>();
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

    public ArrayList<Vehicle> getVehicles()
    {
        return vehicles;
    }

    public void setVehicles(ArrayList<Vehicle> vehicles)
    {
        this.vehicles = vehicles;
    }
    
    public void addVehicle(Vehicle v)
    {
        vehicles.add(v);
    }
}
