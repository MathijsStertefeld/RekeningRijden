package bean;

import administration.domain.Bill;
import administration.domain.Driver;
import administration.domain.PaymentStatus;
import administration.service.AdministrationService;
import java.io.Serializable;
import java.util.Collection;
import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;

@Named
@SessionScoped
public class BillBean implements Serializable {

    @Inject
    AdministrationService administrationService;
    private int currentBsn;
    private Collection<Bill> bills;

    public int getCurrentBsn() {
        return currentBsn;
    }

    public void setCurrentBsn(int bsn) {
        this.currentBsn = bsn;

        Driver driver = administrationService.findDriver(bsn);
        if (driver != null) {
            bills = driver.getBills();
        }
    }
    
    public Collection<Bill> getBills() {
        return bills;
    }
    
    public void changeBillStatus(int id, int status)
    {   
        Driver driver = administrationService.findDriver(currentBsn);
        
        Bill b = driver.getBill(id);
        
        b.setPaymentStatus(PaymentStatus.values()[status]);
        
        if (driver != null) {
            bills = driver.getBills();
        }
    }
    
    public void save()
    {
        
    }
    
    public void edit()
    {
        
    }
}
