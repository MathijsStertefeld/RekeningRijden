package com.marbl.rekeningrijders.website.bean;

//<editor-fold defaultstate="collapsed" desc="Imports">
import com.marbl.administration.domain.Driver;
import com.marbl.rekeningrijders.website.service.RekeningRijdersService;
import java.io.IOException;
import java.io.Serializable;
import java.security.Principal;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
//</editor-fold>

@Named
@SessionScoped
public class DriverBean implements Serializable {

    //<editor-fold defaultstate="collapsed" desc="Fields">
    @Inject
    private RekeningRijdersService service;
    private Driver loggedInDriver;
    private String loginEmail;
    private String loginPassword;
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="Getters & Setters">
    public Driver getLoggedInDriver() {
        return loggedInDriver;
    }
    
    public String getLoginEmail() {
        return loginEmail;
    }

    public void setLoginEmail(String loginEmail) {
        this.loginEmail = loginEmail;
    }

    public String getLoginPassword() {
        return loginPassword;
    }

    public void setLoginPassword(String loginPassword) {
        this.loginPassword = loginPassword;
    }
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="Methods">
    public void checkLogin() {
        FacesContext facesContext = FacesContext.getCurrentInstance();
        ExternalContext externalContext = facesContext.getExternalContext();
        
        loggedInDriver = findLoggedInDriver();
        
        if (loggedInDriver == null) {
            try {
                externalContext.redirect(".");
            } catch (IOException ex) {
            }
        }
    }
    
    public void checkLogout() {
        FacesContext facesContext = FacesContext.getCurrentInstance();
        ExternalContext externalContext = facesContext.getExternalContext();
        
        loggedInDriver = findLoggedInDriver();
        
        if (loggedInDriver != null) {
            try {
                externalContext.redirect("driver-overview.xhtml");
            } catch (IOException ex) {
            }
        }
    }

    public Driver findLoggedInDriver() {
        FacesContext facesContext = FacesContext.getCurrentInstance();
        ExternalContext externalContext = facesContext.getExternalContext();
        HttpServletRequest request = (HttpServletRequest) externalContext.getRequest();
        Principal userPrincipal = request.getUserPrincipal();

        if (userPrincipal != null) {
            int bsn = Integer.parseInt(userPrincipal.getName());
            return service.findDriver(bsn);
        } else {
            return null;
        }
    }
    
    public void login() {
        FacesContext facesContext = FacesContext.getCurrentInstance();
        ExternalContext externalContext = facesContext.getExternalContext();
        HttpServletRequest request = (HttpServletRequest) externalContext.getRequest();
        Driver driver = service.login(loginEmail, loginPassword);
        
        if (driver != null) {
            try {
                String loginUsername = String.valueOf(driver.getBSN());
                request.login(loginUsername, loginPassword);
                loginEmail = "";
                loginPassword = "";
                externalContext.redirect(".");
            } catch (IOException | ServletException ex) {
                facesContext.addMessage(null, new FacesMessage(ex.getMessage()));
            }
        }
    }
    
    public void logout() {
        FacesContext facesContext = FacesContext.getCurrentInstance();
        ExternalContext externalContext = facesContext.getExternalContext();
        HttpServletRequest request = (HttpServletRequest) externalContext.getRequest();

        try {
            request.logout();
            externalContext.redirect(".");
        } catch (IOException | ServletException ex) {
        }
    }

    public void saveChanges() {
        service.editDriver(loggedInDriver);
        showOverview();
    }

    public void showOverview() {
        FacesContext facesContext = FacesContext.getCurrentInstance();
        ExternalContext externalContext = facesContext.getExternalContext();
        
        try {
            externalContext.redirect("driver-overview.xhtml");
        } catch (IOException ex) {
        }
    }
    //</editor-fold>
}
