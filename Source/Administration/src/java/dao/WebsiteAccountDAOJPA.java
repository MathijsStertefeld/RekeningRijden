package dao;

import domain.WebsiteAccount;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

@Stateless
public class WebsiteAccountDAOJPA implements WebsiteAccountDAO {

    @PersistenceContext
    private EntityManager em;

    @Override
    public int size() {
        return findAll().size();
    }

    @Override
    public void insert(WebsiteAccount websiteAccount) {
        em.persist(websiteAccount);
    }

    @Override
    public WebsiteAccount update(WebsiteAccount websiteAccount) {
        return em.merge(websiteAccount);
    }

    @Override
    public void delete(WebsiteAccount websiteAccount) {
        em.remove(websiteAccount);
    }

    @Override
    public List<WebsiteAccount> findAll() {
        String s = "SELECT w FROM WebsiteAccount w";
        TypedQuery<WebsiteAccount> query = em.createQuery(s, WebsiteAccount.class);
        return query.getResultList();
    }

    @Override
    public WebsiteAccount find(Integer bsn) {
        String s = "SELECT w FROM WebsiteAccount w WHERE w.bsn = '" + bsn + "'";
        TypedQuery<WebsiteAccount> query = em.createQuery(s, WebsiteAccount.class);
        
        try {
            return query.getSingleResult();
        } catch (NoResultException e) {
            return null;
        }
    }
}
