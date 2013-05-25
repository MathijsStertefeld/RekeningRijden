package com.marbl.administration.domain;

//<editor-fold defaultstate="collapsed" desc="Imports">
import java.io.Serializable;
import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;
//</editor-fold>

@Entity
@XmlRootElement
public class Rate implements Serializable {

    //<editor-fold defaultstate="collapsed" desc="Fields">
    @Id
    private String name;
    private Type type;
    private double price;
    private double[] prices;
    
    public enum Type { REGION, HIGHWAY, CITY, VEHICLE, MASS}
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="Getters & Setters">
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    
    public Type getRateType()
    {
        return type;
    }
    
    public void setRateType(Type type) {
        this.type = type;
    }
    
    public double getPrice() {
        return price;
    }
    
    public void setPrice(double price) {
        this.price = price;
    }
    
    public double[] getPrices() {
        return prices;
    }
    
    public void setPrices(double[] prices) {
        this.prices = prices;
    }
    
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="Constructors">
    public Rate() {
        this("", Type.REGION, 0);
    }

    public Rate(String name, Type type, double price) {
        this.name = name;
        this.type = type;
        this.price = price;
    }
    
        public Rate(String name, Type type, double[] prices) {
        this.name = name;
        this.type = type;
        this.prices = prices;
    }
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="Methods">
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
