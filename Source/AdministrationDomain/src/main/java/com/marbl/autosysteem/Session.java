/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.marbl.autosysteem;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Leslie Aerts
 */
@Entity
@XmlRootElement(name = "collection")
public class Session
{

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private int authCode = 0;
    
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date date;
    private Collection<TimeStep> timesteps;

    public Session()
    {
    }

    public Session(Date date, ArrayList<TimeStep> timesteps)
    {
        this.timesteps = timesteps;
    }

    @XmlAttribute(name = "date")
    public Date getDate()
    {
        return date;
    }

    public void setDate(Date date)
    {
        this.date = date;
    }

    public Collection<TimeStep> getTimesteps()
    {
        return timesteps;
    }

    public void setTimesteps(Collection<TimeStep> timesteps)
    {
        this.timesteps = timesteps;
    }

    public int getAuthCode()
    {
        return authCode;
    }

    public void setAuthCode(int auth)
    {
        this.authCode = auth;
    }
}
