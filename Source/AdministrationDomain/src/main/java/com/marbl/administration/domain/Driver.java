package com.marbl.administration.domain;

//<editor-fold defaultstate="collapsed" desc="Imports">
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;
//</editor-fold>

@Entity
@XmlRootElement
public class Driver implements Serializable {

    //<editor-fold defaultstate="collapsed" desc="Fields">
    @Id
    private int bsn;
    private String email;
    private String password;
    private String firstName;
    private String lastName;
    private String residence;
    private String address;
    private String zipCode;
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date dateOfBirth;
    private boolean activated;
    @ElementCollection
    @CollectionTable(name = "DRIVER_GROUP", joinColumns = {
        @JoinColumn(name = "BSN")})
    @Column(name = "GROUPNAME")
    @Enumerated(EnumType.STRING)
    private Collection<DriverGroup> groups;
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="Getters & Setters">
    public int getBsn() {
        return bsn;
    }

    public void setBsn(int bsn) {
        this.bsn = bsn;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Collection<DriverGroup> getGroups() {
        return groups;
    }

    public void setGroups(Collection<DriverGroup> groups) {
        this.groups = groups;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getResidence() {
        return residence;
    }

    public void setResidence(String residence) {
        this.residence = residence;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getZipCode() {
        return zipCode;
    }

    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }

    public Date getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(Date dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public boolean getActivated() {
        return activated;
    }

    public void setActivated(boolean activated) {
        this.activated = activated;
    }
    
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="Constructors">
    public Driver() {
        this(0, "", "", "", "", "", "", "", new Date(), false, DriverGroup.ADMIN);
    }

    public Driver(int bsn, String email, String Password, String firstName,
            String lastName, String residence, String address, String zipCode,
            Date dateOfBirth, boolean activated, DriverGroup group) {
        this.bsn = bsn;
        this.email = email;
        this.password = Password;
        this.firstName = firstName;
        this.lastName = lastName;
        this.residence = residence;
        this.address = address;
        this.zipCode = zipCode;
        this.dateOfBirth = dateOfBirth;
        this.activated = activated;
        this.groups = new ArrayList<>();
        this.groups.add(group);
    }
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="Methods">
    @Override
    public int hashCode() {
        return bsn;
    }

    @Override
    public boolean equals(Object obj) {
        return obj instanceof Driver && hashCode() == obj.hashCode();
    }

    @Override
    public String toString() {
        return "Driver{" + "bsn=" + bsn + '}';
    }
    //</editor-fold>
}
