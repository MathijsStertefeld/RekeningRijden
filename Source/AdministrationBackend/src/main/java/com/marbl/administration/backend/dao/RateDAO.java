package com.marbl.administration.backend.dao;

import com.marbl.administration.domain.Rate;
import javax.ejb.Stateless;

@Stateless
public class RateDAO extends AbstractDAO<Rate, String> {

    public RateDAO() {
        super(Rate.class);
    }
}
