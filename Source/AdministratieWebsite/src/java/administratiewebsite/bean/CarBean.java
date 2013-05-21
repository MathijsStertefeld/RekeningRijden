package administratiewebsite.bean;

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

@Named
@SessionScoped
public class CarBean implements Serializable {

    @Inject
    private CarService service;
    private Collection<Car> all;
    private Car current;

    public Collection<Car> getAll() {
        return all;
    }

    public Car getCurrent() {
        return current;
    }

    public void editCurrent() {
        service.edit(current);
        current = null;
    }

    public void findAll() {
        all = service.findAll();
    }

    public void findCurrent() {
        ExternalContext context = FacesContext.getCurrentInstance().getExternalContext();
        Map<String, String> requestParameterMap = context.getRequestParameterMap();

        String id = null;

        if (requestParameterMap.containsKey("id")) {
            id = requestParameterMap.get("id");
        }

        current = service.find(id);

        if (current == null) {
            try {
                context.redirect("CarOverview.xhtml");
            } catch (IOException ex) {
            }
        }
    }
}
