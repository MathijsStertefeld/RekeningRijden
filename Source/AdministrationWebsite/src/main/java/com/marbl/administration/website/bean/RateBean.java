package com.marbl.administration.website.bean;

//<editor-fold defaultstate="collapsed" desc="Imports">
import com.marbl.administration.domain.Rate;
import com.marbl.administration.website.service.RateService;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Map;
import javax.enterprise.context.SessionScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
//</editor-fold>

@Named
@SessionScoped
public class RateBean implements Serializable {

    //<editor-fold defaultstate="collapsed" desc="Fields">
    @Inject
    private RateService service;
    private ArrayList<Rate> highwayRates;
    private ArrayList<Rate> regionRates;
    private ArrayList<Rate> cityRates;
    private ArrayList<Rate> vehicleRates;
    private Rate massRate;
    private Rate current;
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="Getters & Setters">
    public ArrayList<Rate> getHighwayRates() {
        return highwayRates;
    }
    
    public ArrayList<Rate> getRegionRates() {
        return regionRates;
    }
    
    public ArrayList<Rate> getCityRates() {
        return cityRates;
    }
    
    public ArrayList<Rate> getVehicleRates() {
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
