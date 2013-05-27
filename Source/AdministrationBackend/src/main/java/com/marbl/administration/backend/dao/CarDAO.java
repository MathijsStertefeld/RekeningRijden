package com.marbl.administration.backend.dao;

//<editor-fold defaultstate="collapsed" desc="Imports">
import com.marbl.administration.domain.Car;
import javax.ejb.Stateless;
//</editor-fold>

// Use this DAO to manipulate cars in the database.

@Stateless
public class CarDAO extends AbstractDAO<Car, String> {

    //<editor-fold defaultstate="collapsed" desc="Constructors">
    public CarDAO() {
        super(Car.class);
    }
    //</editor-fold>
}
