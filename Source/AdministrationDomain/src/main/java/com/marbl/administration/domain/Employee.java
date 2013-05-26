package com.marbl.administration.domain;

//<editor-fold defaultstate="collapsed" desc="Imports">
import java.io.Serializable;
import java.util.ArrayList;
import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;
//</editor-fold>

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
    private ArrayList<EmployeeGroup> groups;
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

    public ArrayList<EmployeeGroup> getGroups() {
        return groups;
    }

    public void setGroups(ArrayList<EmployeeGroup> groups) {
        this.groups = groups;
    }
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="Constructors">
    public Employee() {
        this("", "");
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
