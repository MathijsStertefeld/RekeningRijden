package com.marbl.rekeningrijders.website.bean;

//<editor-fold defaultstate="collapsed" desc="Imports">
import com.marbl.administration.domain.Driver;
import com.marbl.rekeningrijders.website.service.RekeningRijdersService;
import java.io.Serializable;
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
public class DriverBean implements Serializable {

    //<editor-fold defaultstate="collapsed" desc="Fields">
    @Inject
    private RekeningRijdersService service;
    private Driver loggedInDriver;
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="Getters & Setters">
    public Driver getLoggedInDriver() {
        return loggedInDriver;
    }

    public Driver findLoggedInDriver() {
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
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="Methods">
    @PostConstruct
    public void postConstruct() {
        loggedInDriver = findLoggedInDriver();
    }

    public void save() {
        service.editDriver(loggedInDriver);
    }
    //</editor-fold>
}
