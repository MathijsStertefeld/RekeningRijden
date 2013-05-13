package bean;

import administration.domain.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;
import service.RekeningrijdersService;

@Named
@SessionScoped
public class BillBean implements Serializable {

    private Long billID;
    private Bill bill;
    private Collection<Bill> bills;
    private int bsn;
  
    @Inject
    RekeningrijdersService service;

    public int getBsn(){
        return bsn;
    }
    
    public void setBsn(int i) {
        System.err.println("Test" + i);
        bsn = i;
        bills = service.getBillsFromDriver(bsn);
        System.err.println(bills.size());
    }
    
    public Long getBillID() {
        return billID;
    }
    
    public void setBillID(Long id){
        this.billID = id;
        
        for(Bill b : bills)
        {
            if(b.getId() == id)
            {
                bill = b;
            }
        }
    }
    
    public Bill getBill()
    {
        return bill;
    }
      
    public Collection<Bill> getBills() {
        return bills;
    }
    
    public Collection<Object> getMovements() {
        ArrayList<Object> movements = new ArrayList<Object>();
        
        if (bill != null) {
            movements.addAll(bill.getMovements());
        }
        
        return movements;
    }
    
    public void payBill(Long billID)
    {
        //Ga naar externe payservice
        
        //if succesvol gelukt bij paypal
        //service.payBill(billID);
    }
    
}
