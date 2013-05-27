package com.marbl.administration.website.bean;

//<editor-fold defaultstate="collapsed" desc="Imports">
import com.marbl.administration.domain.Employee;
import com.marbl.administration.domain.EmployeeGroup;
import com.marbl.administration.website.service.EmployeeService;
import java.io.IOException;
import java.io.Serializable;
import javax.enterprise.context.SessionScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
//</editor-fold>

// This bean is used by login.xhtml.
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
    public void checkLogin() throws IOException {
        if (employee == null) {
            FacesContext.getCurrentInstance().getExternalContext().redirect("login.xhtml");
        }
    }

    public void checkLogout() throws IOException {
        if (employee != null) {
            FacesContext.getCurrentInstance().getExternalContext().redirect("car-overview.xhtml");
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
        System.out.println(employee);
        return "";
    }

    public boolean findPermission() {
        if (employee.getGroups().contains(EmployeeGroup.RATE_EMPLOYEE)) {
            return true;
        } else {
            return false;
        }
    }
    //</editor-fold>
}
