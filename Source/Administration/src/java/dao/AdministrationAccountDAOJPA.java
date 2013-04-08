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
    public void persist(AdministrationAccount administrationAccount) {
        if (administrationAccount != null && find(administrationAccount.getName()) == null) {
            em.persist(administrationAccount);
        }
    }

    @Override
    public AdministrationAccount merge(AdministrationAccount administrationAccount) {
        if (administrationAccount != null && find(administrationAccount.getName()) != null) {
            administrationAccount = em.merge(administrationAccount);
        }
        return administrationAccount;
    }

    @Override
    public void remove(AdministrationAccount administrationAccount) {
        if (administrationAccount != null && find(administrationAccount.getName()) != null) {
            em.remove(administrationAccount);
        }
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
