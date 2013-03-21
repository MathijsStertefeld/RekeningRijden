package domain;

import java.io.Serializable;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.*;

@Entity
public class WebsiteAccount implements Serializable {

    //<editor-fold defaultstate="collapsed" desc="Fields">
    @Id
    private int bsn;
    private String email;
    private String hashedPassword;
    private String languageCode;
    private String firstName;
    private String lastName;
    private String residence;
    private String address;
    private String zipCode;
    private Date dateOfBirth;
    @OneToMany
    private List<Bill> bills;
    @OneToMany
    private List<Car> cars;
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

    public String getHashedPassword() {
        return hashedPassword;
    }

    public void setHashedPassword(String hashedPassword) {
        this.hashedPassword = hashedPassword;
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
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="Constructors">
    public WebsiteAccount() {
        this(0, "", "", "", "", "", "", "", "", new Date(0));
    }

    public WebsiteAccount(int bsn, String email, String hashedPassword,
            String languageCode, String firstName, String lastName,
            String residence, String address, String zipCode, Date dateOfBirth) {
        this.bsn = bsn;
        this.email = email;
        this.hashedPassword = hashedPassword;
        this.languageCode = languageCode;
        this.firstName = firstName;
        this.lastName = lastName;
        this.residence = residence;
        this.address = address;
        this.zipCode = zipCode;
        this.dateOfBirth = dateOfBirth;

        bills = new ArrayList<Bill>();
        cars = new ArrayList<Car>();
    }
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="Methods">
    public void addBill(Bill bill) {
        bill.setOwnerBsn(bsn);
        bills.add(bill);
    }

    public void addCar(Car car) {
        car.setOwnerBsn(bsn);
        cars.add(car);
    }
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="Overrides">
    @Override
    public int hashCode() {
        return bsn;
    }

    @Override
    public boolean equals(Object obj) {
        return obj instanceof WebsiteAccount && hashCode() == obj.hashCode();
    }

    @Override
    public String toString() {
        return "WebsiteAccount{" + "bsn=" + bsn + '}';
    }
    //</editor-fold>
}
