package com.tsystems.javaschool.dao.impl;

import com.tsystems.javaschool.dao.interfaces.GenericDAO;
import com.tsystems.javaschool.dao.util.JpaUtil;
import org.apache.log4j.Logger;

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

    final static Logger logger = Logger.getLogger(GenericDAOImpl.class);

    public void save(T entity, EntityManager em) {
        logger.info("Saving entity...");
        em.persist(entity);
        logger.info("Entity has been saved.");
    }

    public void merge(T entity, EntityManager em) {
        logger.info("Merging entity...");
        em.merge(entity);
        logger.info("Entity has been merged.");
    }

    public void delete(T entity, EntityManager em) {
        logger.info("Deleting entity...");
        em.remove(em.contains(entity) ? entity : em.merge(entity));
        logger.info("Entity has been deleted.");
    }

    public List<T> findMany(Query query) {
        logger.info("Searching some entities...");
        List<T> t;
        t = (List<T>) query.getResultList();
        logger.info("Returning List of entities.");
        return t;
    }

    public T findOne(Query query) {
        logger.info("Searching one of entities...");
        T t;
        t = (T) query.getSingleResult();
        logger.info("Returning found entity.");
        return t;
    }

    public T findByID(Class clazz, long id) {
        logger.info("Searching one of entities by id...");
        T t = null;
        EntityManager em = JpaUtil.getEntityManager();
        t = (T) em.find(clazz, id);
        em.close();
        logger.info("Returning found entity.");
        return t;
    }

    public List findAll(Class clazz) {
        logger.info("Searching all of entities...");
        List T = null;
        EntityManager em = JpaUtil.getEntityManager();
        Query query = em.createQuery("from " + clazz.getName());
        T = query.getResultList();
        em.close();
        logger.info("Returning List of entities.");
        return T;
    }

}
