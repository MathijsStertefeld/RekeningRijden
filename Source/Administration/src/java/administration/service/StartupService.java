package administration.service;

import administration.bean.LoginBean;
import administration.dao.*;
import administration.domain.*;
import administration.security.PasswordHasher;
import java.io.Serializable;
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Date;
import java.util.Random;
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
        SecurityGroup admin = new SecurityGroup("ADMIN");
        SecurityGroup driver = new SecurityGroup("DRIVER");
        SecurityGroup employee = new SecurityGroup("EMPLOYEE");
        
        Employee e1 = new Employee("admin", "admin", true);
        Employee e2 = new Employee("Henk", "henk123", true);
        Employee e3 = new Employee("Truus", "truus123", false);
        
        e1.getSecurityGroups().add(admin);
        e1.getSecurityGroups().add(employee);
        e1.getSecurityGroups().add(driver);
        e2.getSecurityGroups().add(employee);
        e3.getSecurityGroups().add(employee);
        
        employeeDAO.create(e1);
        employeeDAO.create(e2);
        employeeDAO.create(e3);
        
        
        Driver d1 = new Driver(1111, "hans@hans.nl", "hans123", "en", "Hans",
                "Hansen", "Eindhoven", "Hoofdstraat 1", "1234AA", new Date());
        Driver d2 = new Driver(2222, "frank@frank.nl", "frank123", "en", "Frank",
                "Franken", "Eindhoven", "Hoofdstraat 2", "1234BB", new Date());
        Driver d3 = new Driver(3333, "tom@tom.nl", "tom123", "en", "Tom",
                "Tommen", "Eindhoven", "Hoofdstraat 3", "1234CC", new Date());
        Driver d4 = new Driver(4444, "sjaak@sjaak.nl", "sjaak123", "en", "Sjaak",
                "Sjaken", "Eindhoven", "Hoofdstraat 4", "1234DD", new Date());
        
        d1.getBills().add(new Bill(1L, new Date(), new Date(), 1000, PaymentStatus.CANCELED));
        d1.getBills().add(new Bill(2L, new Date(), new Date(), 1000, PaymentStatus.PAID));
        d4.getBills().add(new Bill(3L, new Date(), new Date(), 1000, PaymentStatus.OPEN));
        
        d1.getCars().add(new Car("AB-CD-12", "ABCD", CarType.AUTOBUS, PaintColor.BLACK, 1000,
                Classification.EEV, "Suzuki", "Swift"));
        d2.getCars().add(new Car("EF-GH-34", "EFGH", CarType.AUTOBUS, PaintColor.RED, 800,
                Classification.EEV, "Fiat", "Panda"));
        d3.getCars().add(new Car("IJ-KL-56", "IJKL", CarType.AUTOBUS, PaintColor.WHITE, 1200,
                Classification.EEV, "Volkswagen", "Golf"));
        
        driverDAO.create(d1);
        driverDAO.create(d2);
        driverDAO.create(d3);
        driverDAO.create(d4);
    }
}
