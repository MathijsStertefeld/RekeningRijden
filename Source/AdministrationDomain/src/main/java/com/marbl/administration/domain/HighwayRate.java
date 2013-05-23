package com.marbl.administration.domain;

//<editor-fold defaultstate="collapsed" desc="Imports">
import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;
//</editor-fold>

@Entity
@XmlRootElement
public class HighwayRate extends Rate {

    //<editor-fold defaultstate="collapsed" desc="Fields">
    private double[] prices;
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="Getters & Setters">
    public double[] getPrices() {
        return prices;
    }

    public void setPrices(double[] prices) {
        this.prices = prices;
    }
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="Constructors">
    public HighwayRate() {
        this("", new double[24]);
    }

    public HighwayRate(String name, double[] prices) {
        super(name, 0);
        this.prices = prices;
    }
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="Methods">
    @Override
    public int hashCode() {
        return super.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        return obj instanceof HighwayRate && hashCode() == obj.hashCode();
    }

    @Override
    public String toString() {
        return "HighwayRate{" + "name=" + super.getName() + '}';
    }
    //</editor-fold>
}
