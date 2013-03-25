/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package domain;

import java.util.ArrayList;
import java.util.Date;

/**
 *
 * @author Leslie Aerts
 */
public class Session
{

    private Date sessionDate;
    private ArrayList<TimeStep> timesteps;

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
}
