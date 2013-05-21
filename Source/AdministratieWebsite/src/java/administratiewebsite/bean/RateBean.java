package administratiewebsite.bean;

import administratiewebsite.service.RateService;
import administration.domain.Rate;
import java.io.IOException;
import java.io.Serializable;
import java.util.Collection;
import java.util.Map;
import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;

@Named
@SessionScoped
public class RateBean implements Serializable {

    @Inject
    private RateService service;
    private Collection<Rate> all;
    private Rate current;

    public Collection<Rate> getAll() {
        return all;
    }
    
    public Rate getCurrent() {
        return current;
    }
    
    @PostConstruct
    public void postConstruct() {
        Map<String, String> requestParameterMap =
                FacesContext.getCurrentInstance().
                getExternalContext().getRequestParameterMap();
        
        if (requestParameterMap.containsKey("name")) {
            String name = requestParameterMap.get("name");
            current = service.find(name);
        } else {
            all = service.findAll();
        }
    }

    public void editCurrent() {
        service.edit(current);
        all = service.findAll();
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
                context.redirect("RateOverview.xhtml");
            } catch (IOException ex) {
            }
        }
    }
}
