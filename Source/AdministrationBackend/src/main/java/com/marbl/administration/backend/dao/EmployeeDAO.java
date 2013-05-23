package com.marbl.administration.backend.dao;

import com.marbl.administration.domain.Employee;
import javax.ejb.Stateless;

@Stateless
public class EmployeeDAO extends AbstractDAO<Employee, String> {
    
    public EmployeeDAO() {
        super(Employee.class);
    }
}