package com.marbl.administration.backend.dao;

//<editor-fold defaultstate="collapsed" desc="Imports">
import java.io.Serializable;
import java.util.ArrayList;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaQuery;
//</editor-fold>

// Inherit from this class to create a DAO for easy database usage.

public abstract class AbstractDAO<Entity extends Serializable, ID extends Serializable> implements Serializable {

    //<editor-fold defaultstate="collapsed" desc="Fields">
    @PersistenceContext
    protected EntityManager em;
    private Class<Entity> entityClass;
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="Constructors">
    public AbstractDAO(Class<Entity> entityClass) {
        this.entityClass = entityClass;
    }
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="Methods">
    public void create(Entity entity) {
        if (entity != null && !findAll().contains(entity)) {
            em.persist(entity);
        }
    }

    public Entity edit(Entity entity) {
        if (entity != null && findAll().contains(entity)) {
            entity = em.merge(entity);
        }
        
        return entity;
    }

    public void remove(Entity entity) {
        if (entity != null && findAll().contains(entity)) {
            em.remove(em.merge(entity));
        }
    }

    public Entity find(ID id) {
        return em.find(entityClass, id);
    }

    public ArrayList<Entity> findAll() {
        CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
        cq.select(cq.from(entityClass));
        return new ArrayList(em.createQuery(cq).getResultList());
    }

    public ArrayList<Entity> findRange(int[] range) {
        CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
        cq.select(cq.from(entityClass));
        javax.persistence.Query q = em.createQuery(cq);
        q.setMaxResults(range[1] - range[0]);
        q.setFirstResult(range[0]);
        return new ArrayList(q.getResultList());
    }

    public int count() {
        CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
        javax.persistence.criteria.Root<Entity> rt = cq.from(entityClass);
        cq.select(em.getCriteriaBuilder().count(rt));
        javax.persistence.Query q = em.createQuery(cq);
        return ((Long) q.getSingleResult()).intValue();
    }
    //</editor-fold>
}
