package administratiewebsite.domain;

import java.io.Serializable;
import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;

@Entity(name = "SecurityGroup")
@Table(name = "SECURITY_GROUP", schema = "ADMINISTRATION")
@XmlRootElement
public class SecurityGroup implements Serializable {

    //<editor-fold defaultstate="collapsed" desc="Fields">
    @Id
    @Column(nullable = false)
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
    public SecurityGroup() {
        this("");
    }

    public SecurityGroup(String name) {
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
        return obj instanceof SecurityGroup && hashCode() == obj.hashCode();
    }

    @Override
    public String toString() {
        return "NewClass{" + "name=" + name + '}';
    }
    //</editor-fold>
}
