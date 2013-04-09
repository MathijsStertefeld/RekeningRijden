/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import dao.VerplaatsingSysteemDAOJPA;
import domain.*;
import javax.persistence.*;

/**
 *
 * @author Eagle
 */
public class VerplaatsingSysteemService
{
    private final EntityManagerFactory emf;

    public VerplaatsingSysteemService()
    {
        emf = Persistence.createEntityManagerFactory("VerplaatsingenSysteemPU");
        System.out.println("Factory created");
    }

    public void createSession(Session session)
    {
        EntityManager em = emf.createEntityManager();
        VerplaatsingSysteemDAOJPA vpDAO = new VerplaatsingSysteemDAOJPA(em);

        //System.out.println("create session");
        try
        {
            em.getTransaction().begin();
            vpDAO.createSession(session);
            em.getTransaction().commit();
        } catch (Exception e)
        {
            System.err.println("Session creating failed");
            System.err.println(e);
        }
    }

    public void createTimeStep(TimeStep timeStep)
    {
        EntityManager em = emf.createEntityManager();
        VerplaatsingSysteemDAOJPA vpDAO = new VerplaatsingSysteemDAOJPA(em);

        //System.out.println("Creating Timestep...");
        try
        {
            em.getTransaction().begin();
            vpDAO.createTimeStep(timeStep);
            em.getTransaction().commit();
        } catch (Exception e)
        {
            System.err.println("TimeStep creating failed");
            System.err.println(e);
        }
    }

    public void createEdge(Edge edge)
    {
       // System.out.println("Creating Edge...");
        EntityManager em = emf.createEntityManager();
        VerplaatsingSysteemDAOJPA vpDAO = new VerplaatsingSysteemDAOJPA(em);

        try
        {
            em.getTransaction().begin();
            vpDAO.createEdge(edge);
            em.getTransaction().commit();
        } catch (Exception e)
        {
            System.err.println("Edge creating failed");
            System.err.println(e);
        }
    }

    public void createLane(Lane lane)
    {
       // System.out.println("Creating Lane...");
        EntityManager em = emf.createEntityManager();
        VerplaatsingSysteemDAOJPA vpDAO = new VerplaatsingSysteemDAOJPA(em);

        try
        {
            em.getTransaction().begin();
            vpDAO.createLane(lane);
            em.getTransaction().commit();
        } catch (Exception e)
        {
            System.err.println("Lane creating failed");
            System.err.println(e);
        }
    }

    public void createVehiclePosition(VehiclePosition vehiclePosition)
    {
        EntityManager em = emf.createEntityManager();
        VerplaatsingSysteemDAOJPA vpDAO = new VerplaatsingSysteemDAOJPA(em);

       // System.out.println("Creating vehicle pos");
        try
        {
            em.getTransaction().begin();
            vpDAO.createVehiclePosition(vehiclePosition);
            em.getTransaction().commit();
        } catch (Exception e)
        {
            System.err.println("VehiclePosition creating failed");
            System.err.println(e);
        }
    }
}
