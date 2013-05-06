package administratiewebsite.domain;

import java.sql.Time;
import java.util.Collection;
import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;

@Entity(name = "HighwayRate")
@Table(name = "HIGHWAY_RATE", schema = "ADMINISTRATION")
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