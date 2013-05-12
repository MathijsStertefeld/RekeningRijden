/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package verplaatsingensysteem.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import javax.persistence.*;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Alexander Arends & Leslie Aerts
 */
@Entity
@XmlRootElement
public class Lane implements Serializable
{
    // @Id
    //  @GeneratedValue(strategy= GenerationType.AUTO)
    //  private long id;

    @Id
    private String lane_id;
    // @OneToMany(cascade= CascadeType.PERSIST,mappedBy="parentLane")
    @Transient
    private Collection<VehiclePosition> positions;
    @ManyToOne(cascade = CascadeType.MERGE)
    private Edge parentEdge;

    public Lane()
    {
        positions = new ArrayList<VehiclePosition>();
    }

    public Lane(String value, Edge parent)
    {
        this.lane_id = value;
        parentEdge = parent;
        positions = new ArrayList<VehiclePosition>();
    }

    public void addVehicle(VehiclePosition pos)
    {
        positions.add(pos);
    }

    @XmlAttribute(name = "id")
    public String getId()
    {
        return lane_id;
    }

    public void setId(String id)
    {
        this.lane_id = id;
    }

    @XmlElement(name="vehicle")
    public Collection<VehiclePosition> getPositions()
    {
        return positions;
    }

    public void setPositions(Collection<VehiclePosition> positions)
    {
        this.positions = positions;
    }
}
