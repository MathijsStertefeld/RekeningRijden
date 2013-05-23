package com.marbl.administration.backend.service;

//<editor-fold defaultstate="collapsed" desc="Imports">
import com.marbl.administration.backend.dao.*;
import com.marbl.administration.domain.*;
import com.marbl.administration.domain.utils.Hasher;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import javax.annotation.PostConstruct;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.inject.Inject;
//</editor-fold>

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

    @PostConstruct
    public void postConstruct() {
        Hasher hasher = new Hasher("SHA-256", "UTF-8");
        
        //<editor-fold defaultstate="collapsed" desc="Employees">
        ArrayList<Employee> employees = new ArrayList<>();
        
        employees.add(new Employee("admin", hasher.hash("admin"), EmployeeGroup.ADMIN));
        employees.add(new Employee("henk", hasher.hash("henk"), EmployeeGroup.EMPLOYEE));
        employees.add(new Employee("truus", hasher.hash("truus"), EmployeeGroup.RATE_EMPLOYEE));
        
        for (int i = 0; i < employees.size(); i++) {
            employeeDAO.create(employees.get(i));
        }
        //</editor-fold>
        
        //<editor-fold defaultstate="collapsed" desc="Drivers">
        ArrayList<Driver> drivers = new ArrayList<>();
        
        drivers.add(new Driver(1111, "hans@hans.nl", hasher.hash("hans123"),
                "en", "Hans", "Hansen", "Eindhoven", "Hoofdstraat 1",
                "1234AA", new Date(), true, DriverGroup.ADMIN));
        drivers.add(new Driver(2222, "frank@frank.nl", hasher.hash("frank123"),
                "en", "Frank", "Franken", "Eindhoven", "Hoofdstraat 2",
                "1234BB", new Date(), true, DriverGroup.DRIVER));
        drivers.add(new Driver(3333, "tom@tom.nl", hasher.hash("tom123"),
                "en", "Tom", "Tommen", "Eindhoven", "Hoofdstraat 3",
                "1234CC", new Date(), true, DriverGroup.JAM_DRIVER));
        
        for (int i = 0; i < drivers.size(); i++) {
            driverDAO.create(drivers.get(i));
        }
        //</editor-fold>

        //<editor-fold defaultstate="collapsed" desc="Cars">
        ArrayList<Car> cars = new ArrayList<>();
        
        cars.add(new Car("t0", "AB-CD-12", CarType.AUTOBUS,
                PaintColor.BLACK, 1000, Classification.EEV,
                "Suzuki", "Swift", drivers.get(0).getBsn()));
        cars.add(new Car("t1", "EF-GH-34", CarType.AUTOBUS,
                PaintColor.RED, 800, Classification.EEV,
                "Fiat", "Panda", drivers.get(1).getBsn()));
        cars.add(new Car("t2", "IJ-KL-56", CarType.AUTOBUS,
                PaintColor.WHITE, 1200, Classification.EEV,
                "Volkswagen", "Golf", drivers.get(2).getBsn()));
        
        for (int i = 0; i < cars.size(); i++) {
            carDAO.create(cars.get(i));
        }
        //</editor-fold>
        
        //<editor-fold defaultstate="collapsed" desc="Bills">
        ArrayList<Bill> bills = new ArrayList<>();
        
        bills.add(new Bill(1L, new Date(), new Date(), 100, PaymentStatus.CANCELED,
                drivers.get(0).getBsn(), cars.get(0).getCarTrackerId()));
        bills.add(new Bill(2L, new Date(), new Date(), 100, PaymentStatus.PAID,
                drivers.get(1).getBsn(), cars.get(1).getCarTrackerId()));
        bills.add(new Bill(3L, new Date(), new Date(), 100, PaymentStatus.OPEN,
                drivers.get(2).getBsn(), cars.get(2).getCarTrackerId()));
        
        for (int i = 0; i < bills.size(); i++) {
            billDAO.create(bills.get(i));
        }
        //</editor-fold>
    }
}
