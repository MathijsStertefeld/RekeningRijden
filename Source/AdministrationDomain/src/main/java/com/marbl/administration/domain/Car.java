package com.marbl.administration.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;

@Entity
@XmlRootElement
public class Car implements Serializable {

    //<editor-fold defaultstate="collapsed" desc="Fields">
    @Id
    private String licensePlate;
    private String carTrackerId;
    private int driverBsn;
    @ElementCollection
    @CollectionTable(name = "CAR_DRIVER_HISTORY")
    private Collection<Integer> driverHistory;
    private String brand;
    private String model;
    private CarType type;
    private PaintColor paintColor;
    private int mass;
    private Classification classification;
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="Getters & Setters">
    public String getLicensePlate() {
        return licensePlate;
    }

    public void setLicensePlate(String licensePlate) {
        this.licensePlate = licensePlate;
    }

    public String getCarTrackerId() {
        return carTrackerId;
    }

    public void setCarTrackerId(String carTrackerId) {
        this.carTrackerId = carTrackerId;
    }

    public int getDriverBsn() {
        return driverBsn;
    }

    public void setDriverBsn(int driverBsn) {
        this.driverBsn = driverBsn;
    }
    
    public Collection<Integer> getDriverHistory() {
        return driverHistory;
    }
    
    public void setDriverHistory(Collection<Integer> driverHistory) {
        this.driverHistory = driverHistory;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public CarType getType() {
        return type;
    }

    public void setType(CarType type) {
        this.type = type;
    }

    public PaintColor getPaintColor() {
        return paintColor;
    }

    public void setPaintColor(PaintColor paintColor) {
        this.paintColor = paintColor;
    }

    public int getMass() {
        return mass;
    }

    public void setMass(int mass) {
        this.mass = mass;
    }

    public Classification getClassification() {
        return classification;
    }

    public void setClassification(Classification classification) {
        this.classification = classification;
    }
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="Constructors">
    public Car() {
        this("", "", CarType.UNKNOWN, PaintColor.UNKNOWN, 0,
                Classification.UNKNOWN, "", "");
    }

    public Car(String licensePlate, String carTrackerId, CarType type, PaintColor paintColor, int mass,
            Classification classification, String brand, String model) {
        this.licensePlate = licensePlate;
        this.carTrackerId = carTrackerId;
        this.type = type;
        this.paintColor = paintColor;
        this.mass = mass;
        this.classification = classification;
        this.brand = brand;
        this.model = model;
        
        driverHistory = new ArrayList<Integer>();
    }
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="Overrides">
    @Override
    public int hashCode() {
        return licensePlate.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        return obj instanceof Car && hashCode() == obj.hashCode();
    }

    @Override
    public String toString() {
        return "Car{" + "licensePlate=" + licensePlate + '}';
    }
    //</editor-fold>
}
