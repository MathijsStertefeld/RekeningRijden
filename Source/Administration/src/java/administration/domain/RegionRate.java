package administration.domain;

import javax.persistence.*;

@Entity(name = "RegionRate")
@Table(name = "REGION_RATE", schema = "ADMINISTRATION")
public class RegionRate extends Rate {

    //<editor-fold defaultstate="collapsed" desc="Constructors">
    public RegionRate() {
        super();
    }

    public RegionRate(String name) {
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
        return obj instanceof RegionRate && hashCode() == obj.hashCode();
    }

    @Override
    public String toString() {
        return "RegionRate{" + "name=" + super.getName() + '}';
    }
    //</editor-fold>
}
