package administration.dao;

import administration.domain.Driver;
import javax.ejb.Stateless;

@Stateless
public class DriverDAO extends AbstractDAO<Driver, Integer> {

    public DriverDAO() {
        super(Driver.class);
    }
}
