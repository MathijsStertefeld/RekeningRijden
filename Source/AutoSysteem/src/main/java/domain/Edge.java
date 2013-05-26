/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package domain;

import javax.persistence.Id;
import javax.xml.bind.annotation.XmlAttribute;

/**
 *
 * @author Leslie Aerts
 */
class Edge
{
    @Id
    private String edge_id;

    public Edge()
    {
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

}
