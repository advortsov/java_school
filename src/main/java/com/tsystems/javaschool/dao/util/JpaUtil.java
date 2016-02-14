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
    private static EntityManager entityManager;

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

    public static void commitTransaction() {
        JpaUtil.getEntityManager().getTransaction().commit();
    }

    public static void rollbackTransaction() {
        JpaUtil.getEntityManager().getTransaction().rollback();
    }

    public static void closeSession() {
        JpaUtil.getEntityManager().close();
    }


    //session
    public static EntityManager getEntityManager() {
        if (entityManager == null){
            entityManager = getEntityManagerFactory().createEntityManager();
        }
        return entityManager;
    }


}
