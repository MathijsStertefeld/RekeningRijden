/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package database;

import domain.*;

/**
 *
 * @author Eagle
 */
public interface VerplaatsingSysteemDAO
{
    void edit();
    
    void find();
    
    void createEdge(Edge edge);
    
    void createLane(Lane lane);
    
    void createSession(Session session);
    
    void createTimeStep(TimeStep timeStep);
    
    void createVehiclePosition(VehiclePosition vehiclePosition);   
}
