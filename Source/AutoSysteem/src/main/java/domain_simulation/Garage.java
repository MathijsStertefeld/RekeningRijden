/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package domain_simulation;

import domain_simulation.Car;
import java.util.ArrayList;

/**
 *
 * @author Leslie Aerts
 */
public class Garage
{

    private static ArrayList<Car> cars = new ArrayList<Car>();

    public Garage()
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

    public void addCar(Car c)
    {
        cars.add(c);
    }
}
