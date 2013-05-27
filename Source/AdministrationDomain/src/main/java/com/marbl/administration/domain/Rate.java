package com.marbl.administration.domain;

//<editor-fold defaultstate="collapsed" desc="Imports">
import java.io.Serializable;
import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;
//</editor-fold>

// This class represents a rate that is used to calculate a bills total price.

@Entity
@XmlRootElement
public class Rate implements Serializable {

    //<editor-fold defaultstate="collapsed" desc="Fields">
    @Id
    private String name;
    private RateType type;
    private Double price;
    private Double rushHourPrice;
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="Getters & Setters">
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public RateType getType() {
        return type;
    }

    public void setType(RateType type) {
        this.type = type;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Double getRushHourPrice() {
        return rushHourPrice;
    }

    public void setRushHourPrice(Double rushHourPrice) {
        this.rushHourPrice = rushHourPrice;
    }
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="Constructors">
    public Rate() {
        this(null, null, null);
    }

    public Rate(String name, RateType type, Double price) {
        this(name, type, price, 0D);
    }

    public Rate(String name, RateType type, Double price, Double rushHourPrice) {
        this.name = name;
        this.type = type;
        this.price = price;
        this.rushHourPrice = rushHourPrice;
    }
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="Methods">
    public boolean notHighway() {
        return (type != RateType.HIGHWAY);
    }

    @Override
    public int hashCode() {
        return name.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        return obj instanceof Rate && hashCode() == obj.hashCode();
    }

    @Override
    public String toString() {
        return "Rate{" + "name=" + name + '}';
    }
    //</editor-fold>
}
