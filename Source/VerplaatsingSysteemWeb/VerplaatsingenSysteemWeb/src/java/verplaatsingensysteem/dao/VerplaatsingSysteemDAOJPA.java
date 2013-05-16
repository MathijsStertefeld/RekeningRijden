/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package verplaatsingensysteem.dao;

import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import verplaatsingensysteem.domain.Edge;
import verplaatsingensysteem.domain.Lane;
import verplaatsingensysteem.domain.Session;
import verplaatsingensysteem.domain.TimeStep;
import verplaatsingensysteem.domain.VehiclePosition;

/**
 *
 * @author Eagle
 */
@Stateless
public class VerplaatsingSysteemDAOJPA implements VerplaatsingSysteemDAO
{

    @PersistenceContext
    private EntityManager em;

    public VerplaatsingSysteemDAOJPA()
    {
    }

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
        String query = "SELECT t FROM TimeStep t WHERE t.timestepTime = '" + time + "'";
        TypedQuery q = em.createQuery(query, VehiclePosition.class);

        TimeStep ts = (TimeStep) q.getSingleResult();
        return ts;
    }

    @Override
    public Edge findEdge(String id)
    {
        Edge edge = em.find(Edge.class, id);
        return edge;
    }

    @Override
    public Lane findLane(String id)
    {
        Lane lane = em.find(Lane.class, id);

        return lane;
    }

    @Override
    public List<VehiclePosition> findVehiclePositions(String cartrackerId)
    {
        String query = "SELECT v FROM VehiclePosition v WHERE v.carTrackerId = '" + cartrackerId + "'";
        TypedQuery q = em.createQuery(query, VehiclePosition.class);
        List<VehiclePosition> positions = q.getResultList();
        System.out.println("RESULTLIST: " + q.getResultList());


//        for (int i = 0; i < positions.size(); i++)
//        {
//            VehiclePosition vp = positions.get(i);
//            
//            String query1 = "SELECT v.laneId FROM VehiclePosition v WHERE v.carTrackerId = '" + vp.getCarTrackerId() + "' AND v.id = '" + vp.getId() + "'";
//            TypedQuery q1 = em.createQuery(query1, String.class);
//            String laneId = (String) q1.getSingleResult();
//            String query2 = "SELECT l FROM Lane l WHERE l.laneId = '" + laneId +  "'";
//            TypedQuery q2 = em.createQuery(query2, Lane.class);
//            //vp.setParentLane(q2.getSingleResult());
//        }


        return positions;
    }
}
