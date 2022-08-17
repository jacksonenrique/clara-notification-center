package com.clara.notification.center.repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;


public class BaseRepository<T> implements GenericRepository<T> {

    protected EntityManager entityManager;
    private Class<T> type;

    public EntityManager getEntityManager() {
        return entityManager;
    }

    @PersistenceContext
    public void setEntityManager(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    public BaseRepository() {
        Type t = getClass().getGenericSuperclass();
        ParameterizedType pt = (ParameterizedType) t;
        type = (Class) pt.getActualTypeArguments()[0];
    }

    public T SaveData(final T t) {

        entityManager.persist(t);
        return t;
    }


    public void RemoveData(final Object objectToRemove) {
        entityManager.remove(entityManager.merge(objectToRemove));
    }

    public T GetData(final Object id) {
        return (T) entityManager.find(type, id);
    }

    public T UpdateData(final T t) {
        return entityManager.merge(t);
    }


    public Iterable<T> GetAll() {
        CriteriaBuilder cb = this.entityManager.getCriteriaBuilder();
        CriteriaQuery<T> criteriaQuery = cb.createQuery(type);
        Root<T> root = criteriaQuery.from(type);
        criteriaQuery.select(root);
        TypedQuery<T> query = entityManager.createQuery(criteriaQuery);
        return query.getResultList();
    }
}
