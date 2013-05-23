/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package domain_simulation;

import java.util.ArrayList;
import openstreetmaps.org.openstreetmap.gui.CarGraphic;
import org.openstreetmap.osmosis.core.domain.v0_6.Node;
import org.openstreetmap.osmosis.core.domain.v0_6.Way;

/**
 *
 * @author Leslie Aerts
 */
public class Car
{
    //public final double Earth_Radius = 6371;

    private String carTrackerId;
    private VehiclePosition pos;
    private CarGraphic graphic;
    private Route route;

    public Car(String idString, ArrayList<Node> routeNodes)
    {
        this.route = new Route(routeNodes);
        Node n = this.route.getRoute().get(0);
        graphic = new CarGraphic(carTrackerId, n.getLatitude(), n.getLongitude());
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
     * @param lat
     * @param lon 
     */
    public void place(double lat, double lon)
    {
        this.graphic.place(lat, lon);
    }

    /**
     * Translates from the current position.
     * @param lat
     * @param lon 
     */
    public void move(double lat, double lon)
    {
        this.graphic.move(lat, lon);
    }
    
//    public void progress()
//    {
//        int progress = this.route.getProgress();
//        double distance = 0;
//        GeoPosition newPos = null;
//        Node fromNode = null;
//        if (progress != 0)
//        {
//            fromNode = this.route.getRoute().get(progress - 1);
//        } else
//        {
//            fromNode = this.route.getRoute().get(0);
//        }
//
//        if (progress < this.route.getRoute().size())
//        {
//            Node toNode = this.route.getRoute().get(progress);
//            distance = distance(getCarGraphic().getLat(), getCarGraphic().getLon(), toNode.getLatitude(), toNode.getLongitude(), 'K');
//            newPos = NewPositionWithAngleAndDistance(this, distance, getBearing(fromNode, toNode));
//
//            this.place(newPos.getLatitude(), newPos.getLongitude());
//            System.out.println(distance);
//            if (distance <= 1)
//            {
//                this.route.setProgress(progress + 1);
//            }
//        }
//    }
//    public double getBearing(Node from, Node to)
//    {
//        double endLat = deg2rad(to.getLatitude());
//        double endLon = deg2rad(to.getLongitude());
//        double myLat = deg2rad(from.getLatitude());
//        double myLon = deg2rad(from.getLongitude());
//        //double dLat = endLat - myLat; // Not used here
//        double dLon = endLon - myLon;
//
//        double y = Math.sin(dLon) * Math.cos(endLat);
//        double x = Math.cos(myLat) * Math.sin(endLat)
//                - Math.sin(myLat) * Math.cos(endLat) * Math.cos(dLon);
//        double bearing = Math.atan2(y, x);
//
//        return bearing;
//    }
//
//    private double deg2rad(double deg)
//    {
//        return (deg * Math.PI / 180.0);
//    }
//
//    private double rad2deg(double rad)
//    {
//        return (rad * 180 / Math.PI);
//    }
//
//    private double distance(double lat1, double lon1, double lat2, double lon2, char unit)
//    {
//        double theta = lon1 - lon2;
//        double dist = Math.sin(deg2rad(lat1)) * Math.sin(deg2rad(lat2)) + Math.cos(deg2rad(lat1)) * Math.cos(deg2rad(lat2)) * Math.cos(deg2rad(theta));
//        dist = Math.acos(dist);
//        dist = rad2deg(dist);
//        dist = dist * 60 * 1.1515;
//        if (unit == 'K')
//        {
//            dist = dist * 1.609344;
//        } else
//        {
//            if (unit == 'N')
//            {
//                dist = dist * 0.8684;
//            }
//        }
//        return (dist);
//    }
//
//    // (tonode - from node) * currentpos /  distance = % van edge  + fromnode
//    private GeoPosition NewPositionWithAngleAndDistance(Car curPos, double distance, double bearing)
//    {
//        double radLat = deg2rad(curPos.getCarGraphic().getLat());
//        double radLon = deg2rad(curPos.getCarGraphic().getLon());
//
//        double newLat = Math.asin(Math.sin(radLat) * Math.cos(distance / Earth_Radius) + Math.cos(radLat) * Math.sin(distance / Earth_Radius) * Math.cos(bearing));
//        double newLon = radLon + Math.atan2(Math.sin(bearing) * Math.sin(distance / Earth_Radius)
//                * Math.cos(radLat), Math.cos(distance / Earth_Radius) - Math.sin(radLat) * Math.sin(newLat));
//        GeoPosition newPos = new GeoPosition(newLon, newLat);
//        return newPos;
//    }
}
