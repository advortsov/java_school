package com.tsystems.javaschool.dao.impl;

import com.tsystems.javaschool.dao.entity.Author;
import com.tsystems.javaschool.dao.entity.Book;
import com.tsystems.javaschool.dao.entity.Genre;
import com.tsystems.javaschool.dao.interfaces.BookDAO;
import com.tsystems.javaschool.dao.util.JpaUtil;
import org.apache.log4j.Logger;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.List;

/**
 * @author Alexander Dvortsov
 * @version 1.0
 * @since 10.02.2016
 */
public class BookDAOImpl extends GenericDAOImpl<Book, Long> implements BookDAO {

    final static Logger logger = Logger.getLogger(BookDAOImpl.class);

    @Override
    public List<Book> findByName(String name) {
        logger.info("Getting books by book name...");
        List<Book> books = null;
        String sql = "SELECT a FROM Book a WHERE a.name = :name";
        Query query = JpaUtil.getEntityManager().createQuery(sql).
                setParameter("name", name);
        books = findMany(query);
        logger.info("Returning books.");
        return books;
    }

    @Override
    public List<Book> findByGenre(Genre genre) {
        logger.info("Finding books by genre...");
        List<Book> books = null;
        String sql = "SELECT a FROM Book a WHERE a.genre = :genre";
        Query query = JpaUtil.getEntityManager().createQuery(sql).
                setParameter("genre", genre);
        books = findMany(query);
        logger.info("Returning books.");
        return books;
    }

    @Override
    public List<Book> findByAuthor(Author author) {
        logger.info("Finding books by author...");
        List<Book> books = null;
        String sql = "SELECT a FROM Book a WHERE a.author = :author";
        Query query = JpaUtil.getEntityManager().createQuery(sql).
                setParameter("author", author);
        books = findMany(query);
        logger.info("Returning books.");
        return books;
    }

    @Override
    public Book findByIsbn(String isbn) {
        logger.info("Finding book by isbn...");
        Book book = null;
        String sql = "SELECT a FROM Book a WHERE a.isbn = :isbn";
        Query query = JpaUtil.getEntityManager().createQuery(sql).
                setParameter("isbn", isbn);
        book = findOne(query);
        logger.info("Returning book.");
        return book;
    }

    @Override
    public void setBookQuantity(long bookId, int orderQuantity, EntityManager em) {
        logger.info("Setting new book quantity...");
        Book book = this.findByID(Book.class, bookId);
        int actualQuantity = book.getQuantity();
        book.setQuantity(actualQuantity - orderQuantity);
        merge(book, em);
        logger.info("New quantity has been set.");
    }

}
