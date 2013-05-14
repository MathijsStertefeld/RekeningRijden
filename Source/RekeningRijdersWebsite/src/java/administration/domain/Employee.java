package administration.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;

@Entity(name = "Employee")
@Table(name = "EMPLOYEE", schema = "ADMINISTRATION")
@XmlRootElement
public class Employee implements Serializable {

    //<editor-fold defaultstate="collapsed" desc="Fields">
    @Id
    @Column(nullable = false)
    private String username;
    @Column(nullable = false)
    private String password;
    @ElementCollection(targetClass = GroupName.class)
    @CollectionTable(name = "EMPLOYEE_GROUP", schema = "ADMINISTRATION", joinColumns = {
        @JoinColumn(name = "USERNAME", referencedColumnName= "USERNAME")})
    @Column(name = "GROUPNAME")
    @Enumerated(EnumType.STRING)
    private Collection<GroupName> groupNames;
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

    public Collection<GroupName> getGroups() {
        return groupNames;
    }

    public void setGroupNames(Collection<GroupName> groupNames) {
        this.groupNames = groupNames;
    }
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="Constructors">
    public Employee() {
        this("", "");
    }

    public Employee(String username, String password) {
        this.username = username;
        this.password = password;

        groupNames = new ArrayList<GroupName>();
    }
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="Overrides">
    @Override
    public int hashCode() {
        return username.hashCode();
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
