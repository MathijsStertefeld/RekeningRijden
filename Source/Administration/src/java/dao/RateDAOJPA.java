package dao;

import domain.Rate;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

@Stateless
public class RateDAOJPA implements RateDAO {

    @PersistenceContext
    private EntityManager em;

    @Override
    public int size() {
        return findAll().size();
    }

    @Override
    public void insert(Rate rate) {
        if (rate != null && find(rate.getName()) == null) {
            em.persist(rate);
        }
    }

    @Override
    public Rate update(Rate rate) {
        if (rate != null && find(rate.getName()) != null) {
            rate = em.merge(rate);
        }
        
        return rate;
    }

    @Override
    public void delete(Rate rate) {
        if (rate != null && find(rate.getName()) != null) {
            em.remove(rate);
        }
    }

    @Override
    public List<Rate> findAll() {
        String s = "SELECT r FROM Rate r";
        TypedQuery<Rate> query = em.createQuery(s, Rate.class);
        return query.getResultList();
    }

    @Override
    public Rate find(String name) {
        String s = "SELECT r FROM Rate r WHERE r.name = '" + name + "'";
        TypedQuery<Rate> query = em.createQuery(s, Rate.class);
        
        try {
            return query.getSingleResult();
        } catch (NoResultException e) {
            return null;
        }
    }
}
