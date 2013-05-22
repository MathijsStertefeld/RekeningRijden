package administratiewebsite.bean;

//<editor-fold defaultstate="collapsed" desc="Imports">
import administratiewebsite.service.CarService;
import administration.domain.Car;
import java.io.IOException;
import java.io.Serializable;
import java.util.Collection;
import java.util.Map;
import javax.enterprise.context.SessionScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
//</editor-fold>

@Named
@SessionScoped
public class CarBean implements Serializable {

    //<editor-fold defaultstate="collapsed" desc="Fields">
    @Inject
    private CarService service;
    private Collection<Car> all;
    private Car current;
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="Getters & Setters">
    public Collection<Car> getAll() {
        return all;
    }

    public Car getCurrent() {
        return current;
    }
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="Methods">
    public void findAll() {
        all = service.findAll();
    }

    public void findCurrent() {
        FacesContext facesContext = FacesContext.getCurrentInstance();
        ExternalContext externalContext = facesContext.getExternalContext();
        Map<String, String> requestParameterMap = externalContext.getRequestParameterMap();

        if (requestParameterMap.containsKey("id")) {
            String id = requestParameterMap.get("id");
            current = service.find(id);
        }

        if (current == null) {
            showOverview();
        }
    }

    public void saveChanges() {
        service.edit(current);
        showOverview();
    }

    public void showOverview() {
        FacesContext facesContext = FacesContext.getCurrentInstance();
        ExternalContext externalContext = facesContext.getExternalContext();
        
        try {
            externalContext.redirect("CarOverview.xhtml");
            current = null;
        } catch (IOException ex) {
        }
    }
    //</editor-fold>
}
