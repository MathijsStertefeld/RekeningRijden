package bean;

import administration.domain.Driver;
import administration.domain.Employee;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.enterprise.context.SessionScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.http.HttpServletRequest;
import service.RekeningrijdersService;

@Named
@SessionScoped
public class LoginBean implements Serializable {

    @Inject
    RekeningrijdersService service;
    
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
    
    /*public ArrayList<Employee> getEmployees() {
        ArrayList<Employee> empColl = new ArrayList<Employee>();
        empColl.add(new Employee("admin", "admin", true));
        return empColl;
    }*/

    public void login() {
        ExternalContext context = FacesContext.getCurrentInstance().getExternalContext();
        HttpServletRequest request = (HttpServletRequest) context.getRequest();
        try {
            //request.login(username, password);

            Driver driver = service.getDriverByEmail(email);
            
            if (driver.getPassword().equals(password))
            {
                context.redirect("BillOverview.xhtml?bsn=" + driver.getBsn());
            }
            
            /*if (getEmployees().get(0).getName().equals(username)) {
                if (getEmployees().get(0).getPassword().equals(password)) {
                    username = "";
                    password = "";                
                    context.redirect("BillOverview.xhtml");
                }
            }*/
            
        } catch (Exception ex) {
            Logger.getLogger(LoginBean.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
