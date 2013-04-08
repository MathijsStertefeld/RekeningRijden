package administration.service;

import administration.dao.*;
import administration.domain.*;
import java.util.Collection;
import javax.ejb.EJB;
import javax.ejb.Stateless;

@Stateless
public class AdministrationService {

    @EJB
    private DriverDAO driverDAO;
    @EJB
    private EmployeeDAO employeeDAO;
    @EJB
    private RateDAO rateDAO;
    
    public Collection<Driver> findAllDrivers(){
        return driverDAO.findAll();
    }
    
    public Collection<Employee> findAllEmployees() {
        return employeeDAO.findAll();
    }
    
    public Collection<Rate> findAllRates(){
        return rateDAO.findAll();
    }
    
    public Driver findDriver(int bsn){
        return driverDAO.find(bsn);
    }
    
    public Employee findEmployee(String username) {
        return employeeDAO.find(username);
    }
    
    public Rate findRate(String name) {
        return rateDAO.find(name);
    }
    
    public void persist(Employee employee) {
        employeeDAO.persist(employee);
    }
    
    public void persist(Driver driver) {
        driverDAO.persist(driver);
    }
    
    public void persist(Rate rate) {
        rateDAO.persist(rate);
    }
    
    public Employee merge(Employee employee) {
        return employeeDAO.merge(employee);
    }
    
    public Driver merge(Driver driver) {
        return driverDAO.merge(driver);
    }
    
    public Rate merge(Rate rate) {
        return rateDAO.merge(rate);
    }
    
    public void remove(Employee employee) {
        employeeDAO.remove(employee);
    }
    
    public void remove(Driver driver) {
        driverDAO.remove(driver);
    }
    
    public void remove(Rate rate) {
        rateDAO.persist(rate);
    }
}
