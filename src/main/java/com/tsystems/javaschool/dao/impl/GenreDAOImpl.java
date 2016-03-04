package com.tsystems.javaschool.dao.impl;


import com.tsystems.javaschool.dao.entity.Book;
import com.tsystems.javaschool.dao.entity.Genre;
import com.tsystems.javaschool.dao.interfaces.BookDAO;
import com.tsystems.javaschool.dao.interfaces.GenreDAO;
import com.tsystems.javaschool.dao.util.Daos;
import com.tsystems.javaschool.dao.util.JpaUtil;
import com.tsystems.javaschool.services.impl.BookManagerImpl;
import com.tsystems.javaschool.services.interfaces.BookManager;
import com.tsystems.javaschool.services.util.Managers;
import org.apache.log4j.Logger;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.List;

/**
 * @author Alexander Dvortsov
 * @version 1.0
 * @since 08.02.2016
 */
public class GenreDAOImpl extends GenericDAOImpl<Genre, Long> implements GenreDAO {

    final static Logger logger = Logger.getLogger(GenreDAOImpl.class);//    PropertyConfigurator.configure("log4j.properties");

    private BookDAO bookDAO = Daos.getBookDAO();

    @Override
    public Genre findByName(String name) {
        logger.info("Getting genre by genre name...");
        Genre genre = null;
        String sql = "SELECT g FROM Genre g WHERE g.name = :name";
        EntityManager em = JpaUtil.getEntityManager();
        Query query = em.createQuery(sql).
                setParameter("name", name);
        genre = findOne(query);
        em.close();
        logger.info("Returning Genre object.");
        return genre;
    }

    @Override
    public void setNullBeforeDelete(Genre entity, EntityManager em) {
        logger.info("Setting null current genre fields in the books.");
        BookManager bookManager = Managers.getBookManager();
        List<Book> allBooks = bookManager.getBooksByGenre(entity);
        for (Book book : allBooks) {
            book.setGenre(null);
            bookDAO.merge(book, em);
        }
        logger.info("All books with current genre has been proceed.");

    }


}

