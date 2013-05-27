/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.marbl.autosysteem;

/**
 *
 * @author Leslie Aerts
 */
public class GeoPosition {
    
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
