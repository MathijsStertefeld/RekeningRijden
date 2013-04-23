package administration.bean;

import administration.domain.Bill;
import administration.domain.Driver;
import administration.service.DriverService;
import java.io.Serializable;
import java.util.Collection;
import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;

@Named
@SessionScoped
public class AdminBean implements Serializable {

    @Inject
    private DriverService driverService;
    private int bsn;
    private Collection<Bill> bills;

    public int getBsn() {
        return bsn;
    }

    public void setBsn(int bsn) {
        this.bsn = bsn;

        Driver driver = driverService.find(bsn);
        if (driver != null) {
            bills = driver.getBills();
        }
    }
    
    public Collection<Bill> getBills() {
        return bills;
    }
}
