package com.marbl.rekeningrijders.bean;

import com.marbl.administration.domain.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.http.HttpServletRequest;
import com.marbl.rekeningrijders.service.RekeningRijdersService;

@Named
@SessionScoped
public class BillBean implements Serializable {

    @Inject
    RekeningRijdersService service;
    private Bill bill;
    private Collection<Bill> bills;
    
    public Long getBillId() {
        if (bill != null) {
            return bill.getId();
        } else {
            return 0L;
        }
    }
    
    public void setBillId(Long id) {
        for (Bill other : bills) {
            if (id == other.getId()) {
                bill = other;
            }
        }
    }
    
    public Bill getBill() {
        return bill;
    }
      
    public Collection<Bill> getBills() {
        return bills;
    }
    
    public Collection<Object> getMovements() {
        ArrayList<Object> movements = new ArrayList<Object>();
        
        if (bill != null) {
            movements.addAll(bill.getMovements());
        }
        
        return movements;
    }
    
    public Driver getLoggedInDriver() {
        FacesContext context = FacesContext.getCurrentInstance();
        ExternalContext externalContext = context.getExternalContext();
        HttpServletRequest request = (HttpServletRequest) externalContext.getRequest();
        
        if (request.getUserPrincipal() != null) {
            int bsn = Integer.parseInt(request.getUserPrincipal().getName());
            return service.getDriverByBSN(bsn);
        } else {
            return null;
        }
    }
    
    @PostConstruct
    public void postConstruct() {
        Driver driver = getLoggedInDriver();
        
        if (driver != null) {
            bills = driver.getBills();
        }
    }
    
    public void save() {
        service.editBill(bill);
    }
    
    public void payBill(Long billID) {
        //Ga naar externe payservice
        
        //if succesvol gelukt bij paypal
        //service.payBill(billID);
    }
}
