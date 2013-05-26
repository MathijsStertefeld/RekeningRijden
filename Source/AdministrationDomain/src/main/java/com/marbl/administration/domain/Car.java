package com.marbl.administration.domain;

//<editor-fold defaultstate="collapsed" desc="Imports">
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;
//</editor-fold>

@Entity
@XmlRootElement
public class Car implements Serializable {

    //<editor-fold defaultstate="collapsed" desc="Fields">
    @Id
    private String carTrackerId;
    private String licensePlate;
    private String brand;
    private String model;
    private CarType type;
    private PaintColor paintColor;
    private int mass;
    private Classification classification;
    private int driverBSN;
    @ElementCollection
    @CollectionTable(name = "CAR_DRIVER")
    private List<Integer> drivers;
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="Getters & Setters">
    public String getCarTrackerId() {
        return carTrackerId;
    }

    public void setCarTrackerId(String carTrackerId) {
        this.carTrackerId = carTrackerId;
    }

    public String getLicensePlate() {
        return licensePlate;
    }

    public void setLicensePlate(String licensePlate) {
        this.licensePlate = licensePlate;
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

    public int getDriverBSN() {
        return driverBSN;
    }

    public void setDriverBSN(int driverBSN) {
        this.driverBSN = driverBSN;
    }
    
    public List<Integer> getDriverHistory() {
        return drivers;
    }
    
    public void setDriverHistory(List<Integer> driverHistory) {
        this.drivers = driverHistory;
    }
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="Constructors">
    public Car() {
        this("", "", CarType.UNKNOWN, PaintColor.UNKNOWN,
                0, Classification.UNKNOWN, "", "", 0);
    }

    public Car(String carTrackerId, String licensePlate, CarType type, PaintColor paintColor,
            int mass, Classification classification, String brand, String model, int driverBSN) {
        this.carTrackerId = carTrackerId;
        this.licensePlate = licensePlate;
        this.type = type;
        this.paintColor = paintColor;
        this.mass = mass;
        this.classification = classification;
        this.brand = brand;
        this.model = model;
        this.driverBSN = driverBSN;
        this.drivers = new ArrayList();
    }
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="Methods">
    @Override
    public int hashCode() {
        return carTrackerId.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        return obj instanceof Car && hashCode() == obj.hashCode();
    }

    @Override
    public String toString() {
        return "Car{" + "carTrackerId=" + carTrackerId + '}';
    }
    //</editor-fold>
}
