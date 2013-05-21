/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package bean;

import administration.domain.Driver;
import java.io.Serializable;
import java.util.Date;
import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;
import service.RekeningRijdersService;

/**
 *
 * @author Bas
 */
@Named
@SessionScoped
public class RegisterBean implements Serializable{
    
    @Inject
    RekeningRijdersService service;
    
    private int bsn;
    private String email;
    private String password;
    private String languageCode;
    private String firstName;
    private String lastName;
    private String residence;
    private String address;
    private String zipCode;
    private Date dateOfBirth;

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

    public String getLanguageCode() {
        return languageCode;
    }

    public void setLanguageCode(String languageCode) {
        this.languageCode = languageCode;
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
    
    public void register(){
        Driver newDriver = new Driver(bsn, email, password, languageCode, firstName, lastName, residence, address, zipCode, dateOfBirth);
        service.register(newDriver);
    }
}
