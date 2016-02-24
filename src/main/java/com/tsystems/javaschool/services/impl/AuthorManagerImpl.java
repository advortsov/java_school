package com.tsystems.javaschool.services.impl;

import com.tsystems.javaschool.dao.entity.Author;
import com.tsystems.javaschool.dao.impl.AuthorDAOImpl;
import com.tsystems.javaschool.dao.interfaces.AuthorDAO;
import com.tsystems.javaschool.dao.util.JpaUtil;
import com.tsystems.javaschool.services.interfaces.AuthorManager;

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

    private AuthorDAO authorDAO = new AuthorDAOImpl();

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
        try {
            JpaUtil.beginTransaction();
            authorDAO.save(author);
            JpaUtil.commitTransaction();
        } catch (PersistenceException ex) {
            ex.printStackTrace();
            JpaUtil.rollbackTransaction();
        }
    }


    @Override
    public Author findAuthorById(long id) {
        return authorDAO.findByID(Author.class, id);
    }

    @Override
    public void deleteAuthor(Author author) {
        try {
            JpaUtil.beginTransaction();
            authorDAO.setNullBeforeDelete(author); // its not covered by transaction. because JPA doesn't implements ON DELETE SET NULL
            authorDAO.delete(author);
            JpaUtil.commitTransaction();
        } catch (PersistenceException ex) {
            ex.printStackTrace();
            JpaUtil.rollbackTransaction();
        }
    }

    @Override
    public void updateAuthor(Author author) {
        try {
            JpaUtil.beginTransaction();
            authorDAO.merge(author);
            JpaUtil.commitTransaction();
        } catch (PersistenceException ex) {
            Logger.getLogger(BookManagerImpl.class.getName()).log(Level.SEVERE, null, ex);
            ex.printStackTrace();
            JpaUtil.rollbackTransaction();
        }
    }
}
