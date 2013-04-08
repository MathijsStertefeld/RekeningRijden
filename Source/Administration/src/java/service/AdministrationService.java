package service;

import dao.*;
import domain.*;
import java.util.List;
import javax.ejb.Stateless;
import javax.inject.Inject;

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
    
    public List<WebsiteAccount> findAllWebsiteAccounts(){
        return websiteAccountDAO.findAll();
    }
    
    public WebsiteAccount findWebsiteAccount(int bsn){
        return websiteAccountDAO.find(bsn);
    }
    
    public void persist(AdministrationAccount administrationAccount) {
        administrationAccountDAO.persist(administrationAccount);
    }
    
    public void persist(WebsiteAccount websiteAccount) {
        websiteAccountDAO.persist(websiteAccount);
    }
    
    public void persist(Rate rate) {
        rateDAO.persist(rate);
    }
    
    public AdministrationAccount merge(AdministrationAccount administrationAccount) {
        return administrationAccountDAO.merge(administrationAccount);
    }
    
    public WebsiteAccount merge(WebsiteAccount websiteAccount) {
        return websiteAccountDAO.merge(websiteAccount);
    }
    
    public Rate merge(Rate rate) {
        return rateDAO.merge(rate);
    }
    
    public void remove(AdministrationAccount administrationAccount) {
        administrationAccountDAO.remove(administrationAccount);
    }
    
    public void remove(WebsiteAccount websiteAccount) {
        websiteAccountDAO.remove(websiteAccount);
    }
    
    public void remove(Rate rate) {
        rateDAO.persist(rate);
    }
}
