package domain;

import javax.persistence.*;

@Entity
public class CityRate extends Rate {

    //<editor-fold defaultstate="collapsed" desc="Constructors">
    public CityRate() {
        super();
    }

    public CityRate(String name) {
        super(name);
    }
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="Overrides">
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
