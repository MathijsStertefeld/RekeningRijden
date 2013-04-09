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

/**
 *
 * @author Leslie Aerts & Alexander Arends
 */
@Entity
@Table(name = "simsession")
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

    public Collection<TimeStep> getTimesteps()
    {
        return timesteps;
    }

    public void setTimesteps(Collection<TimeStep> timesteps)
    {
        this.timesteps = timesteps;
    }

    public Long getId()
    {
        return id;
    }

    public void setId(Long id)
    {
        this.id = id;
    }
}
