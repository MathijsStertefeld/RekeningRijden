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
    private String id;
    
    @OneToMany(cascade= CascadeType.ALL)
    private Collection<VehiclePosition> positions;

    public Lane()
    {
    }
    
    public Lane(String value)
    {
        this.id = value;
        positions = new ArrayList<VehiclePosition>();
    }
    
    public void addVehicle(VehiclePosition pos)
    {
        positions.add(pos);
    }

    public String getId()
    {
        return id;
    }

    public void setId(String id)
    {
        this.id = id;
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
