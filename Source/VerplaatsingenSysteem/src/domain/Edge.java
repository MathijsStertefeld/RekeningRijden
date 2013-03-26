/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package domain;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Leslie Aerts
 */
public class Edge
{

    private String id;
    private ArrayList<Lane> lanes;
    
    public Edge(String id)
    {
        this.id = id;
        lanes = new ArrayList<Lane>();
    }

    public String getId()
    {
        return id;
    }

    public void setId(String id)
    {
        this.id = id;
    }

    public ArrayList<Lane> getLanes()
    {
        return lanes;
    }

    public void setLanes(ArrayList<Lane> lanes)
    {
        this.lanes = lanes;
    }
    
    public void addLane(Lane l)
    {
        lanes.add(l);
    }
    
}
