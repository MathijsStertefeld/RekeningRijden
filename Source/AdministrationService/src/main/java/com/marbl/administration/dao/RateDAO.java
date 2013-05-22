package com.marbl.administration.dao;

import com.marbl.administration.domain.Rate;
import javax.ejb.Stateless;

@Stateless
public class RateDAO extends AbstractDAO<Rate, String> {

    public RateDAO() {
        super(Rate.class);
    }
}
