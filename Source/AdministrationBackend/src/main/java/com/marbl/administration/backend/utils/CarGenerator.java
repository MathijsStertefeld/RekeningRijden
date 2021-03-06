package com.marbl.administration.backend.utils;

//<editor-fold defaultstate="collapsed" desc="Imports">
import com.marbl.administration.domain.*;
//</editor-fold>

// This class is used to generate mock cars for testing.

public final class CarGenerator {

    //<editor-fold defaultstate="collapsed" desc="Fields">
    private int index;
    private Driver[] drivers;
    private String[] brands;
    private String[] models;
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="Constructors">
    public CarGenerator(Driver[] drivers, String[] brands, String[] models) {
        this.drivers = drivers;
        this.brands = brands;
        this.models = models;
        
        reset();
    }
    //</editor-fold>
    
    //<editor-fold defaultstate="collapsed" desc="Methods">
    public void reset() {
        index = -1;
    }

    public Car[] generate() {
        return generate(drivers.length);
    }

    public Car[] generate(int length) {
        Car[] cars = new Car[length];

        for (int i = 0; i < length; i++) {
            cars[i] = next();
        }

        return cars;
    }

    public Car next() {
        index++;

        String carTrackerId = "t" + index;
        String licensePlate = "AB-CD-0" + index % 10;
        CarType carType = CarType.values()[index % CarType.values().length];
        PaintColor paintColor = PaintColor.values()[index % PaintColor.values().length];
        int mass = 1000;
        Classification classification = Classification.values()[index % Classification.values().length];
        String brand = brands[index % brands.length];
        String model = models[index % models.length];
        int driverBSN = drivers[index % drivers.length].getBSN();

        return new Car(carTrackerId, licensePlate, carType, paintColor, mass, classification, brand, model, driverBSN);
    }
    //</editor-fold>
}
