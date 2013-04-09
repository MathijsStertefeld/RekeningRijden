package bean;

import administration.domain.Employee;
//import administration.service.AdministrationService;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.enterprise.context.SessionScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.http.HttpServletRequest;

@Named
@SessionScoped
public class LoginBean implements Serializable {

    //@Inject
    //AdministrationService administrationService;
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
    
    public ArrayList<Employee> getEmployees() {
        ArrayList<Employee> empColl = new ArrayList<Employee>();
        empColl.add(new Employee("admin", "admin", true));
        return empColl;
        //return administrationService.findAllEmployees();
    }

    public void login() {
        ExternalContext context = FacesContext.getCurrentInstance().getExternalContext();
        HttpServletRequest request = (HttpServletRequest) context.getRequest();
        try {
            //request.login(username, password);

            if (getEmployees().get(0).getName().equals(username))
            {

                if (getEmployees().get(0).getPassword().equals(password))
                {
                    username = "";
                    password = "";                
                    context.redirect("CarOverview.xhtml");
                }
            }
            
        } catch (Exception ex) {
            Logger.getLogger(LoginBean.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
