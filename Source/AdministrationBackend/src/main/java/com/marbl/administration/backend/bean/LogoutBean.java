package com.marbl.administration.backend.bean;

import com.marbl.administration.domain.EmployeeGroup;
import java.io.IOException;
import java.io.Serializable;
import java.security.Principal;
import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;

@Named
@RequestScoped
public class LogoutBean implements Serializable {
    
    public String getName() {
        Principal userPrincipal = getUserPrincipal();
        if (userPrincipal != null) {
            return userPrincipal.getName();
        } else {
            return "";
        }
    }

    public Boolean getIsAdmin() {
        FacesContext facesContext = FacesContext.getCurrentInstance();
        ExternalContext externalContext = facesContext.getExternalContext();
        HttpServletRequest request = (HttpServletRequest) externalContext.getRequest();
        
        return request.isUserInRole(EmployeeGroup.ADMIN.toString());
    }

    public Boolean getIsRateEmployee() {
        FacesContext facesContext = FacesContext.getCurrentInstance();
        ExternalContext externalContext = facesContext.getExternalContext();
        HttpServletRequest request = (HttpServletRequest) externalContext.getRequest();
        
        return request.isUserInRole(EmployeeGroup.RATE_EMPLOYEE.toString());
    }

    public Boolean getIsEmployee() {
        FacesContext facesContext = FacesContext.getCurrentInstance();
        ExternalContext externalContext = facesContext.getExternalContext();
        HttpServletRequest request = (HttpServletRequest) externalContext.getRequest();
        
        return request.isUserInRole(EmployeeGroup.EMPLOYEE.toString());
    }
    
    private Principal getUserPrincipal() {
        FacesContext facesContext = FacesContext.getCurrentInstance();
        ExternalContext externalContext = facesContext.getExternalContext();
        HttpServletRequest request = (HttpServletRequest) externalContext.getRequest();
        
        return request.getUserPrincipal();
    }
    
    @PostConstruct
    public void postConstruct() {
        if (getUserPrincipal() == null) {
            try {
                FacesContext.getCurrentInstance().getExternalContext().redirect(".");
            } catch (IOException ex) {
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
        } catch (IOException ex) {
        } catch (ServletException ex) {
        }
    }
}
