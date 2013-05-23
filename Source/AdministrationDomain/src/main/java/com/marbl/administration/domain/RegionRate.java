package com.marbl.administration.domain;

//<editor-fold defaultstate="collapsed" desc="Imports">
import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;
//</editor-fold>

@Entity
@XmlRootElement
public class RegionRate extends Rate {

    //<editor-fold defaultstate="collapsed" desc="Constructors">
    public RegionRate() {
        this("", 0);
    }

    public RegionRate(String name, double price) {
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
        return obj instanceof RegionRate && hashCode() == obj.hashCode();
    }

    @Override
    public String toString() {
        return "RegionRate{" + "name=" + super.getName() + '}';
    }
    //</editor-fold>
}
