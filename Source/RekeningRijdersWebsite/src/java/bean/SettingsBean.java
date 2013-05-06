package bean;

import administration.domain.Driver;
import java.io.Serializable;
import java.util.Date;
import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;
import service.RekeningrijdersService;

@Named
@SessionScoped
public class SettingsBean implements Serializable {

    @Inject
    private RekeningrijdersService service;
    
    private int bsn;
    private Driver driver;
    
    private String password;
    private String email;
    private String languageCode;
    private String firstName;
    private String lastName;
    private String residence;
    private String address;
    private String zipCode;
    private Date dateOfBirth;

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
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
    
    public int getBsn(){
        return bsn;
    }
    
    public void setBsn(int i){
        this.bsn = i;
        driver = service.getDriver(bsn);
    }
    
    public void editDriver(String s)
    {
        service.editDriver(driver);
    }
    
    public void submitSettings()
    {
        driver.setEmail(email);
        driver.setLanguageCode(languageCode);
        driver.setFirstName(firstName);
        driver.setLastName(lastName);
        driver.setResidence(residence);
        driver.setAddress(address);
        driver.setZipCode(zipCode);
        driver.setDateOfBirth(dateOfBirth);
        
        service.editDriver(driver);
    }
    
     public Boolean editPassword(String oldPass, String newPass){ 
        if(driver.getPassword().equals(oldPass))
        {
            driver.setPassword(newPass);
            service.editDriver(driver);
            return true;
        }
        
        return false;
    }
    
    
    
//    public void editEmail(String s){
//        
//        //check of de string een email is
//        driver.setEmail(s);
//        service.editDriver(driver);
//    }
//    
//   
//    
//    public void editLanguageCode(String s){
//        driver.setLanguageCode(s);
//        service.editDriver(driver);
//    }
//    
//    public void editFirstName(String s){
//        driver.setFirstName(s);
//        service.editDriver(driver);
//    }
//    
//    public void editLastName(String s){
//        driver.setLastName(s);
//        service.editDriver(driver);
//    }
//    
//    public void editResidence(String s){
//        driver.setResidence(s);
//        service.editDriver(driver);
//    }
//    
//    public void editAddress(String s){
//        driver.setAddress(s);
//        service.editDriver(driver);
//    }
//    
//    public void editZipCode(String s){
//        driver.setZipCode(s);
//        service.editDriver(driver);
//    }
//    
//    public void editDateOfBirth(Date date){
//        driver.setDateOfBirth(date);
//        service.editDriver(driver);
//    }  
}
