/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import javax.persistence.*;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Leslie Aerts & Alexander Arends
 */
@Entity
@Table(name = "SIMSESSION")
@XmlRootElement(name = "collection")
public class Session implements Serializable
{

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    @Temporal(TemporalType.DATE)
    private Date sessionDate;
    //@OneToMany(cascade= CascadeType.PERSIST,mappedBy="parentSession")
    @Transient
    private Collection<TimeStep> timesteps;

    public Session()
    {
    }

    public Session(Date sessionDate, ArrayList<TimeStep> timesteps)
    {
        this.sessionDate = sessionDate;
        this.timesteps = timesteps;
    }

    public Date getSessionDate()
    {
        return sessionDate;
    }

    public void setSessionDate(Date sessionDate)
    {
        this.sessionDate = sessionDate;
    }

    @XmlElement(name = "timestep")
    public Collection<TimeStep> getTimesteps()
    {
        return timesteps;
    }

    public TimeStep getTimeStep(double time)
    {
        for (TimeStep ts : timesteps)
        {
            if (ts.getTime() == time)
            {
                return ts;
            }
        }
        return null;
    }

    public void setTimesteps(Collection<TimeStep> timesteps)
    {
        this.timesteps = timesteps;
    }

    @XmlTransient
    public long getId()
    {
        return id;
    }

    public void setId(long id)
    {
        this.id = id;
    }
}
