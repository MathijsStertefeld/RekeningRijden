/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package domain;

import com.marbl.administration.domain.Car;
import com.marbl.autosysteem.Edge;
import com.marbl.autosysteem.GeoPosition;
import java.util.ArrayList;
import openstreetmaps.org.openstreetmap.gui.CarGraphic;
import org.openstreetmap.osmosis.core.domain.v0_6.Node;
import org.openstreetmap.osmosis.core.domain.v0_6.Way;

/**
 *
 * @author Leslie Aerts
 */
public class Vehicle extends com.marbl.administration.domain.Car
{

    private boolean finished;
    private double carSpeed;
    private CarGraphic graphic;
    private Route route;

    public Vehicle(Car c, ArrayList<Node> routeNodes)
    {
        super(c.getCarTrackerId(), c.getLicensePlate(), c.getType(), c.getPaintColor(), c.getMass(), c.getClassification(), c.getBrand(), c.getModel(), c.getDriverBSN());
        this.route = new Route(routeNodes);
        Node n = this.route.getRoute().get(0);
        graphic = new CarGraphic(this.getCarTrackerId(), n.getLatitude(), n.getLongitude());
        this.carSpeed = Navigation.getCarSpeedCalc(50);
        this.finished = false;
    }


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

    public Edge getCurrentEdge()
    {
        Node n = this.getRoute().getRoute().get(this.getRoute().getTargetIndex());
        Way w = Osmosis.getWayFromNode(n);
        Edge e = Osmosis.createNewEdgeFromWay(w);
        String name = Osmosis.getValueOfWay(w, "name");

        if (name.length() == 0 || name == null)
        {
            name = "empty";
        }
        e.setName(name);
        return e;
    }

    public boolean isFinished()
    {
        return finished;
    }

    public void setFinished(boolean finished)
    {
        this.finished = finished;
    }
}