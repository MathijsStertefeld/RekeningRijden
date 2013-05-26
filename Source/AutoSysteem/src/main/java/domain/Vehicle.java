/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package domain;

import domain.simulation.Navigation;
import com.marbl.administration.domain.Car;
import java.util.ArrayList;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;
import openstreetmaps.org.openstreetmap.gui.CarGraphic;
import org.openstreetmap.osmosis.core.domain.v0_6.Node;

/**
 *
 * @author Leslie Aerts
 */
@XmlRootElement(name="vehicle_position")
public class Vehicle extends com.marbl.administration.domain.Car
{
    private double carSpeed;
    private CarGraphic graphic;
    private Route route;

    public Vehicle(Car c, ArrayList<Node> routeNodes)
    {
        super(c.getCarTrackerId(), c.getLicensePlate(), c.getType(), c.getPaintColor(), c.getMass(), c.getClassification(), c.getBrand(), c.getModel(), c.getDriverBsn());
        this.route = new Route(routeNodes);
        Node n = this.route.getRoute().get(0);
        graphic = new CarGraphic(this.getCarTrackerId(), n.getLatitude(), n.getLongitude());
        this.carSpeed = Navigation.getCarSpeedCalc(50);
    }

//    @Override
//    @XmlAttribute(name="tracker_id")
//    public String getCarTrackerId()
//    {
//        return this.getCarTrackerId();
//    }
    
    public double getCarSpeedInKM()
    {
        return this.carSpeed * 2 * 3600;
    }
    
    
    public double getCarSpeed()
    {
        return carSpeed;
    }

    public void setCarSpeed(double carSpeed)
    {
        this.carSpeed = Navigation.getCarSpeedCalc(carSpeed);
    }

    public GeoPosition getPosition()
    {
        return new GeoPosition(graphic.getLat(), graphic.getLon());
    }

    public void setPosition(GeoPosition newPos)
    {
        graphic.place(newPos.getLatitudeInDegrees(), newPos.getLongitudeInDegrees());
    }

    public CarGraphic getCarGraphic()
    {
        return graphic;
    }

    public Route getRoute()
    {
        return route;
    }

    public void setRoute(ArrayList<Node> route)
    {
        this.route = new Route(route);
    }

    /**
     * Places the car directly at the position.
     *
     * @param lat
     * @param lon
     */
    public void place(double lat, double lon)
    {
        this.graphic.place(lat, lon);
    }
}