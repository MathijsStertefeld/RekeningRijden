/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import javax.persistence.*;

/**
 *
 * @author Alexander Arends & Leslie Aerts
 */
@Entity

public class Lane implements Serializable
{
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private long id;
    private String lane_id;
    
    @OneToMany(cascade= CascadeType.PERSIST,mappedBy="parentLane")
    private Collection<VehiclePosition> positions;
    @ManyToOne(cascade= CascadeType.MERGE)
    private Edge parentEdge;
    
    public Lane()
    {
    }
    
    public Lane(String value,Edge parent)
    {
        this.lane_id = value;
        parentEdge = parent;
        positions = new ArrayList<VehiclePosition>();
    }
    
    public void addVehicle(VehiclePosition pos)
    {
        positions.add(pos);
    }

    public String getId()
    {
        return lane_id;
    }

    public void setId(String id)
    {
        this.lane_id = id;
    }

    public Collection<VehiclePosition> getPositions()
    {
        return positions;
    }

    public void setPositions(ArrayList<VehiclePosition> positions)
    {
        this.positions = positions;
    }
    
    
    
    
}
