package bean;

import domain.Bill;
import domain.WebsiteAccount;
import java.util.Collection;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;
import service.AdministrationService;

@Named
@RequestScoped
public class BillBean {

    @Inject
    AdministrationService administrationService;
        
    public Collection<Bill> getBills(int bsn)
    {
        WebsiteAccount websiteAccount = administrationService.findWebsiteAccount(bsn);
        Collection bills = websiteAccount.getAllBills();
        
        return bills;
    }
}
