package com.marbl.rekeningrijders.website.bean;

import com.marbl.administration.domain.Driver;
import java.io.IOException;
import java.io.Serializable;
import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import com.marbl.rekeningrijders.website.service.RekeningRijdersService;

@Named
@RequestScoped
public class LogoutBean implements Serializable {
    
    @Inject
    RekeningRijdersService service;
    
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
        FacesContext context = FacesContext.getCurrentInstance();
        ExternalContext externalContext = context.getExternalContext();
        
        if (getLoggedInDriver() == null) {
            try {
                externalContext.redirect(".");
            } catch (IOException ex) {
            }
        }
    }

    public void logout() {
        FacesContext context = FacesContext.getCurrentInstance();
        ExternalContext externalContext = context.getExternalContext();
        HttpServletRequest request = (HttpServletRequest) externalContext.getRequest();

        try {
            request.logout();
            externalContext.redirect(".");
        } catch (IOException ex) {
        } catch (ServletException ex) {
        }
    }
}
