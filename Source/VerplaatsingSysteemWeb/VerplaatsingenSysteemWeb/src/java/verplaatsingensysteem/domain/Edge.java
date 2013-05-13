/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package verplaatsingensysteem.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import javax.persistence.*;
import javax.xml.bind.Unmarshaller;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Alexander Arends & Leslie Aerts
 */
@Entity
@XmlRootElement
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
    @Transient
    @XmlTransient
    private TimeStep parentTimeStep;

    public Edge()
    {
    }

    public Edge(String id, TimeStep parent)
    {
        this.edge_id = id;
        lanes = new ArrayList<Lane>();
        //parentTimeStep = parent;
    }

    @XmlAttribute(name = "id")
    public String getId()
    {
        return edge_id;
    }

    public void setId(String id)
    {
        this.edge_id = id;
    }

    @XmlElement(name = "lane")
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

    public void afterUnmarshal(Unmarshaller u, Object parent)
    {
        this.parentTimeStep = (TimeStep) parent;
    }

    @XmlTransient
    public TimeStep getParentTimeStep()
    {
        return parentTimeStep;
    }
}
