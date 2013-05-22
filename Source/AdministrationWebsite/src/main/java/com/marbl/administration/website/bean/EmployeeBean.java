package com.marbl.administration.website.bean;

//<editor-fold defaultstate="collapsed" desc="Imports">
import com.marbl.administration.domain.Employee;
import com.marbl.administration.website.service.EmployeeService;
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
public class EmployeeBean implements Serializable {
    
    //<editor-fold defaultstate="collapsed" desc="Fields">
    @Inject
    private EmployeeService service;
    private Employee loggedInEmployee;
    private String loginUsername;
    private String loginPassword;
    //</editor-fold>
    
    //<editor-fold defaultstate="collapsed" desc="Getters & Setters">
    public String getLoginUsername() {
        return loginUsername;
    }

    public void setLoginUsername(String loginUsername) {
        this.loginUsername = loginUsername;
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
        
        loggedInEmployee = findLoggedInEmployee();
        
        if (loggedInEmployee == null) {
            try {
                externalContext.redirect(".");
            } catch (IOException ex) {
            }
        }
    }
    
    public void checkLogout() {
        FacesContext facesContext = FacesContext.getCurrentInstance();
        ExternalContext externalContext = facesContext.getExternalContext();
        
        loggedInEmployee = findLoggedInEmployee();
        
        if (loggedInEmployee != null) {
            try {
                externalContext.redirect("car-overview.xhtml");
            } catch (IOException ex) {
            }
        }
    }

    private Employee findLoggedInEmployee() {
        FacesContext facesContext = FacesContext.getCurrentInstance();
        ExternalContext externalContext = facesContext.getExternalContext();
        HttpServletRequest request = (HttpServletRequest) externalContext.getRequest();
        Principal userPrincipal = request.getUserPrincipal();
        
        if (userPrincipal != null) {
            String username = userPrincipal.getName();
            return service.find(username);
        } else {
            return null;
        }
    }
    
    public void login() {
        FacesContext facesContext = FacesContext.getCurrentInstance();
        ExternalContext externalContext = facesContext.getExternalContext();
        HttpServletRequest request = (HttpServletRequest) externalContext.getRequest();
        
        try {
            request.login(loginUsername, loginPassword);
            loginUsername = "";
            loginPassword = "";
            externalContext.redirect(".");
        } catch (IOException ex) {
        } catch (ServletException ex) {
            facesContext.addMessage(null, new FacesMessage(ex.getMessage()));
        }
    }
    
    public void logout() {
        FacesContext facesContext = FacesContext.getCurrentInstance();
        ExternalContext externalContext = facesContext.getExternalContext();
        HttpServletRequest request = (HttpServletRequest) externalContext.getRequest();

        try {
            request.logout();
            externalContext.redirect(".");
        } catch (IOException ex) {
        } catch (ServletException ex) {
        }
    }
    //</editor-fold>
}
