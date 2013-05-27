package domain;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
import com.marbl.autosysteem.GeoPosition;
import org.openstreetmap.osmosis.core.domain.v0_6.Node;

/**
 *
 * @author Leslie Aerts
 */
public class Navigation
{
    private static final double Earth_Radius = 6371;

    
    /**
     * Calculates the car speed needed for the simulation.
     * @param km The speed you want the car to go in km/h.
     * @return Returns the car speed in simulation units.
     */
    public static double getCarSpeedCalc(double km)
    {
        double sp = 0;
        km /= 3600;
        sp = km / 2;
        return sp;
    }

    /**
     * Moves the geoposition along a linear line between the two positions.
     * @param from Begin node
     * @param to Destination node
     * @param distance Distance to travel
     * @return The new position along the line.
     */
    @Deprecated
    public static GeoPosition navigate(Node from, Node to, double distance)
    {
        double startLat = deg2rad(from.getLatitude());
        double startLon = deg2rad(from.getLongitude());

        //in KM
        double distanceToTravel = distance;
        double bearing = getBearing(from, to);

        double newLat = Math.asin(Math.sin(startLat) * Math.cos(distanceToTravel / Earth_Radius)
                + Math.cos(startLat) * Math.sin(distanceToTravel / Earth_Radius) * Math.cos(bearing));

        double newLon = startLon + Math.atan2(Math.sin(bearing) * Math.sin(distanceToTravel / Earth_Radius) * Math.cos(startLat),
                Math.cos(distanceToTravel / Earth_Radius) - Math.sin(startLat) * Math.sin(newLat));
        GeoPosition nextPoint = new GeoPosition(rad2deg(newLat), rad2deg(newLon));

        return nextPoint;
    }

    /**
     * Calculates the radians from degrees.
     * @param deg The degrees.
     * @return The radians.
     */
    private static double deg2rad(double deg)
    {
        return (deg * Math.PI / 180.0);
    }

    /**
     * Calculates the degrees from radians.
     * @param rad The radians.
     * @return The degrees.
     */
    private static double rad2deg(double rad)
    {
        return (rad * 180 / Math.PI);
    }

    /**
     * Calculates the bearing.
     * @param from Current node location
     * @param to Node destination
     * @return The bearing
     */
    private static double getBearing(Node from, Node to)
    {
        double endLat = deg2rad(to.getLatitude());
        double endLon = deg2rad(to.getLongitude());
        double myLat = deg2rad(from.getLatitude());
        double myLon = deg2rad(from.getLongitude());
        
        double dLon = endLon - myLon;

        double y = Math.sin(dLon) * Math.cos(endLat);
        double x = Math.cos(myLat) * Math.sin(endLat)
                - Math.sin(myLat) * Math.cos(endLat) * Math.cos(dLon);
        double bearing = Math.atan2(y, x);

        return bearing;
    }

    /**
     * Calculates the distance.
     * @param lat1 Start Latitude
     * @param lon1 Start Longitude
     * @param lat2 End Latitude
     * @param lon2 Start Longitude
     * @param unit Distance Unit (K, M)
     * @return 
     */
    public static double getDistance(double lat1, double lon1, double lat2, double lon2, char unit)
    {
        long R = 6371; // km
        double dLat = deg2rad(lat2 - lat1);
        double dLon = deg2rad(lon2 - lon1);
        double lat11 = deg2rad(lat1);
        double lat22 = deg2rad(lat2);

        double a = Math.sin(dLat / 2) * Math.sin(dLat / 2)
                + Math.sin(dLon / 2) * Math.sin(dLon / 2) * Math.cos(lat11) * Math.cos(lat22);
        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
        double d = R * c;
        System.out.println(d);
        return d;
    }
}
