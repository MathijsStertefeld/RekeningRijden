package administration.dao;

import administration.domain.Employee;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.*;

@Stateless
public class EmployeeDAOJPA implements EmployeeDAO {

    @PersistenceContext
    private EntityManager em;

    @Override
    public void persist(Employee employee) {
        if (employee != null && find(employee.getName()) == null) {
            em.persist(employee);
        }
    }

    @Override
    public Employee merge(Employee employee) {
        if (employee != null && find(employee.getName()) != null) {
            employee = em.merge(employee);
        }
        return employee;
    }

    @Override
    public void remove(Employee employee) {
        if (employee != null && find(employee.getName()) != null) {
            em.remove(employee);
        }
    }

    @Override
    public List<Employee> findAll() {
        String s = "SELECT e FROM Employee e";
        TypedQuery<Employee> query = em.createQuery(s, Employee.class);
        return query.getResultList();
    }

    @Override
    public Employee find(String username) {
        String s = "SELECT e FROM Employee e WHERE e.username = '" + username + "'";
        TypedQuery<Employee> query = em.createQuery(s, Employee.class);
        
        try {
            return query.getSingleResult();
        } catch (NoResultException ex) {
            return null;
        }
    }
}
