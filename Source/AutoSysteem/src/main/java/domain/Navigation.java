/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package domain.simulation;

import domain.GeoPosition;
import org.openstreetmap.osmosis.core.domain.v0_6.Node;

/**
 *
 * @author Leslie Aerts
 */
public class Navigation
{
    private static final double Earth_Radius = 6371;

    public static double getCarSpeedCalc(double km)
    {
        double sp = 0;
        km /= 3600;
        sp = km / 2;
        return sp;
    }

    public static GeoPosition navigate(Node from, Node to, double distance)
    {
        double startLat = deg2rad(from.getLatitude());
        double startLon = deg2rad(from.getLongitude());

        //in KM
        double distanceToTravel = distance;

        //double maxDistance = getDistance(from.getLatitude(), from.getLongitude(), to.getLatitude(), to.getLongitude(), 'M');

        double bearing = getBearing(from, to);

        double newLat = Math.asin(Math.sin(startLat) * Math.cos(distanceToTravel / Earth_Radius)
                + Math.cos(startLat) * Math.sin(distanceToTravel / Earth_Radius) * Math.cos(bearing));

        //        var lat2 = Math.asin(Math.sin(lat1) * Math.cos(d / R)
        //                + Math.cos(lat1) * Math.sin(d / R) * Math.cos(brng));

        double newLon = startLon + Math.atan2(Math.sin(bearing) * Math.sin(distanceToTravel / Earth_Radius) * Math.cos(startLat),
                Math.cos(distanceToTravel / Earth_Radius) - Math.sin(startLat) * Math.sin(newLat));

        //        var lon2 = lon1 + Math.atan2(Math.sin(brng) * Math.sin(d / R) * Math.cos(lat1),
        //                Math.cos(d / R) - Math.sin(lat1) * Math.sin(lat2));

        GeoPosition nextPoint = new GeoPosition(rad2deg(newLat), rad2deg(newLon));

        return nextPoint;
    }

    private static double deg2rad(double deg)
    {
        return (deg * Math.PI / 180.0);
    }

    private static double rad2deg(double rad)
    {
        return (rad * 180 / Math.PI);
    }

    private static double getBearing(Node from, Node to)
    {
        double endLat = deg2rad(to.getLatitude());
        double endLon = deg2rad(to.getLongitude());
        double myLat = deg2rad(from.getLatitude());
        double myLon = deg2rad(from.getLongitude());
        //double dLat = endLat - myLat; // Not used here
        double dLon = endLon - myLon;

        double y = Math.sin(dLon) * Math.cos(endLat);
        double x = Math.cos(myLat) * Math.sin(endLat)
                - Math.sin(myLat) * Math.cos(endLat) * Math.cos(dLon);
        double bearing = Math.atan2(y, x);

        return bearing;
    }

    private static double getDistance(double lat1, double lon1, double lat2, double lon2, char unit)
    {
        double theta = lon1 - lon2;
        double dist = Math.sin(deg2rad(lat1)) * Math.sin(deg2rad(lat2)) + Math.cos(deg2rad(lat1)) * Math.cos(deg2rad(lat2)) * Math.cos(deg2rad(theta));
        dist = Math.acos(dist);
        dist = rad2deg(dist);
        dist = dist * 60 * 1.1515;
        if (unit == 'K')
        {
            dist = dist * 1.609344;
        } else
        {
            if (unit == 'N')
            {
                dist = dist * 0.8684;
            }
        }
        return (dist);
    }
}
