package domain;

import java.io.Serializable;
import javax.persistence.*;

@Entity
public class Car implements Serializable {

    //<editor-fold defaultstate="collapsed" desc="Fields">
    @Id
    private String carTrackerId;
    private CarType type;
    private PaintColor paintColor;
    private int mass;
    private EnvironmentalClassification environmentalClassification;
    private int ownerBsn;
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="Getters & Setters">
    public String getCarTrackerId() {
        return carTrackerId;
    }

    public void setCarTrackerId(String carTrackerId) {
        this.carTrackerId = carTrackerId;
    }

    public int getOwnerBsn() {
        return ownerBsn;
    }

    public void setOwnerBsn(int ownerBsn) {
        this.ownerBsn = ownerBsn;
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

    public EnvironmentalClassification getEnvironmentalClassification() {
        return environmentalClassification;
    }

    public void setEnvironmentalClassification(EnvironmentalClassification environmentalClassification) {
        this.environmentalClassification = environmentalClassification;
    }
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="Constructors">
    public Car() {
        this("", CarType.PASSENGER_CAR, PaintColor.VARIOUS,
                0, EnvironmentalClassification.ZERO);
    }

    public Car(String carTrackerId, CarType type, PaintColor paintColor,
            int mass, EnvironmentalClassification environmentalClassification) {
        this.carTrackerId = carTrackerId;
        this.type = type;
        this.paintColor = paintColor;
        this.mass = mass;
        this.environmentalClassification = environmentalClassification;
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
