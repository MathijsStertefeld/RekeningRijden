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
    
    private final EntityManagerFactory emf = Persistence.createEntityManagerFactory("auctionPU");
    
    public VerplaatsingSysteemService()
    {
    }
    
    public void createEdge(Edge edge)
    {
        EntityManager em = emf.createEntityManager();
        VerplaatsingSysteemDAOJPA vpDAO = new VerplaatsingSysteemDAOJPA(em);
        
        try
        {
            vpDAO.createEdge(edge);
        }
        catch (Exception e)
        {
            System.err.println("Edge creating failed");
        }
    }
    
    public void createLane(Lane lane)
    {
        EntityManager em = emf.createEntityManager();
        VerplaatsingSysteemDAOJPA vpDAO = new VerplaatsingSysteemDAOJPA(em);
        
        try
        {
            vpDAO.createLane(lane);
        }
        catch (Exception e)
        {
            System.err.println("Lane creating failed");
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
        }
    }
}
