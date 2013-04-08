package administration.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;

@Entity(name = "Bill")
@Table(name = "BILL", schema = "ADMINISTRATION")
@XmlRootElement
public class Bill implements Serializable {

    //<editor-fold defaultstate="collapsed" desc="Fields">
    @Id
    @Column(nullable = false)
    private int id;
    @Column(nullable = false)
    private int driverBsn;
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date periodDate;
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date paymentDate;
    private double paymentAmount;
    private PaymentStatus paymentStatus;
    //@OneToMany(cascade = {CascadeType.ALL})
    //@JoinTable(name = "BILL_MOVEMENT", schema = "ADMINISTRATION")
    private List<Object> movements;
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="Getters & Setters">
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getOwnerBsn() {
        return driverBsn;
    }

    public void setOwnerBsn(int driverBsn) {
        this.driverBsn = driverBsn;
    }

    public Date getPeriodDate() {
        return periodDate;
    }

    public void setPeriodDate(Date periodDate) {
        this.periodDate = periodDate;
    }

    public Date getPaymentDate() {
        return paymentDate;
    }

    public void setPaymentDate(Date paymentDate) {
        this.paymentDate = paymentDate;
    }

    public double getPaymentAmount() {
        return paymentAmount;
    }

    public void setPaymentAmount(double paymentAmount) {
        this.paymentAmount = paymentAmount;
    }

    public PaymentStatus getPaymentStatus() {
        return paymentStatus;
    }

    public void setPaymentStatus(PaymentStatus paymentStatus) {
        this.paymentStatus = paymentStatus;
    }

    public List<Object> getMovements() {
        return movements;
    }

    public void setMovements(List<Object> movements) {
        this.movements = movements;
    }
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="Constructors">
    public Bill() {
        this(0, new Date(), new Date(), 0, PaymentStatus.OPEN);
    }

    public Bill(int id, Date periodDate, Date paymentDate, double paymentAmount, PaymentStatus paymentStatus) {
        this.id = id;
        this.periodDate = periodDate;
        this.paymentDate = paymentDate;
        this.paymentAmount = paymentAmount;
        this.paymentStatus = paymentStatus;

        movements = new ArrayList<Object>();
    }
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="Overrides">
    @Override
    public int hashCode() {
        return id;
    }

    @Override
    public boolean equals(Object obj) {
        return obj instanceof Bill && hashCode() == obj.hashCode();
    }

    @Override
    public String toString() {
        return "Bill{" + "number=" + id + '}';
    }
    //</editor-fold>
}
