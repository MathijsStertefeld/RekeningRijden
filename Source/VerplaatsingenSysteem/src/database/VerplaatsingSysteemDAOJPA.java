/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package database;

import domain.Edge;
import domain.Lane;
import domain.Session;
import domain.TimeStep;
import domain.VehiclePosition;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

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
    public void edit()
    {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void find()
    {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void createEdge(Edge edge)
    {
        em.persist(edge);
    }

    @Override
    public void createLane(Lane lane)
    {
        em.persist(lane);
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
    public void createVehiclePosition(VehiclePosition vehiclePosition)
    {
       em.persist(vehiclePosition);
    }
}
