package domain;

import java.io.Serializable;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.*;

@Entity
public class Bill implements Serializable {

    //<editor-fold defaultstate="collapsed" desc="Fields">
    @Id
    private int number;
    private Date paymentDate;
    private double paymentAmount;
    private PaymentStatus paymentStatus;
    private int ownerBsn;
    private List<Object> movements;
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="Getters & Setters">
    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
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

    public int getOwnerBsn() {
        return ownerBsn;
    }

    public void setOwnerBsn(int ownerBsn) {
        this.ownerBsn = ownerBsn;
    }
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="Constructors">
    public Bill() {
        this(0, new Date(0), 0, PaymentStatus.OPEN);
    }
    
    public Bill(int number, Date paymentDate, double paymentAmount, PaymentStatus paymentStatus) {
        this.number = number;
        this.paymentDate = paymentDate;
        this.paymentAmount = paymentAmount;
        this.paymentStatus = paymentStatus;
        
        movements = new ArrayList<Object>();
    }
    //</editor-fold>
    
    //<editor-fold defaultstate="collapsed" desc="Methods">
    public void addMovement(Object movement) {
        movements.add(movement);
    }
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="Overrides">
    @Override
    public int hashCode() {
        return number;
    }

    @Override
    public boolean equals(Object obj) {
        return obj instanceof Bill && hashCode() == obj.hashCode();
    }

    @Override
    public String toString() {
        return "Bill{" + "number=" + number + '}';
    }
    //</editor-fold>
}
