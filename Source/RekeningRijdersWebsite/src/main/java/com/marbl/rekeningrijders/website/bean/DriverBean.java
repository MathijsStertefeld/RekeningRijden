package com.marbl.rekeningrijders.website.bean;

//<editor-fold defaultstate="collapsed" desc="Imports">
import com.marbl.administration.domain.Driver;
import com.marbl.rekeningrijders.website.service.RekeningRijdersService;
import java.io.IOException;
import java.io.Serializable;
import javax.enterprise.context.SessionScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
//</editor-fold>

@Named
@SessionScoped
public class DriverBean implements Serializable {

    //<editor-fold defaultstate="collapsed" desc="Fields">
    @Inject
    private RekeningRijdersService service;
    private Driver driver;
    private String email;
    private String password;
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="Getters & Setters">
    public Driver getDriver() {
        return driver;
    }
    
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
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="Methods">
    public void checkLogin() throws IOException {
        if (driver == null) {
            FacesContext.getCurrentInstance().getExternalContext().redirect("login.xhtml");
        }
    }
    
    public void checkLogout() throws IOException {
        if (driver != null) {
            FacesContext.getCurrentInstance().getExternalContext().redirect("driver-overview.xhtml");
        }
    }

    public String login() {
        driver = service.login(email, password);
        email = "";
        password = "";
        return "";
    }
    
    public String logout() {
        driver = null;
        return "";
    }

    public void saveChanges() throws IOException {
        service.editDriver(driver);
        showOverview();
    }

    public void showOverview() throws IOException {
        FacesContext.getCurrentInstance().getExternalContext().redirect("driver-overview.xhtml");
    }
    //</editor-fold>
}
