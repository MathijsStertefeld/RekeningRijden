/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package domain;

import java.util.ArrayList;
import java.util.Date;
import javax.persistence.*;

/**
 *
 * @author Leslie Aerts & Alexander Arends
 */
@Entity
@Table (name = "SIMSESSION")
public class Session
{
    
    
    @Temporal(TemporalType.DATE)
    private Date sessionDate;
    
    private ArrayList<TimeStep> timesteps;
    
    @Id
    private Long id;

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

    public ArrayList<TimeStep> getTimesteps()
    {
        return timesteps;
    }

    public void setTimesteps(ArrayList<TimeStep> timesteps)
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
