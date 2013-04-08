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
    public void persist(WebsiteAccount websiteAccount) {
        if (websiteAccount != null && find(websiteAccount.getBsn()) == null) {
            em.persist(websiteAccount);
        }
    }

    @Override
    public WebsiteAccount merge(WebsiteAccount websiteAccount) {
        if (websiteAccount != null && find(websiteAccount.getBsn()) != null) {
            websiteAccount = em.merge(websiteAccount);
        }
        
        return websiteAccount;
    }

    @Override
    public void remove(WebsiteAccount websiteAccount) {
        if (websiteAccount != null && find(websiteAccount.getBsn()) != null) {
            em.remove(websiteAccount);
        }
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
