/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package domain_simulation;

import java.util.ArrayList;
import org.openstreetmap.osmosis.core.domain.v0_6.Node;

/**
 *
 * @author Leslie Aerts
 */
public class CarHolder
{

    private static ArrayList<Car> cars = new ArrayList<Car>();

    public CarHolder()
    {
        cars = new ArrayList<Car>();
    }

    public static ArrayList<Car> getCars()
    {
        return cars;
    }

    public void setCars(ArrayList<Car> cars)
    {
        this.cars = cars;
    }

    public void addCar(Car c, ArrayList<Node> route)
    {
        c.setRoute(route);
        cars.add(c);
    }
}
