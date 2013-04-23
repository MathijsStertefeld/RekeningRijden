package administration.dao;

import administration.domain.Car;
import javax.ejb.Stateless;

@Stateless
public class CarDAO extends AbstractDAO<Car, String> {

    public CarDAO() {
        super(Car.class);
    }
}
