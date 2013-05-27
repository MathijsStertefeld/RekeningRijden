package com.marbl.administration.backend.dao;

//<editor-fold defaultstate="collapsed" desc="Imports">
import com.marbl.administration.domain.Driver;
import javax.ejb.Stateless;
//</editor-fold>

// Use this DAO to manipulate drivers in the database.

@Stateless
public class DriverDAO extends AbstractDAO<Driver, Integer> {

    //<editor-fold defaultstate="collapsed" desc="Constructors">
    public DriverDAO() {
        super(Driver.class);
    }
    //</editor-fold>
}
