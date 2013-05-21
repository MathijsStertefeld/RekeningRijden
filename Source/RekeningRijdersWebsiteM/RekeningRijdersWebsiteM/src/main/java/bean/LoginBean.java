package bean;

import java.io.IOException;
import java.io.Serializable;
import java.security.Principal;
import java.util.ResourceBundle;
import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import service.RekeningRijdersService;

@Named
@RequestScoped
public class LoginBean implements Serializable {

    @Inject
    RekeningRijdersService service;
    
    String email;
    String password;
    String languageCode;
    private String localisedString;
    
    private ResourceBundle localisedBundle;

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
    
    public String getLanguageCode() {
        return languageCode;
    }
    
    public void setLanguageCode(String languageCode) {
        this.languageCode = languageCode;
        
        if (languageCode.equals("nl"))
        {
            localisedBundle = ResourceBundle.getBundle("Language_nl");
        }
        else if (languageCode.equals("en"))
        {
            localisedBundle = ResourceBundle.getBundle("Language_en");
        }
    }
    
    public String getLocalisedString(String stringName)
    {
        return localisedBundle.getString("User");
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
        
        setLanguageCode("nl");
        
        if (userPrincipal != null) {
            try {
                FacesContext.getCurrentInstance().getExternalContext().redirect("BillOverview.xhtml");
            } catch (IOException ex) {
            }
        }
    }

    public void login() {
        FacesContext context = FacesContext.getCurrentInstance();
        ExternalContext externalContext = context.getExternalContext();
        HttpServletRequest request = (HttpServletRequest) externalContext.getRequest();
        
        try {
            request.login(email, password);
            externalContext.redirect("BillOverview.xhtml");
        } catch (IOException ex) {
        } catch (ServletException ex) {
            context.addMessage(null, new FacesMessage(ex.getMessage()));
        }
        
        password = "";
    }
}
