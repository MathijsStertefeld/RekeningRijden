/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package verplaatsingensysteem.domain;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.*;

/**
 *
 * @author Eagle
 */
@Entity
@Table(name = "Movement")
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
    private String city;
    private String highway;
    private String region;
    private int distance;

    //<editor-fold defaultstate="collapsed" desc="Getters and Setters">
    public long getId()
    {
        return id;
    }

    public int getDriverBsn()
    {
        return driverBsn;
    }

    public void setDriverBsn(int driverBsn)
    {
        this.driverBsn = driverBsn;
    }

    public String getCarTrackerId()
    {
        return carTrackerId;
    }

    public void setCarTrackerId(String carTrackerId)
    {
        this.carTrackerId = carTrackerId;
    }

    public Date getDate()
    {
        return movementDate;
    }

    public void setDate(Date movementDate)
    {
        this.movementDate = movementDate;
    }

    public String getType()
    {
        return type;
    }

    public void setType(String type)
    {
        this.type = type;
    }

    public String getCity()
    {
        return city;
    }

    public void setCity(String city)
    {
        this.city = city;
    }

    public String getHighway()
    {
        return highway;
    }

    public void setHighway(String highway)
    {
        this.highway = highway;
    }

    public String getRegion()
    {
        return region;
    }

    public void setRegion(String region)
    {
        this.region = region;
    }

    public int getDistance()
    {
        return distance;
    }

    public void setDistance(int distance)
    {
        this.distance = distance;
    }
    //</editor-fold>

    public Movement(int driverBsn, String carTrackerId, Date movementDate, String type, String city, String highway, String region, int distance)
    {
        this.driverBsn = driverBsn;
        this.carTrackerId = carTrackerId;
        this.movementDate = movementDate;
        this.type = type;
        this.city = city;
        this.highway = highway;
        this.region = region;
        this.distance = distance;
    }

    public Movement()
    {
        
    }

    public Movement(int driverBsn, String carTrackerId, Date movementDate, String type, int distance)
    {
        this.driverBsn = driverBsn;
        this.carTrackerId = carTrackerId;
        this.movementDate = movementDate;
        this.type = type;

        this.distance = distance;
    }
}
