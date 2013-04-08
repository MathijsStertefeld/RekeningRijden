package domain;

import java.io.Serializable;
import javax.persistence.*;

@Entity(name="GROUPS")
public class Group implements Serializable {

    //<editor-fold defaultstate="collapsed" desc="Fields">
    @Id
    private String name;
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
    public Group() {
        this("");
    }

    public Group(String name) {
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
        return obj instanceof Group && hashCode() == obj.hashCode();
    }

    @Override
    public String toString() {
        return "NewClass{" + "name=" + name + '}';
    }
    //</editor-fold>
}
