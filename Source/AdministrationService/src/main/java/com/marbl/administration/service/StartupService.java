package com.marbl.administration.service;

import com.marbl.administration.dao.DriverDAO;
import com.marbl.administration.dao.EmployeeDAO;
import com.marbl.administration.domain.Bill;
import com.marbl.administration.domain.Car;
import com.marbl.administration.domain.CarType;
import com.marbl.administration.domain.Classification;
import com.marbl.administration.domain.Driver;
import com.marbl.administration.domain.DriverGroup;
import com.marbl.administration.domain.Employee;
import com.marbl.administration.domain.EmployeeGroup;
import com.marbl.administration.domain.PaintColor;
import com.marbl.administration.domain.PaymentStatus;
import java.io.Serializable;
import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
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
    private DriverDAO driverDAO;
    @Inject
    private EmployeeDAO employeeDAO;

    @PostConstruct
    public void postConstruct() {
        Driver d1 = new Driver(1111, "hans@hans.nl", hash("hans123"), "en", "Hans",
                "Hansen", "Eindhoven", "Hoofdstraat 1", "1234AA", new Date(), true);
        Driver d2 = new Driver(2222, "frank@frank.nl", hash("frank123"), "en", "Frank",
                "Franken", "Eindhoven", "Hoofdstraat 2", "1234BB", new Date(), true);
        Driver d3 = new Driver(3333, "tom@tom.nl", hash("tom123"), "en", "Tom",
                "Tommen", "Eindhoven", "Hoofdstraat 3", "1234CC", new Date(), true);
        
        driverDAO.create(d1);
        driverDAO.create(d2);
        driverDAO.create(d3);
        
        d1.getBills().add(new Bill(1L, new Date(), new Date(), 1000, PaymentStatus.CANCELED));
        d2.getBills().add(new Bill(2L, new Date(), new Date(), 1000, PaymentStatus.PAID));
        d3.getBills().add(new Bill(3L, new Date(), new Date(), 1000, PaymentStatus.OPEN));
        
        d1.getCars().add(new Car("AB-CD-12", "ABCD", CarType.AUTOBUS,
                PaintColor.BLACK, 1000, Classification.EEV, "Suzuki", "Swift"));
        d2.getCars().add(new Car("EF-GH-34", "EFGH", CarType.AUTOBUS,
                PaintColor.RED, 800, Classification.EEV, "Fiat", "Panda"));
        d3.getCars().add(new Car("IJ-KL-56", "IJKL", CarType.AUTOBUS,
                PaintColor.WHITE, 1200, Classification.EEV, "Volkswagen", "Golf"));
        
        d1.getGroups().add(DriverGroup.ADMIN);
        d2.getGroups().add(DriverGroup.JAM_DRIVER);
        d3.getGroups().add(DriverGroup.DRIVER);
        
        driverDAO.edit(d1);
        driverDAO.edit(d2);
        driverDAO.edit(d3);
        
        Employee e1 = new Employee("admin", hash("admin"));
        Employee e2 = new Employee("henk", hash("henk"));
        Employee e3 = new Employee("truus", hash("truus"));
        
        employeeDAO.create(e1);
        employeeDAO.create(e2);
        employeeDAO.create(e3);
        
        e1.getGroups().add(EmployeeGroup.ADMIN);
        e2.getGroups().add(EmployeeGroup.RATE_EMPLOYEE);
        e3.getGroups().add(EmployeeGroup.EMPLOYEE);
        
        employeeDAO.edit(e1);
        employeeDAO.edit(e2);
        employeeDAO.edit(e3);
    }
    
    private String hash(String text) {
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
