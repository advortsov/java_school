package com.tsystems.javaschool.dao.impl;

import com.tsystems.javaschool.dao.interfaces.GenericDAO;
import com.tsystems.javaschool.dao.util.JpaUtil;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.io.Serializable;
import java.util.List;

/**
 * @author Alexander Dvortsov
 * @version 1.0
 * @since 08.02.2016
 */

public abstract class GenericDAOImpl<T, ID extends Serializable> implements GenericDAO<T, ID> {

    protected EntityManager getManager() {
        return JpaUtil.getEntityManager();
    }

    public void save(T entity) {
        EntityManager manager = this.getManager();
        manager.persist(entity);
    }

    public void merge(T entity) {
        EntityManager manager = this.getManager();
        manager.merge(entity);
    }

    public void delete(T entity) {
        EntityManager manager = this.getManager();
        manager.detach(entity); // i hope its deleting)
    }

    public List<T> findMany(Query query) {
        List<T> t;
        t = (List<T>) query.getResultList();
        return t;
    }

    public T findOne(Query query) {
        T t;
        t = (T) query.getSingleResult();
        return t;
    }

    public T findByID(Class clazz, long id) {
        EntityManager manager = this.getManager();
        T t = null;
        t = (T) manager.find(clazz, id);
        return t;
    }

    public List findAll(Class clazz) {
        EntityManager manager = this.getManager();
        List T = null;
        Query query = manager.createQuery("from " + clazz.getName());
        T = query.getResultList();
        return T;
    }
}
