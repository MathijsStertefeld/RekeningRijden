package administratiewebsite.bean;

import administratiewebsite.service.BillService;
import administration.domain.Bill;
import java.io.IOException;
import java.io.Serializable;
import java.util.Collection;
import java.util.Map;
import javax.enterprise.context.RequestScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;

@Named
@RequestScoped
public class BillBean implements Serializable {

    @Inject
    private BillService service;
    private Collection<Bill> all;
    private Bill current;

    public Collection<Bill> getAll() {
        return all;
    }
    
    public Bill getCurrent() {
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

        Long id = null;
        
        if (requestParameterMap.containsKey("id")) {
            id = Long.parseLong(requestParameterMap.get("id"));
        }
        
        current = service.find(id);

        if (current == null) {
            try {
                context.redirect("BillOverview.xhtml");
            } catch (IOException ex) {
            }
        }
    }
}
