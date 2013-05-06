/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package verplaatsingensysteem.dao;

import verplaatsingensysteem.domain.Edge;
import verplaatsingensysteem.domain.Lane;
import verplaatsingensysteem.domain.Session;
import verplaatsingensysteem.domain.TimeStep;
import verplaatsingensysteem.domain.VehiclePosition;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author Eagle
 */
public class VerplaatsingSysteemDAOJPA implements VerplaatsingSysteemDAO
{

    @PersistenceContext
    private EntityManager em;

    public VerplaatsingSysteemDAOJPA(EntityManager em)
    {
        this.em = em;
    }

    @Override
    public void createSession(Session session)
    {
        em.persist(session);
    }

    @Override
    public void createTimeStep(TimeStep timeStep)
    {
        em.persist(timeStep);
    }

    @Override
    public void createEdge(Edge edge)
    {
        if (findEdge(edge.getId()) == null)
        {
            em.persist(edge);
        }
    }

    @Override
    public void createLane(Lane lane)
    {
        if (findLane(lane.getId()) == null)
        {
            em.persist(lane);
        }
    }

    @Override
    public void createVehiclePosition(VehiclePosition vehiclePosition)
    {
        em.persist(vehiclePosition);
    }

    @Override
    public Session findSession(long id)
    {
        Session s = em.find(Session.class, id);
        return s;
    }

    @Override
    public TimeStep findTimeStep(double time)
    {
        Query q = em.createQuery("SELECT * FROM TIMESTEP WHERE time = '" + time + "'");
        TimeStep ts = (TimeStep) q.getSingleResult();
        return ts;
    }

    @Override
    public Edge findEdge(String id)
    {
        Edge ts = em.find(Edge.class, id);
        return ts;
    }

    @Override
    public Lane findLane(String id)
    {
        Lane ts = em.find(Lane.class, id);
        return ts;
    }

    @Override
    public VehiclePosition findVehiclePosition(String id)
    {
        VehiclePosition vehiclePosition = em.find(VehiclePosition.class, id);
        return vehiclePosition;
    }
}