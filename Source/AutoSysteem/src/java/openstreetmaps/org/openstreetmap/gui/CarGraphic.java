/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package openstreetmaps.org.openstreetmap.gui;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import openstreetmaps.org.openstreetmap.gui.jmapviewer.interfaces.MapMarker;

/**
 *
 * @author Eagle
 */
public class CarGraphic implements MapMarker
{

    double lat;
    double lon;
    Color color;
    String carTrackerId;
    boolean highlighted = false;

    public CarGraphic(String carTrackerId)
    {
        this(Color.RED, 51.4560332, 5.5380103, carTrackerId);
    }

    public CarGraphic(double lat, double lon, String carTrackerId)
    {
        this(Color.RED, lat, lon, carTrackerId);
    }

    public CarGraphic(Color color, double lat, double lon, String carTrackerId)
    {
        super();
        this.color = color;
        this.lat = lat;
        this.lon = lon;
        this.carTrackerId = carTrackerId;
    }

    @Override
    public double getLat()
    {
        return lat;
    }

    @Override
    public double getLon()
    {
        return lon;
    }

    public String getCarTrackerId()
    {
        return carTrackerId;
    }

    @Override
    public void paint(Graphics g, Point position)
    {
        int size_h = 5;
        int size = size_h * 2;
        g.setColor(color);
        g.fillOval(position.x - size_h, position.y - size_h, size, size);
        g.setColor(Color.BLACK);
        g.drawOval(position.x - size_h, position.y - size_h, size, size);
    }

    @Override
    public String toString()
    {
        return "Car is at " + lat + " " + lon;
    }

    public void highlight(Color color)
    {
        this.color = color;
    }

    public void move(double lat, double lon)
    {
        this.lat += lat;
        this.lon += lon;
    }
}
