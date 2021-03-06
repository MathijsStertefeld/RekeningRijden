package com.marbl.administration.domain;

//<editor-fold defaultstate="collapsed" desc="Imports">
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;
//</editor-fold>

// This class represents a employee and is used for authentication.

@Entity
@XmlRootElement
public class Employee implements Serializable {

    //<editor-fold defaultstate="collapsed" desc="Fields">
    @Id
    private String username;
    private String password;
    @ElementCollection
    @CollectionTable(name = "EMPLOYEE_GROUP", joinColumns = {
        @JoinColumn(name = "USERNAME")})
    @Column(name = "GROUPNAME")
    @Enumerated(EnumType.STRING)
    private List<EmployeeGroup> groups;
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="Getters & Setters">
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public List<EmployeeGroup> getGroups() {
        return groups;
    }

    public void setGroups(List<EmployeeGroup> groups) {
        this.groups = groups;
    }
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="Constructors">
    public Employee() {
        this(null, null);
    }

    public Employee(String username, String password) {
        this.username = username;
        this.password = password;
        this.groups = new ArrayList();
    }
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="Methods">
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
        return "Employee{" + "username=" + username + '}';
    }
    //</editor-fold>
}
