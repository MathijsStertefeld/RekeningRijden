package com.marbl.administration.dao;

import com.marbl.administration.domain.Car;
import javax.ejb.Stateless;

@Stateless
public class CarDAO extends AbstractDAO<Car, String> {

    public CarDAO() {
        super(Car.class);
    }
}
