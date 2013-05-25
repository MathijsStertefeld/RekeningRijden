package com.marbl.administration.backend.service;

import com.marbl.administration.backend.dao.*;
import com.marbl.administration.domain.*;
import com.marbl.administration.domain.utils.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.Random;
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
    private int testBsn;
    private Hasher hasher;
    private Random random;

    @PostConstruct
    public void postConstruct() {
        hasher = new Hasher("SHA-256", "UTF-8");
        testBsn = 900000000;
        random = new Random();

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

        drivers.add(generateDriver("Hans", DriverGroup.ADMIN));
        drivers.add(generateDriver("Frank", DriverGroup.DRIVER));
        drivers.add(generateDriver("Tom", DriverGroup.DRIVER));

        for (int i = 0; i < drivers.size(); i++) {
            driverDAO.create(drivers.get(i));
        }
        //</editor-fold>

        //<editor-fold defaultstate="collapsed" desc="Cars">
        ArrayList<Car> cars = new ArrayList<>();

        cars.add(new Car("t0", "AB-CD-12", CarType.PASSENGER_CAR,
                PaintColor.BLACK, 1000, Classification.ZERO,
                "Suzuki", "Swift", drivers.get(0).getBsn()));
        cars.add(new Car("t1", "EF-GH-34", CarType.PASSENGER_CAR,
                PaintColor.RED, 800, Classification.ONE,
                "Fiat", "Panda", drivers.get(0).getBsn()));
        cars.add(new Car("t2", "IJ-KL-56", CarType.PASSENGER_CAR,
                PaintColor.GRAY, 1200, Classification.TWO,
                "Volkswagen", "Golf", drivers.get(1).getBsn()));
        cars.add(new Car("t3", "MN-OP-78", CarType.AUTOBUS,
                PaintColor.WHITE, 1200, Classification.THREE,
                "Mitsubishi", "VAN", drivers.get(2).getBsn()));

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

        //<editor-fold defaultstate="collapsed" desc="CityRates">
        ArrayList<CityRate> cityRates = new ArrayList<>();

        cityRates.add(new CityRate("Den Bosch", 0.12f));
        cityRates.add(new CityRate("Eindhoven", 0.10f));
        cityRates.add(new CityRate("Tilburg", 0.10f));

        for (int i = 0; i < cityRates.size(); i++) {
            rateDAO.create(cityRates.get(i));
        }
        //</editor-fold>

        //<editor-fold defaultstate="collapsed" desc="HighwayRates">
        ArrayList<HighwayRate> highwayRates = new ArrayList<>();

        highwayRates.add(new HighwayRate("A2", new double[]{0.04, 0.04, 0.04, 0.04, 0.04, 0.04, 0.04, 0.10, 0.10, 0.10, 0.04, 0.04, 0.04, 0.04, 0.04, 0.04, 0.04, 0.10, 0.10, 0.10, 0.04, 0.04, 0.04, 0.04}));
        highwayRates.add(new HighwayRate("A50", new double[]{0.02, 0.02, 0.02, 0.02, 0.02, 0.02, 0.02, 0.08, 0.08, 0.08, 0.02, 0.02, 0.02, 0.02, 0.02, 0.02, 0.02, 0.08, 0.08, 0.08, 0.02, 0.02, 0.02, 0.02}));
        highwayRates.add(new HighwayRate("A67", new double[]{0.02, 0.02, 0.02, 0.02, 0.02, 0.02, 0.02, 0.08, 0.08, 0.08, 0.02, 0.02, 0.02, 0.02, 0.02, 0.02, 0.02, 0.08, 0.08, 0.08, 0.02, 0.02, 0.02, 0.02}));
        highwayRates.add(new HighwayRate("A270", new double[]{0.02, 0.02, 0.02, 0.02, 0.02, 0.02, 0.02, 0.02, 0.02, 0.02, 0.02, 0.02, 0.02, 0.02, 0.02, 0.02, 0.02, 0.02, 0.02, 0.02, 0.02, 0.02, 0.02, 0.02}));

        for (int i = 0; i < highwayRates.size(); i++) {
            rateDAO.create(highwayRates.get(i));
        }
        //</editor-fold>

        //<editor-fold defaultstate="collapsed" desc="RegionRates">
        ArrayList<RegionRate> regionRates = new ArrayList<>();

        regionRates.add(new RegionRate("Regio Den Bosch", 0.05f));
        regionRates.add(new RegionRate("Regio Eindhoven", 0.05f));
        regionRates.add(new RegionRate("Regio Helmond", 0.02f));
        regionRates.add(new RegionRate("Regio Tilburg", 0.05f));
        regionRates.add(new RegionRate("Regio Weert", 0.02f));

        for (int i = 0; i < regionRates.size(); i++) {
            rateDAO.create(regionRates.get(i));
        }
        //</editor-fold>

        //<editor-fold defaultstate="collapsed" desc="VehicleRates">
        ArrayList<VehicleRate> vehicleRates = new ArrayList<>();

        vehicleRates.add(new VehicleRate("Autobus", 0.2f));

        for (int i = 0; i < vehicleRates.size(); i++) {
            rateDAO.create(vehicleRates.get(i));
        }
        //</editor-fold>
    }

    private Driver generateDriver(String name, DriverGroup group) {
        String lower = name.toLowerCase();
        String upper = name.substring(0, 1).toUpperCase() + name.substring(1);

        int bsn = testBsn++;
        String email = lower + "@marbl.com";
        String password = hasher.hash(lower + "123");
        String firstName = upper;
        String lastName = "van der " + upper;
        String residence = new String[]{"Den Bosch", "Eindhoven", "Weert"}[bsn % 3];
        String address = new String[]{"Hoofdstraat ", "Dorpstraat "}[bsn % 2] + bsn % 900000000;
        String zipCode = new String[]{"1234AB", "2345BC", "3456CD", "4567DE", "5678EF", "6789FG"}[bsn % 6];
        Date dateOfBirth = new Date();

        return new Driver(bsn, email, password, firstName, lastName, residence, address, zipCode, dateOfBirth, true, group);
    }
}
