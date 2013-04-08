/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import domain.*;

/**
 *
 * @author Eagle
 */
public interface VerplaatsingSysteemDAO
{
    void createSession(Session session);

    Session findSession(long id);

    void createTimeStep(TimeStep timeStep);

    TimeStep findTimeStep(String id);

    void createEdge(Edge edge);

    Edge findEdge(String id);

    void createLane(Lane lane);

    Lane findLane(String id);

    void createVehiclePosition(VehiclePosition vehiclePosition);
    
    VehiclePosition findVehiclePosition(String id);
}
