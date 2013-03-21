/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package domain;

/**
 *
 * @author Leslie Aerts
 */
public class VehiclePosition
{
    private String carTrackerId;
    private double carPos;
    private double carSpeed;

    public VehiclePosition(String carTrackerId, double carPos, double carSpeed)
    {
        this.carTrackerId = carTrackerId;
        this.carPos = carPos;
        this.carSpeed = carSpeed;
    }

    public String getCarTrackerId()
    {
        return carTrackerId;
    }

    public void setCarTrackerId(String carTrackerId)
    {
        this.carTrackerId = carTrackerId;
    }

    public double getCarPos()
    {
        return carPos;
    }

    public void setCarPos(double carPos)
    {
        this.carPos = carPos;
    }

    public double getCarSpeed()
    {
        return carSpeed;
    }

    public void setCarSpeed(double carSpeed)
    {
        this.carSpeed = carSpeed;
    }
    
    
}
