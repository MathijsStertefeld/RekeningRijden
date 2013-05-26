/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package domain;

import java.util.ArrayList;
import org.openstreetmap.osmosis.core.domain.v0_6.Node;

/**
 *
 * @author Leslie Aerts
 */
public class CarHolder
{

    private static ArrayList<Vehicle> cars = new ArrayList<Vehicle>();

    public CarHolder()
    {
        cars = new ArrayList<Vehicle>();
    }

    public static ArrayList<Vehicle> getCars()
    {
        return cars;
    }

    public void setCars(ArrayList<Vehicle> cars)
    {
        this.cars = cars;
    }

    public void addCar(Vehicle c, ArrayList<Node> route)
    {
        c.setRoute(route);
        cars.add(c);
    }
}
