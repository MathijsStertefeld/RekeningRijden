package com.marbl.administration.backend.dao;

import com.marbl.administration.domain.Driver;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;

@Stateless
public class DriverDAO extends AbstractDAO<Driver, Integer> {

    public DriverDAO() {
        super(Driver.class);
    }
}
