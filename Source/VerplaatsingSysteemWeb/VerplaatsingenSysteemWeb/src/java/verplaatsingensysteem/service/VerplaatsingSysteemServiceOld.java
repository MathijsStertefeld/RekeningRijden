/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package verplaatsingensysteem.service;

import javax.ejb.Startup;
import javax.ejb.Stateless;
import verplaatsingensysteem.dao.VerplaatsingSysteemDAOJPA;
import javax.persistence.*;
import verplaatsingensysteem.dao.VerplaatsingSysteemDAO;
import verplaatsingensysteem.domain.Edge;
import verplaatsingensysteem.domain.Lane;
import verplaatsingensysteem.domain.Session;
import verplaatsingensysteem.domain.TimeStep;
import verplaatsingensysteem.domain.VehiclePosition;

/**
 *
 * @author Eagle + Leslie
 */

        
public class VerplaatsingSysteemServiceOld
{
    
    //VerplaatsingSysteemDAO vpDAO;
    private final EntityManagerFactory emf;

    public VerplaatsingSysteemServiceOld()
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
        }
        catch (Exception e)
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
        }
        catch (Exception e)
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
        }
        catch (Exception e)
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
        }
        catch (Exception e)
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
        }
        catch (Exception e)
        {
            System.err.println("VehiclePosition creating failed");
            System.err.println(e);
        }
    }

    public VehiclePosition getVehiclePosition(String cartrackerId)
    {
        EntityManager em = emf.createEntityManager();

        VerplaatsingSysteemDAOJPA vpDAO = new VerplaatsingSysteemDAOJPA(em);

        try
        {
            VehiclePosition vehiclePosition = vpDAO.findVehiclePosition(cartrackerId);
            return vehiclePosition;
        }
        catch (Exception e)
        {
            return null;
        }
    }

    public TimeStep getTimeStep(double time)
    {
        EntityManager em = emf.createEntityManager();

        VerplaatsingSysteemDAOJPA vpDAO = new VerplaatsingSysteemDAOJPA(em);

        try
        {
            TimeStep timeStep = vpDAO.findTimeStep(time);

            return timeStep;
        }
        catch (Exception e)
        {
            return null;
        }
    }

    public Lane getLane(String id)
    {
        EntityManager em = emf.createEntityManager();

        VerplaatsingSysteemDAOJPA vpDAO = new VerplaatsingSysteemDAOJPA(em);

        try
        {
            Lane lane = vpDAO.findLane(id);

            return lane;
        }
        catch (Exception e)
        {
            return null;
        }
    }

    public Edge getEdge(String id)
    {
        EntityManager em = emf.createEntityManager();

        VerplaatsingSysteemDAOJPA vpDAO = new VerplaatsingSysteemDAOJPA(em);

        try
        {
            Edge edge = vpDAO.findEdge(id);

            return edge;
        }
        catch (Exception e)
        {
            return null;
        }
    }
}
