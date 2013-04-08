package dao;

import domain.Rate;
import java.util.List;

public interface RateDAO {
    
    void persist(Rate rate);

    Rate merge(Rate rate);

    void remove(Rate rate);

    List<Rate> findAll();

    Rate find(String name);
}
