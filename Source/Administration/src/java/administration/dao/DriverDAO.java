package administration.dao;

import administration.domain.Driver;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;

@Stateless
public class DriverDAO extends AbstractDAO<Driver, Integer> {

    public DriverDAO() {
        super(Driver.class);
    }
    
    public Driver findDriverByEmail(String email) {
        String s = "SELECT d FROM Driver d WHERE d.email = '" + email + "'";
        TypedQuery<Driver> query = em.createQuery(s, Driver.class);
        
        try {
            return query.getSingleResult();
        } catch (NoResultException ex) {
            return null;
        }
    }
}
