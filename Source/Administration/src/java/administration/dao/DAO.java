package administration.dao;

import java.util.Collection;

public interface DAO<T, U> {

    void persist(T item);

    T merge(T item);

    void remove(T item);

    Collection<T> findAll();

    T find(U id);
}
