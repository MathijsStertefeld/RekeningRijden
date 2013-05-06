package bean;

import administration.domain.*;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.GenericType;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.api.client.config.ClientConfig;
import com.sun.jersey.api.client.config.DefaultClientConfig;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;
import javax.ws.rs.core.MediaType;

@Named
@SessionScoped
public class BillBean implements Serializable {

    WebResource service;
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
        bill = service.path("resources").path("bill").path(id.toString())
                .accept(MediaType.APPLICATION_JSON)
                .get(Bill.class);
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

    public void create() {
        service.path("resources").path("bill")
                .accept(MediaType.APPLICATION_JSON).post(bill);
    }

    public void edit() {
        service.path("resources").path("bill")
                .accept(MediaType.APPLICATION_JSON).put(bill);
    }

    @PostConstruct
    public void postConstruct() {
        ClientConfig config = new DefaultClientConfig();
        Client client = Client.create(config);
        service = client.resource("http://localhost:8080/RekeningRijdersWebsite/");

        bills = new ArrayList<Bill>(service.path("resources").path("bill")
                .accept(MediaType.APPLICATION_JSON)
                .get(new GenericType<Collection<Bill>>() {
        }));
    }
}
