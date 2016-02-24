package com.tsystems.javaschool.dao.impl;

import com.tsystems.javaschool.dao.entity.Publisher;
import com.tsystems.javaschool.dao.interfaces.PublisherDAO;
import com.tsystems.javaschool.dao.util.JpaUtil;

import javax.persistence.Query;


/**
 * @author Alexander Dvortsov
 * @version 1.0
 * @since 11.02.2016
 */
public class PublisherDAOImpl extends GenericDAOImpl<Publisher, Long> implements PublisherDAO {


    @Override
    public Publisher findByName(String name) {
        Publisher publisher = null;
        String sql = "SELECT a FROM Publisher a WHERE a.name = :name";
        Query query = JpaUtil.getEntityManager().createQuery(sql).
                setParameter("name", name);
        publisher = findOne(query);
        return publisher;
    }
}
