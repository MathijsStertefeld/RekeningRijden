package administration.domain;

import java.io.Serializable;
import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;

@Entity(name = "Car")
@Table(name = "CAR", schema = "ADMINISTRATION")
@XmlRootElement
public class Car implements Serializable {

    //<editor-fold defaultstate="collapsed" desc="Fields">
    @Id
    @Column(nullable = false)
    private String carTrackerId;
    @Column(nullable = false)
    private int driverBsn;
    private CarType type;
    private PaintColor paintColor;
    private int mass;
    private Classification classification;
    private String licencePlate;
    private String brand;
    private String model;
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="Getters & Setters">
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

    public String getLicencePlate() {
        return licencePlate;
    }

    public void setLicencePlate(String licencePlate) {
        this.licencePlate = licencePlate;
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
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="Constructors">
    public Car() {
        this("", CarType.PASSENGER_CAR, PaintColor.VARIOUS, 0,
                Classification.ZERO, "", "", "");
    }

    public Car(String carTrackerId, CarType type, PaintColor paintColor, int mass,
            Classification classification, String licencePlate, String brand, String model) {
        this.carTrackerId = carTrackerId;
        this.type = type;
        this.paintColor = paintColor;
        this.mass = mass;
        this.classification = classification;
        this.licencePlate = licencePlate;
        this.brand = brand;
        this.model = model;
    }
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="Overrides">
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
