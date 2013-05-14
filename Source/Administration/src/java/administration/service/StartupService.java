package administration.service;

import administration.dao.*;
import administration.domain.*;
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
        Employee e1 = new Employee("admin", hash("admin"));
        Employee e2 = new Employee("henk", hash("henk"));
        Employee e3 = new Employee("truus", hash("truus"));
        
        employeeDAO.create(e1);
        employeeDAO.create(e2);
        employeeDAO.create(e3);
        
        e1.getGroups().add(GroupName.ADMIN);
        e2.getGroups().add(GroupName.RATE_EMPLOYEE);
        e3.getGroups().add(GroupName.EMPLOYEE);
        
        employeeDAO.edit(e1);
        employeeDAO.edit(e2);
        employeeDAO.edit(e3);
        
        Driver d1 = new Driver(1111, "hans@hans.nl", "hans123", "en", "Hans",
                "Hansen", "Eindhoven", "Hoofdstraat 1", "1234AA", new Date());
        Driver d2 = new Driver(2222, "frank@frank.nl", "frank123", "en", "Frank",
                "Franken", "Eindhoven", "Hoofdstraat 2", "1234BB", new Date());
        Driver d3 = new Driver(3333, "tom@tom.nl", "tom123", "en", "Tom",
                "Tommen", "Eindhoven", "Hoofdstraat 3", "1234CC", new Date());
        
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
        
        driverDAO.edit(d1);
        driverDAO.edit(d2);
        driverDAO.edit(d3);
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
