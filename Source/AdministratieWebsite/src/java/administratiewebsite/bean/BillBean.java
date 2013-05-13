package administratiewebsite.bean;

import administration.domain.Bill;
import administration.domain.PaymentStatus;
import administratiewebsite.service.BillService;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;

@Named
@SessionScoped
public class BillBean implements Serializable {

    @Inject
    private BillService billService;
    private Bill bill;
    private Collection<Bill> bills;

    public Collection<Bill> getBills() {
        return bills;
    }
    
    public Long getId() {
        if (bill != null) {
            return bill.getId();
        }
        return 0L;
    }
    
    public void setId(Long id) {
        bill = billService.find(id);
    }
    
    public int getDriverBsn() {
        if (bill != null) {
            return bill.getDriverBsn();
        } else {
            return 0;
        }
    }
    
    public Date getPeriodDate() {
        if (bill != null) {
            return bill.getPeriodDate();
        } else {
            return new Date();
        }
    }
    
    public Date getPaymentDate() {
        if (bill != null) {
            return bill.getPaymentDate();
        } else {
            return new Date();
        }
    }
    
    public void setPaymentDate(Date paymentDate) {
        if (bill != null) {
            bill.setPaymentDate(paymentDate);
        }
    }
    
    public double getPaymentAmount() {
        if (bill != null) {
            return bill.getPaymentAmount();
        } else {
            return 0;
        }
    }
    
    public PaymentStatus getPaymentStatus() {
        if (bill != null) {
            return bill.getPaymentStatus();
        } else {
            return PaymentStatus.UNKNOWN;
        }
    }
    
    public void setPaymentStatus(PaymentStatus paymentStatus) {
        if (bill != null) {
            bill.setPaymentStatus(paymentStatus);
        }
    }
    
    public Collection<Object> getMovements() {
        ArrayList<Object> movements = new ArrayList<Object>();
        
        if (bill != null) {
            movements.addAll(bill.getMovements());
        }
        
        return movements;
    }

    @PostConstruct
    public void postConstruct() {
        bills = billService.findAll();
    }

    public void create() {
        billService.create(bill);
        bills = billService.findAll();
    }

    public void edit() {
        billService.edit(bill);
        bills = billService.findAll();
    }
}
