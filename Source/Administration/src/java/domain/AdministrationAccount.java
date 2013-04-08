package domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import javax.persistence.*;

@Entity
public class AdministrationAccount implements Serializable {

    //<editor-fold defaultstate="collapsed" desc="Fields">
    @Id
    private String name;
    private String password;
    @ManyToMany(cascade = {CascadeType.ALL})
    private Collection<Group> groups;
    private Boolean privilege;
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="Getters & Setters">
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Collection<Group> getGroups() {
        return groups;
    }

    public void setGroups(Collection<Group> groups) {
        this.groups = groups;
    }

    public Boolean getPrivilege() {
        return privilege;
    }

    public void setPrivilege(Boolean privilege) {
        this.privilege = privilege;
    }
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="Constructors">
    public AdministrationAccount() {
        this("", "", false);
    }

    public AdministrationAccount(String username, String password, Boolean privilege) {
        this.name = username;
        this.password = password;
        this.privilege = privilege;

        groups = new ArrayList<Group>();
    }
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="Overrides">
    @Override
    public int hashCode() {
        return privilege.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        return obj instanceof AdministrationAccount && hashCode() == obj.hashCode();
    }

    @Override
    public String toString() {
        return "AdministrationAccount{" + "username=" + name + '}';
    }
    //</editor-fold>
}
