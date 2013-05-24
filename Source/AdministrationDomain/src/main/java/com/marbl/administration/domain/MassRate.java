package com.marbl.administration.domain;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;

@Entity
@XmlRootElement
public class MassRate extends Rate {

    //<editor-fold defaultstate="collapsed" desc="Constructors">
    public MassRate() {
        this("", 0);
    }

    public MassRate(String name, double price) {
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
        return obj instanceof MassRate && hashCode() == obj.hashCode();
    }

    @Override
    public String toString() {
        return "MassRate{" + "name=" + super.getName() + '}';
    }
    //</editor-fold>
}
