package com.marbl.administration.backend.service;

//<editor-fold defaultstate="collapsed" desc="Imports">
import com.marbl.administration.backend.dao.*;
import com.marbl.administration.backend.utils.*;
import com.marbl.administration.domain.*;
import com.marbl.administration.domain.utils.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import javax.annotation.PostConstruct;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.inject.Inject;
//</editor-fold>

// This startup class is used to fill the database with mock data.

@Singleton
@Startup
public class StartupService implements Serializable {

    //<editor-fold defaultstate="collapsed" desc="Fields">
    @Inject
    private BillDAO billDAO;
    @Inject
    private CarDAO carDAO;
    @Inject
    private DriverDAO driverDAO;
    @Inject
    private EmployeeDAO employeeDAO;
    @Inject
    private RateDAO rateDAO;
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="Methods">
    @PostConstruct
    public void postConstruct() {
        Hasher hasher = new Hasher("SHA-256", "UTF-8");

        //<editor-fold defaultstate="collapsed" desc="Employees">
        ArrayList<Employee> employees = new ArrayList();

        employees.add(new Employee("admin", hasher.hash("admin123")));
        employees.add(new Employee("employee", hasher.hash("employee123")));
        employees.add(new Employee("rate123", hasher.hash("rate123")));
        
        employees.get(0).getGroups().add(EmployeeGroup.ADMIN);
        employees.get(0).getGroups().add(EmployeeGroup.RATE_EMPLOYEE);
        employees.get(1).getGroups().add(EmployeeGroup.RATE_EMPLOYEE);

        for (int i = 0; i < employees.size(); i++) {
            employees.get(i).getGroups().add(EmployeeGroup.EMPLOYEE);
            employeeDAO.create(employees.get(i));
        }
        //</editor-fold>

        //<editor-fold defaultstate="collapsed" desc="Drivers">
        DriverGenerator driverGenerator = new DriverGenerator("marbl.com", hasher,
                new String[]{"Alexander", "Bas", "Leslie", "Ruud", "Mathijs"},
                new String[]{"Arends", "Geerts", "Aerts", "Lenders", "Stertefeld"});

        Driver[] drivers = driverGenerator.generate();
        
        drivers[0].getGroups().add(DriverGroup.ADMIN);
        drivers[0].getGroups().add(DriverGroup.JAM_DRIVER);
        drivers[1].getGroups().add(DriverGroup.JAM_DRIVER);

        for (int i = 0; i < drivers.length; i++) {
            drivers[i].getGroups().add(DriverGroup.DRIVER);
            driverDAO.create(drivers[i]);
        }
        //</editor-fold>

        //<editor-fold defaultstate="collapsed" desc="Cars">
        CarGenerator carGenerator = new CarGenerator(drivers,
                new String[] {"Ford", "Suzuki", "Fiat", "Volkswagen"},
                new String[] {"Mustang", "Swift", "Panda", "Golf"});

        Car[] cars = carGenerator.generate(drivers.length * 3 / 2);

        for (int i = 0; i < cars.length; i++) {
            carDAO.create(cars[i]);
        }
        //</editor-fold>

        //<editor-fold defaultstate="collapsed" desc="Bills">
        ArrayList<Bill> bills = new ArrayList();

        bills.add(new Bill(drivers[0].getBSN(), cars[0].getCarTrackerId(), 100.00D, 1, 2013));
        bills.add(new Bill(drivers[1].getBSN(), cars[1].getCarTrackerId(), 100.00D, 1, 2013));
        bills.add(new Bill(drivers[2].getBSN(), cars[2].getCarTrackerId(), 100.00D, 1, 2013));
        
        bills.get(0).setPaymentDate(new Date());
        bills.get(0).setPaymentStatus(PaymentStatus.PAID);
        bills.get(1).setPaymentStatus(PaymentStatus.CANCELED);

        for (int i = 0; i < bills.size(); i++) {
            billDAO.create(bills.get(i));
        }
        //</editor-fold>

        //<editor-fold defaultstate="collapsed" desc="CityRates">
        ArrayList<Rate> cityRates = new ArrayList();
 
        cityRates.add(new Rate("Den Bosch", RateType.CITY, 1.2D));
        cityRates.add(new Rate("Eindhoven", RateType.CITY, 1.0D));
        cityRates.add(new Rate("Tilburg", RateType.CITY, 1.2D));
 
        for (int i = 0; i < cityRates.size(); i++) {
            rateDAO.create(cityRates.get(i));
        }
        //</editor-fold>
 
        //<editor-fold defaultstate="collapsed" desc="HighwayRates">
        ArrayList<Rate> highwayRates = new ArrayList();
        
        //highwayRates.add(new Rate("A2", Rate.RateType.HIGHWAY,      new ArrayList<Double>(Arrays.asList(1.1, 1.1, 1.1, 1.1, 1.1, 1.1, 1.1, 1.8, 1.8, 1.8, 1.1, 1.1, 1.1, 1.1, 1.1, 1.1, 1.1, 1.8, 1.8, 1.8, 1.1, 1.1, 1.1, 1.1))));
        //highwayRates.add(new Rate("A50", Rate.RateType.HIGHWAY,     new ArrayList<Double>(Arrays.asList(1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.5, 1.5, 1.5, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.5, 1.5, 1.5, 1.0, 1.0, 1.0, 1.0))));
        //highwayRates.add(new Rate("A67", Rate.RateType.HIGHWAY,     new ArrayList<Double>(Arrays.asList(1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.5, 1.5, 1.5, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.5, 1.5, 1.5, 1.0, 1.0, 1.0, 1.0))));
        //highwayRates.add(new Rate("A270", Rate.RateType.HIGHWAY,    new ArrayList<Double>(Arrays.asList(1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0))));
        
        highwayRates.add(new Rate("A2", RateType.HIGHWAY, 1.1D, 1.8D));
        highwayRates.add(new Rate("A50", RateType.HIGHWAY, 1.0D, 1.5D));
        highwayRates.add(new Rate("A67", RateType.HIGHWAY, 1.0D, 1.5D));
        highwayRates.add(new Rate("A270", RateType.HIGHWAY, 1.0D, 1.0D));
        
        for (int i = 0; i < highwayRates.size(); i++) {
            rateDAO.create(highwayRates.get(i));
        }
        //</editor-fold>
 
        //<editor-fold defaultstate="collapsed" desc="RegionRates">
        ArrayList<Rate> regionRates = new ArrayList();
 
        regionRates.add(new Rate("Regio Den Bosch", RateType.REGION, 1.2D));
        regionRates.add(new Rate("Regio Eindhoven", RateType.REGION, 1.0D));
        regionRates.add(new Rate("Regio Helmond", RateType.REGION, 0.8D));
        regionRates.add(new Rate("Regio Tilburg", RateType.REGION, 1.1D));
        regionRates.add(new Rate("Regio Weert", RateType.REGION, 0.8D));
 
        for (int i = 0; i < regionRates.size(); i++) {
            rateDAO.create(regionRates.get(i));
        }
        //</editor-fold>
 
        //<editor-fold defaultstate="collapsed" desc="VehicleRates">
        ArrayList<Rate> vehicleRates = new ArrayList();
 
        vehicleRates.add(new Rate("Unknown", RateType.VEHICLE, 1.0D));
        vehicleRates.add(new Rate("Passenger Car", RateType.VEHICLE, 1.0D));
        vehicleRates.add(new Rate("Van", RateType.VEHICLE, 1.1D));
        vehicleRates.add(new Rate("Autobus", RateType.VEHICLE, 1.3D));
        vehicleRates.add(new Rate("Truck", RateType.VEHICLE, 1.5D));
 
        for (int i = 0; i < vehicleRates.size(); i++) {
            rateDAO.create(vehicleRates.get(i));
        }
        //</editor-fold>
       
        //<editor-fold defaultstate="collapsed" desc="MassRate">
        Rate massRate = new Rate("Massa", RateType.MASS, 0.1D);
        rateDAO.create(massRate);
        //</editor-fold>
    }
    //</editor-fold>
}
