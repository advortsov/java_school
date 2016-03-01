package com.tsystems.javaschool.dao.util;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 * @author Alexander Dvortsov
 * @version 1.0
 * @since 10.02.2016
 */
public class JpaUtil {

    private static EntityManagerFactory entityManagerFactory;

    public static EntityManagerFactory getEntityManagerFactory() {
        if (entityManagerFactory == null) {
            entityManagerFactory = Persistence.createEntityManagerFactory("BOOKSTORE1");
        }
        return entityManagerFactory;
    }

    public static EntityManager beginTransaction() {
        EntityManager manager = JpaUtil.getEntityManager();
        manager.getTransaction().begin();
        return manager;
    }

    public static void commitTransaction(EntityManager em) {
        em.getTransaction().commit();
        em.close();
    }

    public static void rollbackTransaction(EntityManager em) {
        em.getTransaction().rollback();
        em.close();
    }

    public static EntityManager getEntityManager() {
        return getEntityManagerFactory().createEntityManager();
    }


}
