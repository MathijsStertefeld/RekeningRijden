/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package domain;

import java.util.List;

/**
 *
 * @author Leslie Aerts
 */
public class Edge
{

    private String id;
    private List<Lane> lanes;
    
    public Edge(String id)
    {
        this.id = id;
    }

    public String getId()
    {
        return id;
    }

    public void setId(String id)
    {
        this.id = id;
    }

    public List<Lane> getLanes()
    {
        return lanes;
    }

    public void setLanes(List<Lane> lanes)
    {
        this.lanes = lanes;
    }
    
    public void addLane(Lane l)
    {
        lanes.add(l);
    }
    
}
