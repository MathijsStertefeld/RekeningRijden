package com.marbl.administration.dao;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaQuery;

public abstract class AbstractDAO<Entity extends Serializable, ID extends Serializable> implements Serializable {

    @PersistenceContext
    protected EntityManager em;
    private Class<Entity> entityClass;

    public AbstractDAO(Class<Entity> entityClass) {
        this.entityClass = entityClass;
    }

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

    public Collection<Entity> findAll() {
        CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
        cq.select(cq.from(entityClass));
        return em.createQuery(cq).getResultList();
    }

    public Collection<Entity> findRange(int[] range) {
        CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
        cq.select(cq.from(entityClass));
        javax.persistence.Query q = em.createQuery(cq);
        q.setMaxResults(range[1] - range[0]);
        q.setFirstResult(range[0]);
        return q.getResultList();
    }

    public int count() {
        CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
        javax.persistence.criteria.Root<Entity> rt = cq.from(entityClass);
        cq.select(em.getCriteriaBuilder().count(rt));
        javax.persistence.Query q = em.createQuery(cq);
        return ((Long) q.getSingleResult()).intValue();
    }
}
