package domain;

import com.marbl.autosysteem.GeoPosition;

public class WorldMath {

    public static double Earth_Radius = 6371;

    /**
     * Calculates the distance in KM between 2 positions based on the fastest
     * great circle path.
     *
     * @param from Current Position
     * @param to Destination
     * @return Distance in km stored in a double
     */
    public static double DistanceInKM(GeoPosition from, GeoPosition to){
        double endLat = to.getLatitudeInRadians();
        double endLon = to.getLongitudeInRadians();
        double myLat = from.getLatitudeInRadians();
        double myLon = from.getLongitudeInRadians();
        double dLat = endLat - myLat;
        double dLon = endLon - myLon;

        // Berekenen van afstand vliegtuig tot vliegveld
        double a = Math.sin(dLat / 2) * Math.sin(dLat / 2)
                + Math.sin(dLon / 2) * Math.sin(dLon / 2) * Math.cos(myLat) * Math.cos(endLat);
        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
        double distance = Earth_Radius * c;

        return distance;
    }

    /**
     * Calculates the angle between 2 Positions
     *
     * @param from Initial Position
     * @param to Other Position
     * @return The bearing between the 2 positions stored in a double.
     */
    public static double AngleToInRadians(GeoPosition from, GeoPosition to) {
        double endLat = to.getLatitudeInRadians();
        double endLon = to.getLongitudeInRadians();
        double myLat = from.getLatitudeInRadians();
        double myLon = from.getLongitudeInRadians();
        
        double dLon = endLon - myLon;

        double y = Math.sin(dLon) * Math.cos(endLat);
        double x = Math.cos(myLat) * Math.sin(endLat)
                - Math.sin(myLat) * Math.cos(endLat) * Math.cos(dLon);
        double bearing = Math.atan2(y, x);

        return bearing;
    }

    /**
     * Calculate a new Position based on the bearing and distance.
     *
     * @param curPos Current Position
     * @param distance The distance in KM a double // denk in km
     * @param bearing The bearing in radians in a double
     * @return new Position
     */
    public static GeoPosition NewPositionWithAngleAndDistance(GeoPosition curPos, double distance, double bearing) {
        double myLat = curPos.getLatitudeInRadians();
        double myLon = curPos.getLongitudeInRadians();

        double newLat = Math.asin(Math.sin(myLat) * Math.cos(distance / Earth_Radius)
                + Math.cos(myLat) * Math.sin(distance / Earth_Radius) * Math.cos(bearing));
        double newLon = myLon + Math.atan2(Math.sin(bearing) * Math.sin(distance / Earth_Radius)
                * Math.cos(myLat), Math.cos(distance / Earth_Radius) - Math.sin(myLat) * Math.sin(newLat));
        GeoPosition newPos = new GeoPosition(Math.toDegrees(newLat), Math.toDegrees(newLon));
        //Misschien moet hierboven niet naar Degrees, maar ik denk van wel (Bas)

        return newPos;
    }
}
