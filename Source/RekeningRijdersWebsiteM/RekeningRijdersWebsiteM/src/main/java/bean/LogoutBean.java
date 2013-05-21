package bean;

import administration.domain.DriverGroup;
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
        FacesContext context = FacesContext.getCurrentInstance();
        ExternalContext externalContext = context.getExternalContext();
        HttpServletRequest request = (HttpServletRequest) externalContext.getRequest();
        
        return request.isUserInRole(DriverGroup.ADMIN.toString());
    }

    public Boolean getIsJamDriver() {
        FacesContext context = FacesContext.getCurrentInstance();
        ExternalContext externalContext = context.getExternalContext();
        HttpServletRequest request = (HttpServletRequest) externalContext.getRequest();
        
        return request.isUserInRole(DriverGroup.JAM_DRIVER.toString());
    }

    public Boolean getIsDriver() {
        FacesContext context = FacesContext.getCurrentInstance();
        ExternalContext externalContext = context.getExternalContext();
        HttpServletRequest request = (HttpServletRequest) externalContext.getRequest();
        
        return request.isUserInRole(DriverGroup.DRIVER.toString());
    }
    
    private Principal getUserPrincipal() {
        FacesContext context = FacesContext.getCurrentInstance();
        ExternalContext externalContext = context.getExternalContext();
        HttpServletRequest request = (HttpServletRequest) externalContext.getRequest();
        
        return request.getUserPrincipal();
    }
    
    @PostConstruct
    public void postConstruct() {
        if (getUserPrincipal() == null) {
            try {
                FacesContext.getCurrentInstance().getExternalContext().redirect("Login.xhtml");
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
            externalContext.redirect("Login.xhtml");
        } catch (IOException ex) {
        } catch (ServletException ex) {
        }
    }
}
