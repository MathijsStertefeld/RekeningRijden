package bean;

import administration.domain.Driver;
import java.io.Serializable;
import java.security.Principal;
import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.http.HttpServletRequest;
import service.RekeningRijdersService;

@Named
@SessionScoped
public class DriverBean implements Serializable {

    @Inject
    private RekeningRijdersService service;
    private Driver driver;

    public Driver getDriver() {
        return driver;
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
        
        if (userPrincipal != null) {
            String email = userPrincipal.getName();
            driver = service.getDriverByEmail(email);
        }
    }
    
    public void save() {
        service.editDriver(driver);
    }
}
