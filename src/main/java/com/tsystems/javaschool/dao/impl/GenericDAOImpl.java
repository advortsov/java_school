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


    public void save(T entity, EntityManager em) {
        em.persist(entity);
    }

    public void merge(T entity, EntityManager em) {
        em.merge(entity);
    }

    public void delete(T entity, EntityManager em) {
//        em.remove(entity);
        em.remove(em.contains(entity) ? entity : em.merge(entity));
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
        T t = null;
        EntityManager em = JpaUtil.getEntityManager();
        t = (T) em.find(clazz, id);
        em.close();
        return t;
    }

    public List findAll(Class clazz) {
        List T = null;
        EntityManager em = JpaUtil.getEntityManager();
        Query query = em.createQuery("from " + clazz.getName());
        T = query.getResultList();
        em.close();
        return T;
    }

}
