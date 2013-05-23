package com.marbl.administration.domain;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;

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

    //<editor-fold defaultstate="collapsed" desc="Overrides">
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
