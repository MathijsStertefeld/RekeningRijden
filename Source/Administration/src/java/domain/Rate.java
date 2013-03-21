package domain;

import java.io.Serializable;
import javax.persistence.*;

@Entity
public abstract class Rate implements Serializable {

    //<editor-fold defaultstate="collapsed" desc="Fields">
    @Id
    private String name;
    private double price;
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="Getters & Setters">
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="Constructors">
    public Rate() {
        this("");
    }

    public Rate(String name) {
        this.name = name;
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
