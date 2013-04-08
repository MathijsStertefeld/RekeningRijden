package administration.service;

import administration.domain.*;
import java.util.Date;
import javax.annotation.PostConstruct;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.inject.Inject;

@Singleton
@Startup
public class StartupService {

    @Inject
    AdministrationService administrationService;

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
        
        administrationService.persist(e2);
        administrationService.persist(e3);
        
        Driver hans = new Driver(1111, "hans@hans.nl", "hans123", "en", "Hans",
                "Hansen", "Eindhoven", "Hoofdstraat 1", "1234AA", new Date());
        Driver frank = new Driver(2222, "frank@frank.nl", "frank123", "en", "Frank",
                "Franken", "Eindhoven", "Hoofdstraat 2", "1234BB", new Date());
        Driver tom = new Driver(3333, "tom@tom.nl", "tom123", "en", "Tom",
                "Tommen", "Eindhoven", "Hoofdstraat 3", "1234CC", new Date());
        Driver sjaak = new Driver(4444, "sjaak@sjaak.nl", "sjaak123", "en", "Sjaak",
                "Sjaken", "Eindhoven", "Hoofdstraat 4", "1234DD", new Date());
        
        hans.getBills().add(new Bill(1, new Date(), 1000, PaymentStatus.CANCELED));
        hans.getBills().add(new Bill(2, new Date(), 1000, PaymentStatus.PAID));
        hans.getBills().add(new Bill(3, new Date(), 1000, PaymentStatus.OPEN));
        
        administrationService.persist(hans);
        administrationService.persist(frank);
        administrationService.persist(tom);
        administrationService.persist(sjaak);
    }
}
