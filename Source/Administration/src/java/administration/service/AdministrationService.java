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
    
    public void create(Driver driver) {
        driverDAO.create(driver);
    }
    
    public void create(Employee employee) {
        employeeDAO.create(employee);
    }
    
    public void create(Rate rate) {
        rateDAO.create(rate);
    }
    
    public Driver edit(Driver driver) {
        return driverDAO.edit(driver);
    }
    
    public Employee edit(Employee employee) {
        return employeeDAO.edit(employee);
    }
    
    public Rate edit(Rate rate) {
        return rateDAO.edit(rate);
    }
    
    public void remove(Driver driver) {
        driverDAO.remove(driver);
    }
    
    public void remove(Employee employee) {
        employeeDAO.remove(employee);
    }
    
    public void remove(Rate rate) {
        rateDAO.remove(rate);
    }
}
