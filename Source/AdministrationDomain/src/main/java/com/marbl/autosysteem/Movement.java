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
 *
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
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date movementDate;
    private String type; // Values are "city", "highway" or "region".
    private String cityName;
    private String highwayName;
    private String regionName;
    private double distance;

    //<editor-fold defaultstate="collapsed" desc="Getters and Setters">
    
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

    @XmlTransient
    public Date getMovementDate()
    {
        return movementDate;
    }

    public void setMovementDate(Date movementDate)
    {
        this.movementDate = movementDate;
    }

    @XmlAttribute(name = "road_type")
    public String getType()
    {
        return type;
    }

    public void setType(String type)
    {
        this.type = type;
    }

    @XmlAttribute(name = "city")
    public String getCityName()
    {
        return cityName;
    }

    public void setCityName(String cityName)
    {
        this.cityName = cityName;
    }

    @XmlAttribute(name = "highway")
    public String getHighwayName()
    {
        return highwayName;
    }

    public void setHighwayName(String highwayName)
    {
        this.highwayName = highwayName;
    }

    @XmlAttribute(name = "region")
    public String getRegionName()
    {
        return regionName;
    }

    public void setRegionName(String regionName)
    {
        this.regionName = regionName;
    }

    public double getDistance()
    {
        return distance;
    }

    public void setDistance(double distance)
    {
        this.distance = distance;
    }

    //</editor-fold>
    public Movement(int driverBsn, String carTrackerId, Date movementDate, String type, String city, String highway, String region, double distance)
    {
        this.driverBsn = driverBsn;
        this.carTrackerId = carTrackerId;
        this.movementDate = movementDate;
        this.type = type;
        this.cityName = city;
        this.highwayName = highway;
        this.regionName = region;
        this.distance = distance;
    }

    public Movement()
    {
    }

    public Movement(int driverBsn, String carTrackerId, Date movementDate, String type, double distance)
    {
        this.driverBsn = driverBsn;
        this.carTrackerId = carTrackerId;
        this.movementDate = movementDate;
        this.type = type;
        this.distance = distance;
    }
}
