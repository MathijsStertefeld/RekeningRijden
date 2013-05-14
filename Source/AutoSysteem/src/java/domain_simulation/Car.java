/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package domain_simulation;

import domain.VehiclePosition;

/**
 *
 * @author Leslie Aerts
 */
public class Car
{

    private String carId;
    private VehiclePosition pos;

    public Car(String idString)
    {
        this.carId = idString;
        pos = new VehiclePosition();
    }

    public String getCarId()
    {
        return carId;
    }

    public void setCarId(String carId)
    {
        this.carId = carId;
    }

    public VehiclePosition getPos()
    {
        return pos;
    }

    public void setPos(VehiclePosition pos)
    {
        this.pos = pos;
    }

    public void move(long speed)
    {
        this.pos.setCarPos(this.pos.getCarPos() + speed);
    }
}
