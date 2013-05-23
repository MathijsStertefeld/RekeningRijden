/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package domain_simulation;

/**
 *
 * @author Leslie Aerts
 */
public class GeoPosition
{

   private double longitude;
   private double latitude;

    public GeoPosition(double lon, double lat)
    {
        this.longitude = lon;
        this.latitude = lat;
    }

    public double getLongitude()
    {
        return longitude;
    }

    public void setLongitude(double longitude)
    {
        this.longitude = longitude;
    }

    public double getLatitude()
    {
        return latitude;
    }

    public void setLatitude(double latitude)
    {
        this.latitude = latitude;
    }
    
    
}
