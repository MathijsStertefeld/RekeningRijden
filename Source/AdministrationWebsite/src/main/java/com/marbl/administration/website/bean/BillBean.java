package com.marbl.administration.website.bean;

//<editor-fold defaultstate="collapsed" desc="Imports">
import com.marbl.administration.domain.Bill;
import com.marbl.administration.domain.Car;
import com.marbl.administration.domain.CarType;
import com.marbl.administration.website.service.BillService;
import com.marbl.administration.website.service.CarService;
import com.marbl.administration.website.service.MovementService;
import com.marbl.administration.website.service.RateService;
import com.marbl.autosysteem.Movement;
import com.marbl.autosysteem.TimeStep;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Map;
import javax.enterprise.context.SessionScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
//</editor-fold>

@Named
@SessionScoped
public class BillBean implements Serializable {

    //<editor-fold defaultstate="collapsed" desc="Fields">
    @Inject
    private BillService service;
    private ArrayList<Bill> all;
    private Bill current;
    @Inject
    private CarService carService;
    @Inject
    private RateService rateService;
    @Inject
    private MovementService movementService;
    
    /*private Movement mockMovement1 = new Movement(1, 900000000, "t0", new Date(), "", "", "Regio Eindhoven", "REGION", 200, new TimeStep());
    private Movement mockMovement2 = new Movement(2, 900000000, "t0", new Date(), "", "", "Regio Eindhoven", "REGION", 100, new TimeStep());
    private Movement mockMovement3 = new Movement(3, 900000000, "t0", new Date(), "", "", "Eindhoven", "CITY", 100, new TimeStep());
    private Movement mockMovement4 = new Movement(4, 900000000, "t0", new Date(), "", "", "A2", "HIGHWAY", 100, new TimeStep());
    private Movement mockMovement5 = new Movement(5, 900000000, "t0", new Date(), "", "", "A2", "HIGHWAY", 200, new TimeStep());*/
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="Getters & Setters">
    public ArrayList<Bill> getAll() {
        return all;
    }

    public Bill getCurrent() {
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
            Long id = Long.parseLong(requestParameterMap.get("id"));
            current = service.find(id);
        }

        if (current == null) {
            showOverview();
        }
    }
    
    public void generate() {
        ArrayList<Bill> bills = new ArrayList();
        
        // TODO: iterate through movement history
        for (Car car : carService.findAll()) {
            
            // TODO: use driver bsn and car tracker id from history
            Integer driverBSN = car.getDriverBSN();
            String carTrackerId = car.getCarTrackerId();
            
            // TODO: use rates to calculate payment amount
            Double paymentAmount = 0d;
            ArrayList<Movement> movement = movementService.findAll(car.getDriverBSN(), car.getCarTrackerId());
            if (movement != null)
            {
                for (Movement mov : movement)
                {
                    paymentAmount = mov.getDistance();
                    System.err.println(paymentAmount);
                    paymentAmount /= 1000;
                    System.err.println(paymentAmount);
                    paymentAmount *= rateService.find(car.getType().toString()).getPrice();
                    System.err.println(paymentAmount);
                    paymentAmount *= (car.getMass() * rateService.findMassRate().getPrice());
                    System.err.println(paymentAmount);
                    paymentAmount *= rateService.findAllRegionRates().get(1).getPrice();
                    System.err.println(paymentAmount);              
                }
            }
            
            
            
            // TODO: generate bills for all unpaid months
            Integer paymentMonth = Calendar.getInstance().get(Calendar.MONTH) - 1;
            Integer paymentYear = Calendar.getInstance().get(Calendar.YEAR);
            
            bills.add(new Bill(driverBSN, carTrackerId, paymentAmount, paymentMonth, paymentYear));
        }
        
        for (Bill bill : bills) {
            service.create(bill);
        }
        
        findAll();
    }

    public void saveChanges() {
        service.edit(current);
        showOverview();
    }

    public void showOverview() {
        FacesContext facesContext = FacesContext.getCurrentInstance();
        ExternalContext externalContext = facesContext.getExternalContext();
        
        try {
            externalContext.redirect("bill-overview.xhtml");
            current = null;
        } catch (IOException ex) {
        }
    }
    //</editor-fold>
}
