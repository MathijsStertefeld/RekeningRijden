package com.marbl.administration.backend.service;

import com.marbl.administration.backend.dao.*;
import com.marbl.administration.domain.*;
import java.io.Serializable;
import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.inject.Inject;

@Singleton
@Startup
public class StartupService implements Serializable {

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

    @PostConstruct
    public void postConstruct() {
        //<editor-fold defaultstate="collapsed" desc="Bills">
        ArrayList<Bill> bills = new ArrayList<Bill>();
        bills.add(new Bill(1L, new Date(), new Date(), 1000, PaymentStatus.CANCELED));
        bills.add(new Bill(2L, new Date(), new Date(), 1000, PaymentStatus.PAID));
        bills.add(new Bill(3L, new Date(), new Date(), 1000, PaymentStatus.OPEN));
        //</editor-fold>

        //<editor-fold defaultstate="collapsed" desc="Cars">
        ArrayList<Car> cars = new ArrayList<Car>();
        cars.add(new Car("AB-CD-12", "ABCD", CarType.AUTOBUS,
                PaintColor.BLACK, 1000, Classification.EEV, "Suzuki", "Swift"));
        cars.add(new Car("EF-GH-34", "EFGH", CarType.AUTOBUS,
                PaintColor.RED, 800, Classification.EEV, "Fiat", "Panda"));
        cars.add(new Car("IJ-KL-56", "IJKL", CarType.AUTOBUS,
                PaintColor.WHITE, 1200, Classification.EEV, "Volkswagen", "Golf"));
        //</editor-fold>

        //<editor-fold defaultstate="collapsed" desc="Drivers">
        ArrayList<Driver> drivers = new ArrayList<Driver>();
        drivers.add(new Driver(1111, "hans@hans.nl", hash("hans123"), "en", "Hans",
                "Hansen", "Eindhoven", "Hoofdstraat 1", "1234AA", new Date(), true));
        drivers.add(new Driver(2222, "frank@frank.nl", hash("frank123"), "en", "Frank",
                "Franken", "Eindhoven", "Hoofdstraat 2", "1234BB", new Date(), true));
        drivers.add(new Driver(3333, "tom@tom.nl", hash("tom123"), "en", "Tom",
                "Tommen", "Eindhoven", "Hoofdstraat 3", "1234CC", new Date(), true));
        //</editor-fold>

        //<editor-fold defaultstate="collapsed" desc="Employee">
        ArrayList<Employee> employees = new ArrayList<Employee>();
        employees.add(new Employee("admin", hash("admin")));
        employees.add(new Employee("henk", hash("henk")));
        employees.add(new Employee("truus", hash("truus")));
        //</editor-fold>

        //<editor-fold defaultstate="collapsed" desc="Create">
        for (int i = 0; i < bills.size(); i++) {
            billDAO.create(bills.get(i));
        }
        
        for (int i = 0; i < cars.size(); i++) {
            carDAO.create(cars.get(i));
        }
        
        for (int i = 0; i < drivers.size(); i++) {
            driverDAO.create(drivers.get(i));
        }
        
        for (int i = 0; i < employees.size(); i++) {
            employeeDAO.create(employees.get(i));
        }
        //</editor-fold>

        //<editor-fold defaultstate="collapsed" desc="Relations">
        addBillToDriver(drivers.get(0), bills.get(0));
        addBillToDriver(drivers.get(1), bills.get(1));
        addBillToDriver(drivers.get(2), bills.get(2));
 
        addCarToDriver(drivers.get(0), cars.get(0));
        addCarToDriver(drivers.get(1), cars.get(1));
        addCarToDriver(drivers.get(2), cars.get(2));

        addGroupToDriver(drivers.get(0), DriverGroup.ADMIN);
        addGroupToDriver(drivers.get(1), DriverGroup.JAM_DRIVER);
        addGroupToDriver(drivers.get(2), DriverGroup.DRIVER);

        addGroupToEmployee(employees.get(0), EmployeeGroup.ADMIN);
        addGroupToEmployee(employees.get(1), EmployeeGroup.RATE_EMPLOYEE);
        addGroupToEmployee(employees.get(2), EmployeeGroup.EMPLOYEE);
        //</editor-fold>

        //<editor-fold defaultstate="collapsed" desc="Edit">
        for (int i = 0; i < bills.size(); i++) {
            billDAO.edit(bills.get(i));
        }
        
        for (int i = 0; i < cars.size(); i++) {
            carDAO.edit(cars.get(i));
        }
        
        for (int i = 0; i < drivers.size(); i++) {
            driverDAO.edit(drivers.get(i));
        }
        
        for (int i = 0; i < employees.size(); i++) {
            employeeDAO.edit(employees.get(i));
        }
        //</editor-fold>
    }
    
    public void addBillToDriver(Driver driver, Bill bill) {
        driver.getBillIds().add(bill.getId());
        bill.setDriverBsn(driver.getBsn());
    }
    
    public void addCarToDriver(Driver driver, Car car) {
        driver.getCarLicensePlates().add(car.getLicensePlate());
        
        if (car.getDriverBsn() != 0) {
            car.getDriverHistory().add(car.getDriverBsn());
        }
        
        if (car.getDriverHistory().contains(driver.getBsn())) {
            car.getDriverHistory().remove(driver.getBsn());
        }
        
        car.setDriverBsn(driver.getBsn());
    }
    
    public void addGroupToDriver(Driver driver, DriverGroup group) {
        driver.getGroups().add(group);
    }
    
    public void addGroupToEmployee(Employee employee, EmployeeGroup group) {
        employee.getGroups().add(group);
    }

    public String hash(String text) {
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            md.update(text.getBytes("UTF-8"));
            byte[] digest = md.digest();
            BigInteger bi = new BigInteger(1, digest);
            text = String.format("%0" + (digest.length << 1) + "X", bi);
        } catch (UnsupportedEncodingException ex) {
            Logger.getLogger(StartupService.class.getName()).log(Level.SEVERE, null, ex);
        } catch (NoSuchAlgorithmException ex) {
            Logger.getLogger(StartupService.class.getName()).log(Level.SEVERE, null, ex);
        }

        return text;
    }
}
