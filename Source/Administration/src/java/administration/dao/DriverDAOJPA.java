package administration.dao;

import administration.domain.Driver;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.*;

@Stateless
public class DriverDAOJPA implements DriverDAO {

    @PersistenceContext
    private EntityManager em;

    @Override
    public void persist(Driver driver) {
        if (driver != null && find(driver.getBsn()) == null) {
            em.persist(driver);
        }
    }

    @Override
    public Driver merge(Driver driver) {
        if (driver != null && find(driver.getBsn()) != null) {
            driver = em.merge(driver);
        }
        
        return driver;
    }

    @Override
    public void remove(Driver driver) {
        if (driver != null && find(driver.getBsn()) != null) {
            em.remove(driver);
        }
    }

    @Override
    public List<Driver> findAll() {
        String s = "SELECT d FROM Driver d";
        TypedQuery<Driver> query = em.createQuery(s, Driver.class);
        return query.getResultList();
    }

    @Override
    public Driver find(Integer bsn) {
        String s = "SELECT d FROM Driver d WHERE d.bsn = '" + bsn + "'";
        TypedQuery<Driver> query = em.createQuery(s, Driver.class);
        
        try {
            return query.getSingleResult();
        } catch (NoResultException ex) {
            return null;
        }
    }
}
