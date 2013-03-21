package service;

import dao.*;
import domain.*;
import java.sql.Date;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.ejb.Stateless;
import javax.inject.Inject;

@Singleton
@Startup
@Stateless
public class AdministrationService {

    @Inject
    private AdministrationAccountDAO administrationAccountDAO;
    @Inject
    private WebsiteAccountDAO websiteAccountDAO;
    @Inject
    private RateDAO rateDAO;
    
    public List<AdministrationAccount> findAllAdministrationAccounts() {
        return administrationAccountDAO.findAll();
    }

    @PostConstruct
    public void initAccounts() {
        AdministrationAccount henk = new AdministrationAccount("Henk", "henk123", false);
        AdministrationAccount truus = new AdministrationAccount("Truus", "truus123", true);
        
        administrationAccountDAO.insert(henk);
        administrationAccountDAO.insert(truus);
        
        WebsiteAccount hans = new WebsiteAccount(1111, "hans@hans.nl", "hans123", "en", "Hans",
                "Hansen", "Eindhoven", "Hoofdstraat 1", "1234AA", new Date(11));
        WebsiteAccount frank = new WebsiteAccount(2222, "frank@frank.nl", "frank123", "en", "Frank",
                "Franken", "Eindhoven", "Hoofdstraat 2", "1234BB", new Date(22));
        WebsiteAccount tom = new WebsiteAccount(3333, "tom@tom.nl", "tom123", "en", "Tom",
                "Tommen", "Eindhoven", "Hoofdstraat 3", "1234CC", new Date(33));
        WebsiteAccount sjaak = new WebsiteAccount(4444, "sjaak@sjaak.nl", "sjaak123", "en", "Sjaak",
                "Sjaken", "Eindhoven", "Hoofdstraat 4", "1234DD", new Date(44));
        
        hans.addBill(new Bill(1, new Date(1), 1000, PaymentStatus.CANCELED));
        hans.addBill(new Bill(2, new Date(2), 1000, PaymentStatus.PAYED));
        hans.addBill(new Bill(3, new Date(3), 1000, PaymentStatus.OPEN));
        
        websiteAccountDAO.insert(hans);
        websiteAccountDAO.insert(frank);
        websiteAccountDAO.insert(tom);
        websiteAccountDAO.insert(sjaak);
    }
}
