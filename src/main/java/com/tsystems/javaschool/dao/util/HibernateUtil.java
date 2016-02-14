package com.tsystems.javaschool.dao.util;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

/**
 * @author Alexander Dvortsov
 * @version 1.0
 * @since 08.02.2016
 */
public class HibernateUtil {

    private static SessionFactory sessionFactory;

    public static SessionFactory getSessionFactory() {
        if (sessionFactory == null) {
            // loads configuration and mappings
            Configuration configuration = new Configuration().configure();
            ServiceRegistry serviceRegistry
                    = new StandardServiceRegistryBuilder()
                    .applySettings(configuration.getProperties()).build();

            // builds a session factory from the service registry
            sessionFactory = configuration.buildSessionFactory(serviceRegistry);
        }

        return sessionFactory;
    }

    public static Session beginTransaction() {
        //Session hibernateSession = HibernateUtil.getSession();

        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
        Session hibernateSession = sessionFactory.getCurrentSession();

        hibernateSession.beginTransaction();
        return hibernateSession;
    }

    public static void commitTransaction() {
        HibernateUtil.getSession().getTransaction().commit();
    }

    public static void rollbackTransaction() {
        HibernateUtil.getSession().getTransaction().rollback();
    }

    public static void closeSession() {
        HibernateUtil.getSession().close();
    }

    public static Session getSession() {
        Session hibernateSession = sessionFactory.getCurrentSession();
        return hibernateSession;
    }
}
