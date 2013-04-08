package bean;

import domain.AdministrationAccount;
import java.io.IOException;
import java.io.Serializable;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.enterprise.context.SessionScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import service.AdministrationService;

@Named
@SessionScoped
public class LoginBean implements Serializable {

    @Inject
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
        for (AdministrationAccount administrationAccount : getAdministrationAccounts()) {
            if (administrationAccount.getName().toLowerCase().equals(username.toLowerCase())) {
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
    }
}
