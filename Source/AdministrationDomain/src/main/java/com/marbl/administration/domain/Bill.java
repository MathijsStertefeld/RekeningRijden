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
public class Bill implements Serializable {

    //<editor-fold defaultstate="collapsed" desc="Fields">
    @Id
    private Long id;
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date periodDate;
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date paymentDate;
    private double paymentAmount;
    private PaymentStatus paymentStatus;
    private int driverBsn;
    private String carTrackerId;
    private Collection<Object> movements;
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="Getters & Setters">
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
    
    public String getCarTrackerId() {
        return carTrackerId;
    }
    
    public void setCarTrackerId(String carTrackerId) {
        this.carTrackerId = carTrackerId;
    }

    public int getDriverBsn() {
        return driverBsn;
    }

    public void setDriverBsn(int driverBsn) {
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

    public Collection<Object> getMovements() {
        return movements;
    }

    public void setMovements(Collection<Object> movements) {
        this.movements = movements;
    }
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="Constructors">
    public Bill() {
        this(0L, new Date(), new Date(), 0, PaymentStatus.UNKNOWN, 0, "");
    }

    public Bill(Long id, Date periodDate, Date paymentDate, double paymentAmount,
            PaymentStatus paymentStatus, int driverBsn, String carTrackerId) {
        this.id = id;
        this.periodDate = periodDate;
        this.paymentDate = paymentDate;
        this.paymentAmount = paymentAmount;
        this.paymentStatus = paymentStatus;
        this.driverBsn = driverBsn;
        this.carTrackerId = carTrackerId;
        this.movements = new ArrayList<>();
    }
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="Methods">
    @Override
    public int hashCode() {
        return id.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        return obj instanceof Bill && hashCode() == obj.hashCode();
    }

    @Override
    public String toString() {
        return "Bill{" + "id=" + id + '}';
    }
    //</editor-fold>
}
