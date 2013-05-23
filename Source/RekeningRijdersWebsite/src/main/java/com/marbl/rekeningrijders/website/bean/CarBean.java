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
public class CarBean implements Serializable {

    //<editor-fold defaultstate="collapsed" desc="Fields">
    @Inject
    private RekeningRijdersService service;
    private Collection<Car> all;
    private Car current;
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="Getters & Setters">
    public String getLicensePlate() {
        if (current != null) {
            return current.getLicensePlate();
        } else {
            return "";
        }
    }

    public void setLicensePlate(String licensePlate) {
        for (Car other : all) {
            if (licensePlate.equals(other.getLicensePlate())) {
                current = other;
            }
        }
    }

    public Collection<Car> getAll() {
        return all;
    }

    public Car getCurrent() {
        return current;
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
            all = driver.getCars();
        }
    }

    public void save() {
        service.editCar(current);
    }
    //</editor-fold>
}
