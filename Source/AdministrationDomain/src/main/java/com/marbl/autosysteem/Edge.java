/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.marbl.autosysteem;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.xml.bind.annotation.XmlAttribute;

/**
 *
 * @author Leslie Aerts
 */
@Entity
public class Edge implements Serializable
{
    @Id
    private String edge_id;
    private String name;

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

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }
}
