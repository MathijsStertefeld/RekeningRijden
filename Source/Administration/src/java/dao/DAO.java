package dao;

import java.util.List;

public interface DAO<T, U> {

    int size();

    void insert(T item);

    T update(T item);

    void delete(T item);

    List<T> findAll();

    T find(U id);
}
