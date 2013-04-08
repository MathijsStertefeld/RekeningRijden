/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package bean;

import domain.Bill;
import domain.WebsiteAccount;
import java.util.Collection;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import service.AdministrationService;

/**
 *
 * @author Bas
 */
@ManagedBean
@RequestScoped
public class BillBean {

    @EJB
    AdministrationService administrationService;
        
    public Collection<Bill> getBills(int bsn)
    {
        WebsiteAccount websiteAccount = administrationService.findWebsiteAccount(bsn);
        Collection bills = websiteAccount.getAllBills();
        
        return bills;
    }
}
