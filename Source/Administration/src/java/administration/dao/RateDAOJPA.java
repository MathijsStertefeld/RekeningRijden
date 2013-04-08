package administration.dao;

import administration.domain.Rate;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.*;

@Stateless
public class RateDAOJPA implements RateDAO {

    @PersistenceContext
    private EntityManager em;

    @Override
    public void persist(Rate rate) {
        if (rate != null && find(rate.getName()) == null) {
            em.persist(rate);
        }
    }

    @Override
    public Rate merge(Rate rate) {
        if (rate != null && find(rate.getName()) != null) {
            rate = em.merge(rate);
        }
        
        return rate;
    }

    @Override
    public void remove(Rate rate) {
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
        } catch (NoResultException ex) {
            return null;
        }
    }
}
