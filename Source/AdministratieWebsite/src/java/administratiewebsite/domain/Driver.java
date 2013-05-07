package administratiewebsite.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;

@Entity(name = "Driver")
@Table(name = "DRIVER", schema = "ADMINISTRATION")
@XmlRootElement
public class Driver implements Serializable {

    //<editor-fold defaultstate="collapsed" desc="Fields">
    @Id
    @Column(nullable = false)
    private int bsn;
    @Column(nullable = false, unique = true)
    private String email;
    @Column(nullable = false)
    private String password;
    @ManyToMany(cascade = {CascadeType.PERSIST})
    @JoinTable(name = "DRIVER_SECURITY_GROUP", schema = "ADMINISTRATION")
    private Collection<SecurityGroup> securityGroups;
    private String languageCode;
    private String firstName;
    private String lastName;
    private String residence;
    private String address;
    private String zipCode;
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date dateOfBirth;
    @OneToMany(cascade={CascadeType.ALL})
    @JoinTable(name = "DRIVER_BILL", schema = "ADMINISTRATION")
    private Collection<Bill> bills;
    @OneToMany(cascade={CascadeType.ALL})
    @JoinTable(name = "DRIVER_CAR", schema = "ADMINISTRATION")
    private Collection<Car> cars;
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

    public Collection<SecurityGroup> getSecurityGroups() {
        return securityGroups;
    }

    public void setSecurityGroups(Collection<SecurityGroup> securityGroups) {
        this.securityGroups = securityGroups;
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

    public Collection<Bill> getBills() {
        return bills;
    }
    
    public Bill getBill(int id)
    {
        for(Bill b : bills)
        {
            if(b.getId() == id)
            {
                return b;
            }
        }
        
        return null;
    }

    public void setBills(Collection<Bill> bills) {
        this.bills = bills;
    }

    public Collection<Car> getCars() {
        return cars;
    }

    public void setCars(Collection<Car> cars) {
        this.cars = cars;
    }
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="Constructors">
    public Driver() {
        this(0, "", "", "", "", "", "", "", "", new Date());
    }

    public Driver(int bsn, String email, String Password,
            String languageCode, String firstName, String lastName,
            String residence, String address, String zipCode, Date dateOfBirth) {
        this.bsn = bsn;
        this.email = email;
        this.password = Password;
        this.languageCode = languageCode;
        this.firstName = firstName;
        this.lastName = lastName;
        this.residence = residence;
        this.address = address;
        this.zipCode = zipCode;
        this.dateOfBirth = dateOfBirth;

        securityGroups = new ArrayList<SecurityGroup>();
        bills = new ArrayList<Bill>();
        cars = new ArrayList<Car>();
    }
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="Overrides">
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
