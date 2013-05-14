package bean;

import administration.domain.Driver;
import administration.domain.Employee;
import java.io.IOException;
import java.io.Serializable;
import java.security.Principal;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import service.RekeningRijdersService;

@Named
@SessionScoped
public class LoginBean implements Serializable {

    @Inject
    RekeningRijdersService service;
    
    String email;
    String password;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    
    private Principal getUserPrincipal() {
        FacesContext context = FacesContext.getCurrentInstance();
        ExternalContext externalContext = context.getExternalContext();
        HttpServletRequest request = (HttpServletRequest) externalContext.getRequest();
        
        return request.getUserPrincipal();
    }
    
    @PostConstruct
    public void postConstruct() {
        Principal userPrincipal = getUserPrincipal();
        
        if (userPrincipal != null) {
            try {
                Driver driver = service.getDriverByEmail(userPrincipal.getName());
                FacesContext.getCurrentInstance().getExternalContext().redirect("BillOverview.xhtml?bsn=" + driver.getBsn());
            } catch (IOException ex) {
            }
        }
    }

    public void login() {
        FacesContext context = FacesContext.getCurrentInstance();
        ExternalContext externalContext = context.getExternalContext();
        HttpServletRequest request = (HttpServletRequest) externalContext.getRequest();
        
        try {
            Driver driver = service.getDriverByEmail(email);
            request.login(email, password);             
            externalContext.redirect("BillOverview.xhtml?bsn=" + driver.getBsn());
        } catch (IOException ex) {
        } catch (ServletException ex) {
            context.addMessage(null, new FacesMessage(ex.getMessage()));
        }
        
        password = "";
    }
}
