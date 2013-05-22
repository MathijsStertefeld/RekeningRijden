package bean;

import administration.domain.Driver;
import java.io.IOException;
import java.io.Serializable;
import java.util.Locale;
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
    
    private String email;
    private String password;
    String languageCode;
    
    private Locale dutchLocale;
    private Locale englishLocale;

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
            FacesContext.getCurrentInstance().getViewRoot().setLocale(dutchLocale);
        }
        else if (languageCode.equals("en"))
        {
            FacesContext.getCurrentInstance().getViewRoot().setLocale(englishLocale);
        }
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
        
        dutchLocale = new Locale.Builder().setLanguage("nl").build();
        englishLocale = new Locale.Builder().setLanguage("en").build();
        setLanguageCode("nl");
        
        if (getLoggedInDriver() != null) {
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
        Driver driver = service.getDriverByEmail(email);
        
        if (driver != null) {
            try {
                String username = String.valueOf(driver.getBsn());
                request.login(username, password);
                externalContext.redirect("BillOverview.xhtml");
            } catch (IOException ex) {
            } catch (ServletException ex) {
                context.addMessage(null, new FacesMessage(ex.getMessage()));
            }
        }
        
        password = "";
    }
    
    public void changeLanguageToNL()
    {
        setLanguageCode("nl");
    }
    
    public void changeLanguageToEN()
    {
        setLanguageCode("en");
    }
}
