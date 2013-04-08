package administration.domain;

import java.sql.Time;
import javax.persistence.*;

@Entity(name = "HighwayRate")
@Table(name = "HIGHWAY_RATE", schema = "ADMINISTRATION")
public class HighwayRate extends Rate {

    //<editor-fold defaultstate="collapsed" desc="Fields">
    private double rushPrice;
    private Time rushBegin;
    private Time rushEnd;
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="Getters & Setters">
    public double getRushPrice() {
        return rushPrice;
    }

    public void setRushPrice(double rushPrice) {
        this.rushPrice = rushPrice;
    }

    public Time getRushBegin() {
        return rushBegin;
    }

    public void setRushBegin(Time rushBegin) {
        this.rushBegin = rushBegin;
    }

    public Time getRushEnd() {
        return rushEnd;
    }

    public void setRushEnd(Time rushEnd) {
        this.rushEnd = rushEnd;
    }
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="Constructors">
    public HighwayRate() {
        super();
    }

    public HighwayRate(String name) {
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
        return obj instanceof HighwayRate && hashCode() == obj.hashCode();
    }

    @Override
    public String toString() {
        return "HighwayRate{" + "name=" + super.getName() + '}';
    }
    //</editor-fold>
}
