package com.tsystems.javaschool.services.impl;


import com.tsystems.javaschool.dao.entity.Genre;
import com.tsystems.javaschool.dao.impl.GenreDAOImpl;
import com.tsystems.javaschool.dao.interfaces.GenreDAO;
import com.tsystems.javaschool.dao.util.JpaUtil;
import com.tsystems.javaschool.services.interfaces.GenreManager;

import javax.persistence.NonUniqueResultException;
import javax.persistence.PersistenceException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Alexander Dvortsov
 * @version 1.0
 * @since 08.02.2016
 */
public class GenreManagerImpl implements GenreManager {

    private GenreDAO genreDAO = new GenreDAOImpl();

    @Override
    public Genre findByGenreName(String name) {
        return genreDAO.findByName(name);
    }

    @Override
    public List<Genre> loadAllGenres() {
        return genreDAO.findAll(Genre.class);
    }

    @Override
    public void saveNewGenre(Genre genre) {
        try {
            JpaUtil.beginTransaction();
            genreDAO.save(genre);
            JpaUtil.commitTransaction();
        } catch (PersistenceException ex) {
            ex.printStackTrace();
            JpaUtil.rollbackTransaction();
        }
    }

    @Override
    public Genre findGenreById(long id) {
        Genre genre = null;
        try {
            JpaUtil.beginTransaction();
            genre = genreDAO.findByID(Genre.class, id);
            JpaUtil.commitTransaction();
        } catch (PersistenceException ex) {
            ex.printStackTrace();
        }
        return genre;
    }

    @Override
    public void deleteGenre(Genre genre) {
        try {
            JpaUtil.beginTransaction();
            genreDAO.delete(genre);
            JpaUtil.commitTransaction();
        } catch (PersistenceException ex) {
            ex.printStackTrace();
            JpaUtil.rollbackTransaction();
        }
    }

}