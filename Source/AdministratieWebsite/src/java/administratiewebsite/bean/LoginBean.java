package administratiewebsite.bean;

import java.io.IOException;
import java.io.Serializable;
import java.security.Principal;
import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;

@Named
@RequestScoped
public class LoginBean implements Serializable {

    String username;
    String password;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
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
        if (getUserPrincipal() != null) {
            try {
                FacesContext.getCurrentInstance().getExternalContext().redirect("CarOverview.xhtml");
            } catch (IOException ex) {
            }
        }
    }

    public void login() {
        FacesContext context = FacesContext.getCurrentInstance();
        ExternalContext externalContext = context.getExternalContext();
        HttpServletRequest request = (HttpServletRequest) externalContext.getRequest();
        
        try {
            request.login(username, password);             
            externalContext.redirect("CarOverview.xhtml");
        } catch (IOException ex) {
        } catch (ServletException ex) {
            context.addMessage(null, new FacesMessage(ex.getMessage()));
        }
        
        password = "";
    }
}
