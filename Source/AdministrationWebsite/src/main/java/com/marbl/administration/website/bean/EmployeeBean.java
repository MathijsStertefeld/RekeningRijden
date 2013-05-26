package com.marbl.administration.website.bean;

//<editor-fold defaultstate="collapsed" desc="Imports">
import com.marbl.administration.domain.Employee;
import com.marbl.administration.website.service.EmployeeService;
import java.io.Serializable;
import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;
//</editor-fold>

@Named
@SessionScoped
public class EmployeeBean implements Serializable {
    
    //<editor-fold defaultstate="collapsed" desc="Fields">
    @Inject
    private EmployeeService service;
    private Employee employee;
    private String username;
    private String password;
    //</editor-fold>
    
    //<editor-fold defaultstate="collapsed" desc="Getters & Setters">
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
    //</editor-fold>
    
    //<editor-fold defaultstate="collapsed" desc="Methods">
    public String checkLogin() {
        if (employee == null) {
            return "login.xhtml";
        } else {
            return null;
        }
    }
    
    public String checkLogout() {
        if (employee != null) {
            return "car-overview.xhtml";
        } else {
            return null;
        }
    }
    
    public String login() {
        employee = service.login(username, password);
        username = "";
        password = "";
        return "";
    }
    
    public String logout() {
        employee = null;
        return "";
    }
    //</editor-fold>
}
