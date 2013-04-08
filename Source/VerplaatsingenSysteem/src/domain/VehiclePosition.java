/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package domain;

import java.io.Serializable;
import javax.persistence.*;

/**
 *
 * @author Leslie Aerts
 */
@Entity
public class VehiclePosition implements Serializable
{

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String carTrackerId;
    private double carPos;
    private double carSpeed;

    public VehiclePosition()
    {
    }

    public VehiclePosition(String carTrackerId, double carPos, double carSpeed)
    {
        this.carTrackerId = carTrackerId;
        this.carPos = carPos;
        this.carSpeed = carSpeed;
    }

    public String getCarTrackerId()
    {
        return carTrackerId;
    }

    public void setCarTrackerId(String carTrackerId)
    {
        this.carTrackerId = carTrackerId;
    }

    public double getCarPos()
    {
        return carPos;
    }

    public void setCarPos(double carPos)
    {
        this.carPos = carPos;
    }

    public double getCarSpeed()
    {
        return carSpeed;
    }

    public void setCarSpeed(double carSpeed)
    {
        this.carSpeed = carSpeed;
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
