package com.marbl.administration.backend.dao;

//<editor-fold defaultstate="collapsed" desc="Imports">
import com.marbl.administration.domain.Employee;
import javax.ejb.Stateless;
//</editor-fold>

// Use this DAO to manipulate employees in the database.

@Stateless
public class EmployeeDAO extends AbstractDAO<Employee, String> {
    
    //<editor-fold defaultstate="collapsed" desc="Constructors">
    public EmployeeDAO() {
        super(Employee.class);
    }
    //</editor-fold>
}
