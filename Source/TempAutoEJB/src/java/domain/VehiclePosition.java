/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package domain;

import java.io.Serializable;
import javax.persistence.*;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.Unmarshaller;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Leslie Aerts
 */
@Entity
@XmlRootElement(name = "vehicle")
public class VehiclePosition implements Serializable
{

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String carTrackerId;
    private double carPos;
    private double carSpeed;
    @ManyToOne(cascade = CascadeType.MERGE)
    private Lane parentLane;
    @ManyToOne(cascade = CascadeType.MERGE)
    private TimeStep parentTimeStep;

    public VehiclePosition()
    {
    }

    public VehiclePosition(String carTrackerId, double carPos, double carSpeed, Lane parent, TimeStep parentTimeStep)
    {
        this.carTrackerId = carTrackerId;
        this.carPos = carPos;
        this.carSpeed = carSpeed;
        this.parentLane = parent;
        this.parentTimeStep = parentTimeStep;
    }

    @XmlAttribute(name = "id")
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

    @XmlAttribute(name = "pos")
    public void setCarPos(double carPos)
    {
        this.carPos = carPos;
    }

    @XmlAttribute(name = "speed")
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

    //@XmlAttribute(name = "parent_lane_id")
//    public String getParentLaneId()
//    {
//        return parentLane.getId();
//    }

    @XmlTransient
    public Lane getParentLane()
    {
        return parentLane;
    }

    //@XmlAttribute(name = "parent_timestep_id")
//    public long getParentTimeStepId()
//    {
//        return parentTimeStep.getId();
//    }
    @XmlTransient
    public TimeStep getParentTimeStep()
    {
        return parentTimeStep;
    }
}
