package com.marbl.administration.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;

@Entity
@XmlRootElement
public class Bill implements Serializable {

    //<editor-fold defaultstate="collapsed" desc="Fields">
    @Id
    @Column(nullable = false)
    private Long id;
    @Column(nullable = false)
    private String carLicensePlate;
    private int driverBsn;
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date periodDate;
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date paymentDate;
    private double paymentAmount;
    private PaymentStatus paymentStatus;
    private Collection<Object> movements;
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="Getters & Setters">
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
    
    public String getCarLicensePlate() {
        return carLicensePlate;
    }
    
    public void setCarLicensePlate(String carLicensePlate) {
        this.carLicensePlate = carLicensePlate;
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
        this(0L, new Date(), new Date(), 0, PaymentStatus.UNKNOWN);
    }

    public Bill(Long id, Date periodDate, Date paymentDate, double paymentAmount, PaymentStatus paymentStatus) {
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
    
    public void pay() {
        this.paymentStatus = PaymentStatus.PAID;
        this.paymentDate = new Date();
    }
}
