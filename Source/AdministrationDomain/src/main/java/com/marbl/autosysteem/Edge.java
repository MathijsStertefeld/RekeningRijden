/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.marbl.autosysteem;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.xml.bind.annotation.XmlAttribute;

/**
 *
 * @author Leslie Aerts
 */
@Entity
public class Edge
{

    @Id
    private String edge_id;    
    private String name;
    private String type; // Values are "city", "highway" or "region".
    private String city;
    private String highway;
    private String region;

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

    public String getType()
    {
        return type;
    }

    public void setType(String type)
    {
        this.type = type;
    }

    public String getCity()
    {
        return city;
    }

    public void setCity(String city)
    {
        this.city = city;
    }

    public String getHighway()
    {
        return highway;
    }

    public void setHighway(String highway)
    {
        this.highway = highway;
    }

    public String getRegion()
    {
        return region;
    }

    public void setRegion(String region)
    {
        this.region = region;
    }
}
