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
public class Edge implements Serializable
{

    @Id
    private String id;
    @OneToMany(cascade= CascadeType.ALL)
    private Collection<Lane> lanes;

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

    public Collection<Lane> getLanes()
    {
        return lanes;
    }

    public void setLanes(Collection<Lane> lanes)
    {
        this.lanes = lanes;
    }

    public void addLane(Lane l)
    {
        lanes.add(l);
    }
}
