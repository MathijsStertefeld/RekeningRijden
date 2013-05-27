package com.marbl.administration.backend.dao;

//<editor-fold defaultstate="collapsed" desc="Imports">
import com.marbl.administration.domain.Rate;
import javax.ejb.Stateless;
//</editor-fold>

// Use this DAO to manipulate bills in the rates.

@Stateless
public class RateDAO extends AbstractDAO<Rate, String> {

    //<editor-fold defaultstate="collapsed" desc="Constructors">
    public RateDAO() {
        super(Rate.class);
    }
    //</editor-fold>
}
