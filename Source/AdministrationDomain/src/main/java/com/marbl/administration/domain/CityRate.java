package com.marbl.administration.domain;

//<editor-fold defaultstate="collapsed" desc="Imports">
import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;
//</editor-fold>

@Entity
@XmlRootElement
public class CityRate extends Rate {

    //<editor-fold defaultstate="collapsed" desc="Constructors">
    public CityRate() {
        this("", 0);
    }

    public CityRate(String name, double price) {
        super(name, price);
    }
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="Methods">
    @Override
    public int hashCode() {
        return super.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        return obj instanceof CityRate && hashCode() == obj.hashCode();
    }

    @Override
    public String toString() {
        return "CityRate{" + "name=" + super.getName() + '}';
    }
    //</editor-fold>
}
