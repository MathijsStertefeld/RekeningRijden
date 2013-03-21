/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package domain;

import java.util.ArrayList;

/**
 *
 * @author Leslie Aerts
 */
public class Lane
{

    private String id;
    private ArrayList<VehiclePosition> positions;
    
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

    public ArrayList<VehiclePosition> getPositions()
    {
        return positions;
    }

    public void setPositions(ArrayList<VehiclePosition> positions)
    {
        this.positions = positions;
    }
    
    
    
    
}
