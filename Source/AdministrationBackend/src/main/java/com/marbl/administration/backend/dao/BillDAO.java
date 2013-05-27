package com.marbl.administration.backend.dao;

//<editor-fold defaultstate="collapsed" desc="Imports">
import com.marbl.administration.domain.Bill;
import javax.ejb.Stateless;
//</editor-fold>

// Use this DAO to manipulate bills in the database.

@Stateless
public class BillDAO extends AbstractDAO<Bill, Long> {

    //<editor-fold defaultstate="collapsed" desc="Constructors">
    public BillDAO() {
        super(Bill.class);
    }
    //</editor-fold>
}
