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

   // @Id
   // @GeneratedValue(strategy = GenerationType.AUTO)
   // private long id;
    
    @Id
    private String edge_id;
    //@OneToMany(cascade= CascadeType.PERSIST,mappedBy = "parentEdge")
    
    @Transient
    private Collection<Lane> lanes;
//    @ManyToOne(cascade = CascadeType.MERGE)
//    private TimeStep parentTimeStep;

    public Edge()
    {
    }

    public Edge(String id, TimeStep parent)
    {
        this.edge_id = id;
        lanes = new ArrayList<Lane>();
        //parentTimeStep = parent;
    }

    public String getId()
    {
        return edge_id;
    }

    public void setId(String id)
    {
        this.edge_id = id;
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
