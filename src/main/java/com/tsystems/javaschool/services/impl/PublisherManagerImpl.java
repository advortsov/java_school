package com.tsystems.javaschool.services.impl;

import com.tsystems.javaschool.dao.entity.Publisher;
import com.tsystems.javaschool.dao.impl.PublisherDAOImpl;
import com.tsystems.javaschool.dao.interfaces.PublisherDAO;
import com.tsystems.javaschool.dao.util.Daos;
import com.tsystems.javaschool.dao.util.JpaUtil;
import com.tsystems.javaschool.services.interfaces.PublisherManager;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * @author Alexander Dvortsov
 * @version 1.0
 * @since 11.02.2016
 */
public class PublisherManagerImpl implements PublisherManager {

    private PublisherDAO publisherDAO = Daos.getPublisherDAO();

    @Override
    public Publisher findByPublisherName(String name) {
        return publisherDAO.findByName(name);
    }

    @Override
    public List<Publisher> loadAllPublishers() {
        return publisherDAO.findAll(Publisher.class);
    }

    @Override
    public void saveNewPublisher(Publisher publisher) {
        EntityManager em = null;
        try {
            em = JpaUtil.beginTransaction();
            publisherDAO.save(publisher, em);
            JpaUtil.commitTransaction(em);
        } catch (PersistenceException ex) {
            ex.printStackTrace();
            JpaUtil.rollbackTransaction(em);
        }
    }

    @Override
    public Publisher findPublisherById(long id) {
        return publisherDAO.findByID(Publisher.class, id);
    }

    @Override
    public void deletePublisher(Publisher publisher) {
        EntityManager em = null;
        try {
            em = JpaUtil.beginTransaction();
            publisherDAO.delete(publisher, em);
            JpaUtil.commitTransaction(em);
        } catch (PersistenceException ex) {
            ex.printStackTrace();
            JpaUtil.rollbackTransaction(em);
        }
    }

    @Override
    public void updatePublisher(Publisher publisher) {
        EntityManager em = null;
        try {
            em = JpaUtil.beginTransaction();
            publisherDAO.merge(publisher, em);
            JpaUtil.commitTransaction(em);
        } catch (PersistenceException ex) {
            Logger.getLogger(BookManagerImpl.class.getName()).log(Level.SEVERE, null, ex);
            ex.printStackTrace();
            JpaUtil.rollbackTransaction(em);
        }
    }
}
