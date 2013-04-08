package bean;

import domain.Bill;
import domain.WebsiteAccount;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import javax.enterprise.context.SessionScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.http.HttpServletRequest;
import service.AdministrationService;

@Named
@SessionScoped
public class BillBean implements Serializable {

    @Inject
    AdministrationService administrationService;
    
    private int bsn;
    private Collection<Bill> bills = new ArrayList<Bill>();

    public Collection<Bill> getBills() {
        return bills;
    }

    public int getBsn() {
        return bsn;
    }

    public void setBsn(int bsn) {
        this.bsn = bsn;
        
        WebsiteAccount websiteAccount = administrationService.findWebsiteAccount(bsn);
        bills = new ArrayList<Bill>();
        
        if (websiteAccount != null) {
            bills.addAll(websiteAccount.getAllBills());
        }
    }
        
//    public Collection<Bill> getBills() {
//        WebsiteAccount websiteAccount = administrationService.findWebsiteAccount(bsn);
//        Collection bills = new ArrayList<Bill>();
//        
//        if (websiteAccount != null) {
//            bills.addAll(websiteAccount.getAllBills());
//        }
//        
//        return bills;
//    }
}
