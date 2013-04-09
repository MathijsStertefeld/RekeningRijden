package administration.dao;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.EntityManager;

public abstract class AbstractDAO<Entity, ID> implements Serializable {

    private Class<Entity> entityClass;

    public AbstractDAO(Class<Entity> entityClass) {
        this.entityClass = entityClass;
    }

    protected abstract EntityManager getEntityManager();

    public void create(Entity entity) {
        if (entity != null && !findAll().contains(entity)) {
            getEntityManager().persist(entity);
        }
    }

    public Entity edit(Entity entity) {
        if (entity != null && findAll().contains(entity)) {
            entity = getEntityManager().merge(entity);
        }
        return entity;
    }

    public void remove(Entity entity) {
        if (entity != null && findAll().contains(entity)) {
            getEntityManager().remove(getEntityManager().merge(entity));
        }
    }

    public Entity find(ID id) {
        return getEntityManager().find(entityClass, id);
    }

    public Collection<Entity> findAll() {
        javax.persistence.criteria.CriteriaQuery cq = getEntityManager().getCriteriaBuilder().createQuery();
        cq.select(cq.from(entityClass));
        return getEntityManager().createQuery(cq).getResultList();
    }

    public Collection<Entity> findRange(int[] range) {
        javax.persistence.criteria.CriteriaQuery cq = getEntityManager().getCriteriaBuilder().createQuery();
        cq.select(cq.from(entityClass));
        javax.persistence.Query q = getEntityManager().createQuery(cq);
        q.setMaxResults(range[1] - range[0]);
        q.setFirstResult(range[0]);
        return q.getResultList();
    }

    public int count() {
        javax.persistence.criteria.CriteriaQuery cq = getEntityManager().getCriteriaBuilder().createQuery();
        javax.persistence.criteria.Root<Entity> rt = cq.from(entityClass);
        cq.select(getEntityManager().getCriteriaBuilder().count(rt));
        javax.persistence.Query q = getEntityManager().createQuery(cq);
        return ((Long) q.getSingleResult()).intValue();
    }
}
