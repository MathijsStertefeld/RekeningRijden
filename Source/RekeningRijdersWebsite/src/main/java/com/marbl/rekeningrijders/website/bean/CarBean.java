package com.marbl.rekeningrijders.website.bean;

import com.marbl.administration.domain.*;
import java.io.Serializable;
import java.util.Collection;
import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.http.HttpServletRequest;
import com.marbl.rekeningrijders.website.service.RekeningRijdersService;

@Named
@SessionScoped
public class CarBean implements Serializable {

    
    @Inject
    private RekeningRijdersService service;
    private Car car;
    private Collection<Car> cars;
    
    public String getLicensePlate() {
        if (car != null) {
            return car.getLicensePlate();
        } else {
            return "";
        }
    }
    
    public void setLicensePlate(String licensePlate) {
        for (Car other : cars) {
            if (licensePlate.equals(other.getLicensePlate())) {
                car = other;
            }
        }
    }
    
    public Car getCar() {
        return car;
    }
    
    public Collection<Car> getCars() {
        return cars;
    }
    
    public Driver getLoggedInDriver() {
        FacesContext context = FacesContext.getCurrentInstance();
        ExternalContext externalContext = context.getExternalContext();
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
        Driver driver = getLoggedInDriver();
        
        if (driver != null) {
            cars = driver.getCars();
        }
    }
    
    public void save() {
        service.editCar(car);
    }
}
