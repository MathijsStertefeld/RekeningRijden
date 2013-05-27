package com.marbl.administration.website.bean;

//<editor-fold defaultstate="collapsed" desc="Imports">
import com.marbl.administration.domain.Rate;
import com.marbl.administration.website.service.RateService;
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

// This bean is used by rate-details.xhtml and rate-overview.xhtml.

@Named
@SessionScoped
public class RateBean implements Serializable {

    //<editor-fold defaultstate="collapsed" desc="Fields">
    @Inject
    private RateService service;
    private Collection<Rate> highwayRates;
    private Collection<Rate> regionRates;
    private Collection<Rate> cityRates;
    private Collection<Rate> vehicleRates;
    private Rate massRate;
    private Rate current;
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="Getters & Setters">
    public Collection<Rate> getHighwayRates() {
        return highwayRates;
    }
    
    public Collection<Rate> getRegionRates() {
        return regionRates;
    }
    
    public Collection<Rate> getCityRates() {
        return cityRates;
    }
    
    public Collection<Rate> getVehicleRates() {
        return vehicleRates;
    }
    
    public Rate getMassRate() {
        return massRate;
    }
    
    public void setMassRate(Rate massRate) {
        this.massRate = massRate;
    }

    public Rate getCurrent() {
        return current;
    }
    
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="Methods">
    public void findAll() {
        highwayRates = service.findAllHighwayRates();
        regionRates = service.findAllRegionRates();
        cityRates = service.findAllCityRates();
        vehicleRates = service.findAllVehicleRates();
        massRate = service.findMassRate();
    }

    public void findCurrent() {
        FacesContext facesContext = FacesContext.getCurrentInstance();
        ExternalContext externalContext = facesContext.getExternalContext();
        Map<String, String> requestParameterMap = externalContext.getRequestParameterMap();

        if (requestParameterMap.containsKey("name")) {
            String name = requestParameterMap.get("name");
            current = service.find(name);
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
            externalContext.redirect("rate-overview.xhtml");
            current = null;
        } catch (IOException ex) {
        }
    }
    //</editor-fold>
}
