package domain;

import java.io.Serializable;
import javax.persistence.*;

@Entity
public class NewClass implements Serializable {

    //<editor-fold defaultstate="collapsed" desc="Fields">
    @Id
    private int id;
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="Getters & Setters">
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="Constructors">
    public NewClass() {
        this(0);
    }

    public NewClass(int id) {
        this.id = id;
    }
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="Overrides">
    @Override
    public int hashCode() {
        return id;
    }

    @Override
    public boolean equals(Object obj) {
        return obj instanceof NewClass && hashCode() == obj.hashCode();
    }

    @Override
    public String toString() {
        return "NewClass{" + "id=" + id + '}';
    }
    //</editor-fold>
}
