package dao;

import domain.Rate;
import java.util.List;

public interface RateDAO {
    
    int size();

    void insert(Rate rate);

    Rate update(Rate rate);

    void delete(Rate rate);

    List<Rate> findAll();

    Rate find(String name);
}
