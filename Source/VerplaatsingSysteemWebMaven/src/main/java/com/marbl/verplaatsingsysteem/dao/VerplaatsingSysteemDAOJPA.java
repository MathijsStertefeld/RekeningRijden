/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.marbl.verplaatsingsysteem.dao;


import java.util.Date;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import com.marbl.autosysteem.*;

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

//    @Override
//    public void createLane(Lane lane)
//    {
//        if (findLane(lane.getId()) == null)
//        {
//            em.persist(lane);
//        }
//    }
//
//    @Override
//    public void createVehiclePosition(VehiclePosition vehiclePosition)
//    {
//        em.persist(vehiclePosition);
//    }

    @Override
    public Session findSession(long id)
    {
        Session s = em.find(Session.class, id);
        return s;
    }

    @Override
    public TimeStep findTimeStep(double time)
    {
        String query = "SELECT t FROM TimeStep t WHERE t.time = '" + time + "'";
        TypedQuery q = em.createQuery(query, TimeStep.class);

        TimeStep ts = (TimeStep) q.getSingleResult();
        return ts;
    }

    @Override
    public Edge findEdge(String id)
    {
        Edge edge = em.find(Edge.class, id);
        return edge;
    }

//    @Override
//    public Lane findLane(String id)
//    {
//        Lane lane = em.find(Lane.class, id);
//
//        return lane;
//    }
//
//    @Override
//    public List<VehiclePosition> findVehiclePositions(String cartrackerId)
//    {
//        String query = "SELECT v FROM VehiclePosition v WHERE v.carTrackerId = '" + cartrackerId + "'";
//        TypedQuery q = em.createQuery(query, VehiclePosition.class);
//        List<VehiclePosition> positions = q.getResultList();
//        // System.out.println("RESULTLIST: " + q.getResultList());
//
//        return positions;
//    }

    @Override
    public void createMovement(Movement movement)
    {
        if (findMovement(movement.getId()) == null)
        {
            em.persist(movement);
        }
    }

    @Override
    public Movement findMovement(long id)
    {
        Movement m = em.find(Movement.class, id);
        return m;
    }

    @Override
    public List<Movement> findAllMovements()
    {
        String query = "SELECT m FROM Movement";
        TypedQuery q = em.createQuery(query, Movement.class);
        List<Movement> movements = q.getResultList();

        return movements;
    }
}
