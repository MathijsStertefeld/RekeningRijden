package service;

import domain.AdministrationAccount;
import domain.Bill;
import domain.PaymentStatus;
import domain.WebsiteAccount;
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
        AdministrationAccount henk = new AdministrationAccount("Henk", "henk123", false);
        AdministrationAccount truus = new AdministrationAccount("Truus", "truus123", true);
        
        administrationService.persist(henk);
        administrationService.persist(truus);
        
        WebsiteAccount hans = new WebsiteAccount(1111, "hans@hans.nl", "hans123", "en", "Hans",
                "Hansen", "Eindhoven", "Hoofdstraat 1", "1234AA", new Date());
        WebsiteAccount frank = new WebsiteAccount(2222, "frank@frank.nl", "frank123", "en", "Frank",
                "Franken", "Eindhoven", "Hoofdstraat 2", "1234BB", new Date());
        WebsiteAccount tom = new WebsiteAccount(3333, "tom@tom.nl", "tom123", "en", "Tom",
                "Tommen", "Eindhoven", "Hoofdstraat 3", "1234CC", new Date());
        WebsiteAccount sjaak = new WebsiteAccount(4444, "sjaak@sjaak.nl", "sjaak123", "en", "Sjaak",
                "Sjaken", "Eindhoven", "Hoofdstraat 4", "1234DD", new Date());
        
        hans.addBill(new Bill(1, new Date(1), 1000, PaymentStatus.CANCELED));
        hans.addBill(new Bill(2, new Date(2), 1000, PaymentStatus.PAYED));
        hans.addBill(new Bill(3, new Date(3), 1000, PaymentStatus.OPEN));
        
        administrationService.persist(hans);
        administrationService.persist(frank);
        administrationService.persist(tom);
        administrationService.persist(sjaak);
    }
}
