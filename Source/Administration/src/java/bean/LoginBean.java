package bean;

import domain.AdministrationAccount;
import java.io.IOException;
import java.io.Serializable;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import service.AdministrationService;

@ManagedBean
@SessionScoped
public class LoginBean implements Serializable {

    @EJB
    AdministrationService administrationService;
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
    
    public List<AdministrationAccount> getAdministrationAccounts() {
        return administrationService.findAllAdministrationAccounts();
    }

    public void login() {
        System.out.println("LOGIN 1");
        for (AdministrationAccount administrationAccount : getAdministrationAccounts()) {
            if (administrationAccount.getUsername().toLowerCase().equals(username.toLowerCase())) {
                if (administrationAccount.getPassword().equals(password)) {
                    try {
                        ExternalContext context = FacesContext.getCurrentInstance().getExternalContext();
                        context.redirect("view.xhtml");
                    } catch (IOException ex) {
                        Logger.getLogger(LoginBean.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
                break;
            }
        }
        System.out.println("LOGIN 2");
    }
}
