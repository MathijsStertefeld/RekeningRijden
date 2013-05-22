package com.marbl.rekeningrijders.bean;

import com.marbl.administration.domain.Driver;
import java.io.IOException;
import java.io.Serializable;
import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import com.marbl.rekeningrijders.service.RekeningRijdersService;

@Named
@SessionScoped
public class DriverBean implements Serializable {

    @Inject
    private RekeningRijdersService service;
    private Driver driver;

    public Driver getDriver() {
        return driver;
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
        driver = getLoggedInDriver();
    }
    
    public void save() {
        service.editDriver(driver);
    } 
}
