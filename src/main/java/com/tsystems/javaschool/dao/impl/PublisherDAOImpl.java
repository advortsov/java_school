package com.tsystems.javaschool.dao.impl;

import com.tsystems.javaschool.dao.entity.Publisher;
import com.tsystems.javaschool.dao.interfaces.PublisherDAO;
import com.tsystems.javaschool.dao.util.JpaUtil;
import org.apache.log4j.Logger;

import javax.persistence.EntityManager;
import javax.persistence.Query;


/**
 * @author Alexander Dvortsov
 * @version 1.0
 * @since 11.02.2016
 */
public class PublisherDAOImpl extends GenericDAOImpl<Publisher, Long> implements PublisherDAO {

    final static Logger logger = Logger.getLogger(PublisherDAOImpl.class);//    PropertyConfigurator.configure("log4j.properties");

    @Override
    public Publisher findByName(String name) {
        logger.info("Getting publisher by genre name...");
        Publisher publisher = null;
        String sql = "SELECT a FROM Publisher a WHERE a.name = :name";
        EntityManager em = JpaUtil.getEntityManager();
        Query query = em.createQuery(sql).
                setParameter("name", name);
        publisher = findOne(query);
        em.close();
        logger.info("Returning Publisher object.");
        return publisher;
    }
}
