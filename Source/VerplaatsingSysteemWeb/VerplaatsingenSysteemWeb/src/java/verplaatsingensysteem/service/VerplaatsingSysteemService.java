/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package verplaatsingensysteem.service;

import java.util.List;
import javax.ejb.Startup;
import javax.ejb.Stateless;
import javax.inject.Inject;
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
@Stateless
@Startup
public class VerplaatsingSysteemService
{

    @Inject
    private VerplaatsingSysteemDAO vpDAO;

    public VerplaatsingSysteemService()
    {
    }

    public void createSession(Session session)
    {
        try
        {
            vpDAO.createSession(session);

        } catch (Exception e)
        {
            System.err.println("Session creating failed");
            System.err.println(e);
        }
    }

    public void createTimeStep(TimeStep timeStep)
    {
        try
        {
            vpDAO.createTimeStep(timeStep);

        } catch (Exception e)
        {
            System.err.println("TimeStep creating failed");
            e.printStackTrace();
        }
    }

    public void createEdge(Edge edge)
    {

        try
        {
            vpDAO.createEdge(edge);
        } catch (Exception e)
        {
            System.err.println("Edge creating failed");
            e.printStackTrace();
        }
    }

    public void createLane(Lane lane)
    {
        try
        {
            vpDAO.createLane(lane);

        } catch (Exception e)
        {
            System.err.println("Lane creating failed");
            e.printStackTrace();
        }
    }

    public void createVehiclePosition(VehiclePosition vehiclePosition)
    {
        try
        {
            vpDAO.createVehiclePosition(vehiclePosition);

        } catch (Exception e)
        {
            System.err.println("VehiclePosition creating failed");
            e.printStackTrace();
        }
    }

    public List<VehiclePosition> getVehiclePositions(String cartrackerId)
    {

        try
        {
            List<VehiclePosition> positions = vpDAO.findVehiclePositions(cartrackerId);
            return positions;
        } catch (Exception e)
        {
            e.printStackTrace();
        }
        return null;
    }

    public TimeStep getTimeStep(double time)
    {
        //    EntityManager em = emf.createEntityManager();

        //  VerplaatsingSysteemDAOJPA vpDAO = new VerplaatsingSysteemDAOJPA(em);

        try
        {
            TimeStep timeStep = vpDAO.findTimeStep(time);

            return timeStep;
        } catch (Exception e)
        {
            e.printStackTrace();
        }
        return null;
    }

    public Lane getLane(String id)
    {
        //    EntityManager em = emf.createEntityManager();

        //   VerplaatsingSysteemDAOJPA vpDAO = new VerplaatsingSysteemDAOJPA(em);

        try
        {
            Lane lane = vpDAO.findLane(id);

            return lane;
        } catch (Exception e)
        {
            e.printStackTrace();
        }
        return null;
    }

    public Edge getEdge(String id)
    {
        //    EntityManager em = emf.createEntityManager();

        //   VerplaatsingSysteemDAOJPA vpDAO = new VerplaatsingSysteemDAOJPA(em);

        try
        {
            Edge edge = vpDAO.findEdge(id);

            return edge;
        } catch (Exception e)
        {
            e.printStackTrace();
        }
        return null;
    }
}
