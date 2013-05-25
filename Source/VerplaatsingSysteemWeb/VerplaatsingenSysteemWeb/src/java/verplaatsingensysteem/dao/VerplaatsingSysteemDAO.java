/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package verplaatsingensysteem.dao;

import java.util.Date;
import java.util.List;
import verplaatsingensysteem.domain.Edge;
import verplaatsingensysteem.domain.Lane;
import verplaatsingensysteem.domain.Movement;
import verplaatsingensysteem.domain.Session;
import verplaatsingensysteem.domain.TimeStep;
import verplaatsingensysteem.domain.VehiclePosition;

/**
 *
 * @author Eagle
 */
public interface VerplaatsingSysteemDAO
{
    void createSession(Session session);

    Session findSession(long id);

    void createTimeStep(TimeStep timeStep);

    TimeStep findTimeStep(double time);

    void createEdge(Edge edge);

    Edge findEdge(String id);

    void createLane(Lane lane);

    Lane findLane(String id);

    void createVehiclePosition(VehiclePosition vehiclePosition);
    
    List<VehiclePosition> findVehiclePositions(String cartrackerId) ;
    
    void createMovement(Movement movement);

    Movement findMovement(long id);
    
    List<Movement> findAllMovements();
   
    
}
