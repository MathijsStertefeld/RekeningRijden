package com.marbl.administration.domain;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;

@Entity
@XmlRootElement
public class VehicleRate extends Rate {

    //<editor-fold defaultstate="collapsed" desc="Constructors">
    public VehicleRate() {
        this("", 0);
    }

    public VehicleRate(String name, double price) {
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
        return obj instanceof VehicleRate && hashCode() == obj.hashCode();
    }

    @Override
    public String toString() {
        return "VehicleRate{" + "name=" + super.getName() + '}';
    }
    //</editor-fold>
}
