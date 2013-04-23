package administration.dao;

import administration.domain.Employee;
import javax.ejb.Stateless;

@Stateless
public class EmployeeDAO extends AbstractDAO<Employee, String> {
    
    public EmployeeDAO() {
        super(Employee.class);
    }
}
