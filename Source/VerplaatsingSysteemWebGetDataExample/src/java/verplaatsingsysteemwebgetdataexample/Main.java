/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package verplaatsingsysteemwebgetdataexample;

import java.util.List;
import verplaatsingensysteem.domain.VehiclePosition;

/**
 *
 * @author Eagle
 */
public class Main
{

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args)
    {
        GetData getData = new GetData();
        List<VehiclePosition> positions = getData.getVehiclePosition("t0");
        
        if (positions.size() > 0)
        {
            for (int i = 0; i < positions.size(); i++)
            {
                VehiclePosition vp = positions.get(i);
                System.out.println("Getting data for cartracker: " + vp.getCarTrackerId());
                System.out.println("Position = " + vp.getCarPos());
                System.out.println("Speed = " + vp.getCarSpeed());
                System.out.println("Id = " + vp.getId());
                System.out.println("Lane = " + vp.getParentLane());
                System.out.println("Timestep = " + vp.getParentTimeStep());
                System.out.println("----------------------");
            }
        }
        else
        {
            System.out.println("VehiclePosition list is empty");
        }
    }
}
