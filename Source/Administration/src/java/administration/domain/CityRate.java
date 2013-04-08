package administration.domain;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;

@Entity(name = "CityRate")
@Table(name = "CITY_RATE", schema = "ADMINISTRATION")
@XmlRootElement
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
