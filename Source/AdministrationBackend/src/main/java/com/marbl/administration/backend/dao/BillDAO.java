package com.marbl.administration.backend.dao;

import com.marbl.administration.domain.Bill;
import javax.ejb.Stateless;

@Stateless
public class BillDAO extends AbstractDAO<Bill, Long> {

    public BillDAO() {
        super(Bill.class);
    }
}
