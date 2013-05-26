/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package domain;

/**
 *
 * @author Leslie Aerts
 */
public class GeoPosition {

    //private final static double DEGREES_TO_RADIANS = Math.PI / 180d;
    //private final static double RADIANS_TO_DEGREES = 180d / Math.PI;
    
    //Always save these in degrees
    private double longitude;
    private double latitude;

    public GeoPosition(double lat, double lon) {
        this.longitude = lon;
        this.latitude = lat;
    }

    public double getLongitudeInDegrees() {
        return longitude;
    }
    
    public double getLongitudeInRadians(){
        return Math.toRadians(longitude);
    }

    public void setLongitudeInDegrees(double longitude) {
        this.longitude = longitude;
    }
    
    public void setLongitudeInRadians(double longitude){
        this.longitude = Math.toDegrees(longitude);
    }

    public double getLatitudeInDegrees() {
        return latitude;
    }
    
    public double getLatitudeInRadians(){
        return Math.toRadians(latitude);
    }

    public void setLatitudeInDegrees(double latitude) {
        this.latitude = latitude;
    }
    
    public void setLatitudeInRadians(double latitude){
        this.latitude = Math.toDegrees(latitude);
    }
}
