package com.marbl.rekeningrijders.website.bean;

//<editor-fold defaultstate="collapsed" desc="Imports">
import com.marbl.administration.domain.Driver;
import com.marbl.rekeningrijders.website.service.RekeningRijdersService;
import java.io.IOException;
import java.io.Serializable;
import java.util.Locale;
import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
//</editor-fold>

@Named
@RequestScoped
public class LoginBean implements Serializable {

    //<editor-fold defaultstate="collapsed" desc="Fields">
    @Inject
    private RekeningRijdersService service;
    private String email;
    private String password;
    private String languageCode;
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

        if (languageCode.equals("nl")) {
            FacesContext.getCurrentInstance().getViewRoot().setLocale(dutchLocale);
        } else if (languageCode.equals("en")) {
            FacesContext.getCurrentInstance().getViewRoot().setLocale(englishLocale);
        }
    }

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
        dutchLocale = new Locale.Builder().setLanguage("nl").build();
        englishLocale = new Locale.Builder().setLanguage("en").build();
        setLanguageCode("nl");

        if (getLoggedInDriver() != null) {
            try {
                FacesContext.getCurrentInstance().getExternalContext().redirect("driver-overview.xhtml");
            } catch (IOException ex) {
            }
        }
    }

    public void login() {
        FacesContext context = FacesContext.getCurrentInstance();
        ExternalContext externalContext = context.getExternalContext();
        HttpServletRequest request = (HttpServletRequest) externalContext.getRequest();
        Driver driver = service.findDriverByEmail(email);

        if (driver != null) {
            System.err.println(driver.getActivated() + driver.getEmail());
            if (driver.getActivated() == true) {
                try {
                    String username = String.valueOf(driver.getBsn());
                    request.login(username, password);
                    externalContext.redirect(".");
                } catch (IOException ex) {
                } catch (ServletException ex) {
                    context.addMessage(null, new FacesMessage(ex.getMessage()));
                }
            }
        }

        password = "";
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

    public void changeLanguageToNL() {
        setLanguageCode("nl");
    }

    public void changeLanguageToEN() {
        setLanguageCode("en");
    }
}
