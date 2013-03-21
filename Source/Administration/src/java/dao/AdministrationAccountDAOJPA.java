package dao;

import domain.AdministrationAccount;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

@Stateless
public class AdministrationAccountDAOJPA implements AdministrationAccountDAO {

    @PersistenceContext
    private EntityManager em;

    @Override
    public int size() {
        return findAll().size();
    }

    @Override
    public void insert(AdministrationAccount administrationAccount) {
        em.persist(administrationAccount);
    }

    @Override
    public AdministrationAccount update(AdministrationAccount administrationAccount) {
        return em.merge(administrationAccount);
    }

    @Override
    public void delete(AdministrationAccount administrationAccount) {
        em.remove(administrationAccount);
    }

    @Override
    public List<AdministrationAccount> findAll() {
        String s = "SELECT a FROM AdministrationAccount a";
        TypedQuery<AdministrationAccount> query = em.createQuery(s, AdministrationAccount.class);
        return query.getResultList();
    }

    @Override
    public AdministrationAccount find(String name) {
        String s = "SELECT a FROM AdministrationAccount a WHERE a.name = '" + name + "'";
        TypedQuery<AdministrationAccount> query = em.createQuery(s, AdministrationAccount.class);
        
        try {
            return query.getSingleResult();
        } catch (NoResultException e) {
            return null;
        }
    }
}
