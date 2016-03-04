package com.tsystems.javaschool.services.impl;


import com.tsystems.javaschool.dao.entity.Genre;
import com.tsystems.javaschool.dao.interfaces.GenreDAO;
import com.tsystems.javaschool.dao.util.Daos;
import com.tsystems.javaschool.dao.util.JpaUtil;
import com.tsystems.javaschool.services.exception.DuplicateException;
import com.tsystems.javaschool.services.interfaces.GenreManager;
import org.hibernate.exception.ConstraintViolationException;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * @author Alexander Dvortsov
 * @version 1.0
 * @since 08.02.2016
 */
public class GenreManagerImpl implements GenreManager {

    private GenreDAO genreDAO = Daos.getGenreDAO();

    @Override
    public Genre findByGenreName(String name) {
        return genreDAO.findByName(name);
    }

    @Override
    public List<Genre> loadAllGenres() {
        return genreDAO.findAll(Genre.class);
    }

    @Override
    public void saveNewGenre(Genre genre) throws DuplicateException {
        EntityManager em = null;
        try {
            em = JpaUtil.beginTransaction();
            genreDAO.save(genre, em);
            JpaUtil.commitTransaction(em);
        } catch (PersistenceException ex) {
            ex.printStackTrace();
            JpaUtil.rollbackTransaction(em);
            throw new DuplicateException();
        }
    }

    @Override
    public void updateGenre(Genre genre) {
        EntityManager em = null;
        try {
            em = JpaUtil.beginTransaction();
            genreDAO.merge(genre, em);
            JpaUtil.commitTransaction(em);
        } catch (PersistenceException ex) {
            ex.printStackTrace();
            JpaUtil.rollbackTransaction(em);
        }
    }

    @Override
    public Genre findGenreById(long id) {
        return genreDAO.findByID(Genre.class, id);
    }

    @Override
    public void deleteGenre(Genre genre) {
        EntityManager em = null;
        try {
            em = JpaUtil.beginTransaction();
            genreDAO.setNullBeforeDelete(genre, em); // its covered by transaction. because JPA doesn't implements ON DELETE SET NULL
            genreDAO.delete(genre, em);
            JpaUtil.commitTransaction(em);
        } catch (PersistenceException ex) {
            ex.printStackTrace();
            JpaUtil.rollbackTransaction(em);
        }
    }

    @Override
    public Genre findGenreByName(String name) {
        return genreDAO.findByName(name);
    }

}