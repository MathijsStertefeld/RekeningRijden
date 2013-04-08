/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package database;

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

    public void createEdge(Edge edge)
    {
        System.out.println("Creating Edge...");
        EntityManager em = emf.createEntityManager();
        VerplaatsingSysteemDAOJPA vpDAO = new VerplaatsingSysteemDAOJPA(em);

        try
        {
            vpDAO.createEdge(edge);
        }
        catch (Exception e)
        {
            System.err.println("Edge creating failed");
            System.err.println(e);
        }
    }

    public void createLane(Lane lane)
    {
        System.out.println("Creating Lane...");
        EntityManager em = emf.createEntityManager();
        VerplaatsingSysteemDAOJPA vpDAO = new VerplaatsingSysteemDAOJPA(em);

        try
        {
            vpDAO.createLane(lane);
        }
        catch (Exception e)
        {
            System.err.println("Lane creating failed");
            System.err.println(e);
        }
    }

    public void createTimeStep(TimeStep timeStep)
    {
        EntityManager em = emf.createEntityManager();
        VerplaatsingSysteemDAOJPA vpDAO = new VerplaatsingSysteemDAOJPA(em);

        try
        {
            vpDAO.createTimeStep(timeStep);
        }
        catch (Exception e)
        {
            System.err.println("TimeStep creating failed");
            System.err.println(e);
        }
    }

    public void createSession(Session session)
    {
        EntityManager em = emf.createEntityManager();
        VerplaatsingSysteemDAOJPA vpDAO = new VerplaatsingSysteemDAOJPA(em);

        try
        {
            vpDAO.createSession(session);

        }
        catch (Exception e)
        {
            System.err.println("Session creating failed");
            System.err.println(e);
        }
    }

    public void createVehiclePosition(VehiclePosition vehiclePosition)
    {
        EntityManager em = emf.createEntityManager();
        VerplaatsingSysteemDAOJPA vpDAO = new VerplaatsingSysteemDAOJPA(em);

        try
        {
            vpDAO.createVehiclePosition(vehiclePosition);
        }
        catch (Exception e)
        {
            System.err.println("VehiclePosition creating failed");
            System.err.println(e);
        }
    }
}
