/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package domain_simulation;

import domain.VehiclePosition;
import openstreetmaps.org.openstreetmap.gui.CarGraphic;

/**
 *
 * @author Leslie Aerts
 */
public class Car
{

    private String carTrackerId;
    private double longitude;
    private double latitude;
    private VehiclePosition pos;
    private CarGraphic graphic;

    public Car(String idString)
    {
        graphic = new CarGraphic(carTrackerId);
        this.carTrackerId = idString;
        pos = new VehiclePosition();
    }

    public String getCarTrackerId()
    {
        return carTrackerId;
    }

    public void setCarTrackerId(String carId)
    {
        this.carTrackerId = carId;
    }

    public VehiclePosition getPos()
    {
        return pos;
    }

    public void setPos(VehiclePosition pos)
    {
        this.pos = pos;
    }

    public CarGraphic getCarGraphic()
    {
        return graphic;
    }

    public void move(double lat, double lon)
    {
        this.graphic.move(lat, lon);
    }
}
