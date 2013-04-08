package administration.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import javax.persistence.*;

@Entity(name = "Employee")
@Table(name = "EMPLOYEE", schema = "ADMINISTRATION")
public class Employee implements Serializable {

    //<editor-fold defaultstate="collapsed" desc="Fields">
    @Id
    @Column(nullable = false)
    private String username;
    @Column(nullable = false)
    private String password;
    @ManyToMany(cascade = {CascadeType.PERSIST})
    @JoinTable(name = "EMPLOYEE_SECURITY_GROUP", schema = "ADMINISTRATION")
    private Collection<SecurityGroup> securityGroups;
    private Boolean privilege;
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="Getters & Setters">
    public String getName() {
        return username;
    }

    public void setName(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Collection<SecurityGroup> getSecurityGroups() {
        return securityGroups;
    }

    public void setSecurityGroups(Collection<SecurityGroup> securityGroups) {
        this.securityGroups = securityGroups;
    }

    public Boolean getPrivilege() {
        return privilege;
    }

    public void setPrivilege(Boolean privilege) {
        this.privilege = privilege;
    }
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="Constructors">
    public Employee() {
        this("", "", false);
    }

    public Employee(String username, String password, Boolean privilege) {
        this.username = username;
        this.password = password;
        this.privilege = privilege;

        securityGroups = new ArrayList<SecurityGroup>();
    }
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="Overrides">
    @Override
    public int hashCode() {
        return privilege.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        return obj instanceof Employee && hashCode() == obj.hashCode();
    }

    @Override
    public String toString() {
        return "Employee{" + "name=" + username + '}';
    }
    //</editor-fold>
}
