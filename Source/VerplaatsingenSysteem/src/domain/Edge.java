/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package domain;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.*;

/**
 *
 * @author Alexander Arends & Leslie Aerts
 */
@Entity
@Table (name = "EDGE")
public class Edge
{

    @Id
    private String id;
    
    @OneToMany
    private ArrayList<Lane> lanes;

    public Edge()
    {
    }
    
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
