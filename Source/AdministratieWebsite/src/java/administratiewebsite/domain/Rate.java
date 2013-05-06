package administratiewebsite.domain;

import java.io.Serializable;
import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;

@Entity(name = "Rate")
@Table(name = "RATE", schema = "ADMINISTRATION")
@XmlRootElement
public abstract class Rate implements Serializable {

    //<editor-fold defaultstate="collapsed" desc="Fields">
    @Id
    @Column(nullable = false)
    private String name;
    @Column(nullable = false)
    private double price;
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="Getters & Setters">
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    
    public double getPrice() {
        return price;
    }
    
    public void setPrice(double price) {
        this.price = price;
    }
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="Constructors">
    public Rate() {
        this("", 0);
    }

    public Rate(String name, double price) {
        this.name = name;
        this.price = price;
    }
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="Overrides">
    @Override
    public int hashCode() {
        return name.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        return obj instanceof Rate && hashCode() == obj.hashCode();
    }

    @Override
    public String toString() {
        return "Rate{" + "name=" + name + '}';
    }
    //</editor-fold>
}
