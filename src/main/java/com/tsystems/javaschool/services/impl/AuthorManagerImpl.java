package com.tsystems.javaschool.services.impl;

import com.tsystems.javaschool.dao.entity.Author;
import com.tsystems.javaschool.dao.impl.AuthorDAOImpl;
import com.tsystems.javaschool.dao.interfaces.AuthorDAO;
import com.tsystems.javaschool.dao.util.Daos;
import com.tsystems.javaschool.dao.util.JpaUtil;
import com.tsystems.javaschool.services.interfaces.AuthorManager;

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
public class AuthorManagerImpl implements AuthorManager {

    private AuthorDAO authorDAO = Daos.getAuthorDAO();

    @Override
    public Author findByAuthorName(String name) {
        return authorDAO.findByName(name);
    }

    @Override
    public List<Author> loadAllAuthors() {
        return authorDAO.findAll(Author.class);
    }

    @Override
    public void saveNewAuthor(Author author) {
        EntityManager em = null;
        try {
            em = JpaUtil.beginTransaction();
            authorDAO.save(author, em);
            JpaUtil.commitTransaction(em);
        } catch (PersistenceException ex) {
            ex.printStackTrace();
            JpaUtil.rollbackTransaction(em);
        }
    }


    @Override
    public Author findAuthorById(long id) {
        return authorDAO.findByID(Author.class, id);
    }

    @Override
    public void deleteAuthor(Author author) {
        EntityManager em = null;
        try {
            em = JpaUtil.beginTransaction();
            authorDAO.setNullBeforeDelete(author, em); // its not covered by transaction. because JPA doesn't implements ON DELETE SET NULL
            authorDAO.delete(author, em);
            JpaUtil.commitTransaction(em);
        } catch (PersistenceException ex) {
            ex.printStackTrace();
            JpaUtil.rollbackTransaction(em);
        }
    }

    @Override
    public void updateAuthor(Author author) {
        EntityManager em = null;
        try {
            em = JpaUtil.beginTransaction();
            authorDAO.merge(author, em);
            JpaUtil.commitTransaction(em);
        } catch (PersistenceException ex) {
            Logger.getLogger(BookManagerImpl.class.getName()).log(Level.SEVERE, null, ex);
            ex.printStackTrace();
            JpaUtil.rollbackTransaction(em);
        }
    }
}
