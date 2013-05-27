/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.marbl.autosysteem;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.*;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 * @author Eagle
 */
@Entity
@Table(name = "Movement")
@XmlRootElement(name = "movement")
public class Movement implements Serializable
{

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private int driverBsn;
    private String carTrackerId;
    @Temporal(javax.persistence.TemporalType.TIMESTAMP)
    private Date movementDate;
    private String wayName; // Values are "city", "highway" or "region".
    private double distance;
    @ManyToOne(cascade = CascadeType.MERGE)
    private TimeStep parentTimestep;
    //<editor-fold defaultstate="collapsed" desc="Getters and Setters">

    public Movement(long id, int driverBsn, String carTrackerId, Date movementDate, String wayname, double distance, TimeStep parent)
    {
        this.id = id;
        this.driverBsn = driverBsn;
        this.carTrackerId = carTrackerId;
        this.movementDate = movementDate;
        this.wayName = wayname;
        this.distance = distance;
    }

    @XmlTransient
    public long getId()
    {
        return id;
    }

    public void setId(long id)
    {
        this.id = id;
    }

    @XmlAttribute(name = "driver_bsn")
    public int getDriverBsn()
    {
        return driverBsn;
    }

    public void setDriverBsn(int driverBsn)
    {
        this.driverBsn = driverBsn;
    }

    @XmlAttribute(name = "car_tracker_id")
    public String getCarTrackerId()
    {
        return carTrackerId;
    }

    public void setCarTrackerId(String carTrackerId)
    {
        this.carTrackerId = carTrackerId;
    }

    @XmlAttribute(name = "date")
    public Date getMovementDate()
    {
        return movementDate;
    }

    public void setMovementDate(Date movementDate)
    {
        this.movementDate = movementDate;
    }

    public double getDistance()
    {
        return distance;
    }

    @XmlAttribute(name = "way_name")
    public String getWayName()
    {
        return wayName;
    }

    public void setWayName(String wayName)
    {
        this.wayName = wayName;
    }

    public void setDistance(double distance)
    {
        this.distance = distance;
    }

    //</editor-fold>
    public Movement(TimeStep parent, int driverBsn, String carTrackerId, Date movementDate, String type, String city, String highway, String region, double distance)
    {
        this.driverBsn = driverBsn;
        this.carTrackerId = carTrackerId;
        this.movementDate = movementDate;
        this.wayName = type;
        this.distance = distance;
        this.parentTimestep = parent;
    }

    public Movement()
    {
    }

    public Movement(int driverBsn, String carTrackerId, Date movementDate, String type, double distance)
    {
        this.driverBsn = driverBsn;
        this.carTrackerId = carTrackerId;
        this.movementDate = movementDate;
        this.wayName = type;
        this.distance = distance;
    }

    @XmlTransient
    public TimeStep getParentTimestep()
    {
        return parentTimestep;
    }

    public void setParentTimestep(TimeStep parentTimestep)
    {
        this.parentTimestep = parentTimestep;
    }
}
