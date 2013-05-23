package com.marbl.rekeningrijders.website.bean;

//<editor-fold defaultstate="collapsed" desc="Imports">
import com.marbl.administration.domain.*;
import com.marbl.rekeningrijders.website.service.RekeningRijdersService;
import java.io.Serializable;
import java.util.Collection;
import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.http.HttpServletRequest;
//</editor-fold>

@Named
@SessionScoped
public class BillBean implements Serializable {

    //<editor-fold defaultstate="collapsed" desc="Fields">
    @Inject
    private RekeningRijdersService service;
    private Collection<Bill> all;
    private Bill current;
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="Getters & Setters">
    public Long getBillId() {
        if (current != null) {
            return current.getId();
        } else {
            return 0L;
        }
    }

    public void setBillId(Long id) {
        for (Bill other : all) {
            if (id == other.getId()) {
                current = other;
            }
        }
    }

    public Collection<Bill> getAll() {
        return all;
    }

    public Bill getCurrent() {
        return current;
    }

    public Collection<Object> getMovements() {
        if (current != null) {
            return current.getMovements();
        } else {
            return null;
        }
    }
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="Methods">
    public Driver findLoggedInDriver() {
        FacesContext facesContext = FacesContext.getCurrentInstance();
        ExternalContext externalContext = facesContext.getExternalContext();
        HttpServletRequest request = (HttpServletRequest) externalContext.getRequest();

        if (request.getUserPrincipal() != null) {
            int bsn = Integer.parseInt(request.getUserPrincipal().getName());
            return service.findDriver(bsn);
        } else {
            return null;
        }
    }

    @PostConstruct
    public void postConstruct() {
        Driver driver = findLoggedInDriver();

        if (driver != null) {
            all = driver.getBills();
        }
    }

    public void save() {
        service.editBill(current);
    }

    public void payBill(Long billId) {
        //Ga naar externe payservice
        //if succesvol gelukt bij paypal
        //service.payBill(billId);
    }
    //</editor-fold>
}
