package com.tsystems.javaschool.services.impl;

import com.tsystems.javaschool.dao.entity.Author;
import com.tsystems.javaschool.dao.entity.Book;
import com.tsystems.javaschool.dao.entity.Genre;
import com.tsystems.javaschool.dao.interfaces.AuthorDAO;
import com.tsystems.javaschool.dao.interfaces.BookDAO;
import com.tsystems.javaschool.dao.util.Daos;
import com.tsystems.javaschool.dao.util.JpaUtil;
import com.tsystems.javaschool.services.enums.SearchType;
import com.tsystems.javaschool.services.exception.DuplicateException;
import com.tsystems.javaschool.services.interfaces.BookManager;
import org.apache.log4j.Logger;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceException;
import java.util.ArrayList;
import java.util.List;


/**
 * @author Alexander Dvortsov
 * @version 1.0
 * @since 10.02.2016
 */
public class BookManagerImpl implements BookManager {

    final static Logger logger = Logger.getLogger(BookManagerImpl.class);//    PropertyConfigurator.configure("log4j.properties");

    private BookDAO bookDAO = Daos.getBookDAO();
    private AuthorDAO authorDAO = Daos.getAuthorDAO();

    public BookManagerImpl(BookDAO bookDAO) {
        this.bookDAO = bookDAO;
    }

    public BookManagerImpl() {
    }

    @Override
    public List<Book> findByBookName(String name) {
        logger.info("Try to get books by book name...");
        return bookDAO.findByName(name);
    }

    @Override
    public List<Book> findByAuthorName(String name) {
        logger.info("Try to get books by author name...");
        return bookDAO.findByAuthor(authorDAO.findByName(name));
    }

    @Override
    public List<Book> loadAllBooks() {
        logger.info("Loading all books list...");
        return bookDAO.findAll(Book.class);
    }

    @Override
    public void saveNewBook(Book book) throws DuplicateException {
        logger.info("Try to save new book...");
        EntityManager em = null;
        try {
            em = JpaUtil.beginTransaction();
            bookDAO.save(book, em);
            JpaUtil.commitTransaction(em);
            logger.info("New book has been saved.");
        } catch (PersistenceException ex) {
            logger.error("New book has not been saved!");
            ex.printStackTrace();
            JpaUtil.rollbackTransaction(em);
            throw new DuplicateException();
        }
    }

    @Override
    public void updateBook(Book book) throws DuplicateException {
        logger.info("Try to update book...");
        EntityManager em = null;
        try {
            em = JpaUtil.beginTransaction();
            bookDAO.merge(book, em);
            JpaUtil.commitTransaction(em);
            logger.info("New book has been updated.");
        } catch (PersistenceException ex) {
            logger.error("Book has not been updated!");
            ex.printStackTrace();
            JpaUtil.rollbackTransaction(em);
            throw new DuplicateException();
        }
    }

    @Override
    public Book findBookById(long id) {
        logger.info("Try to get one book by id...");
        return bookDAO.findByID(Book.class, id);
    }

    @Override
    public void deleteBook(Book book) {
        logger.info("Try to delete book...");
        EntityManager em = null;
        try {
            em = JpaUtil.beginTransaction();
            bookDAO.delete(book, em);
            JpaUtil.commitTransaction(em);
            logger.info("New book has been deleted.");
        } catch (PersistenceException ex) {
            logger.error("Book has not been deleted!");
            ex.printStackTrace();
            JpaUtil.rollbackTransaction(em);
        }
    }

    @Override
    public List<Book> getBooksByGenre(Genre genre) {
        logger.info("Try to get books by genre...");
        return bookDAO.findByGenre(genre);
    }

    @Override
    public List<Book> getBooksBySearch(String searchStr, SearchType type) {
        logger.info("Try to get books by search...");

        try {

            if (type == SearchType.AUTHOR) {
                logger.info("Searching by author...");
                return findByAuthorName(searchStr);
            } else if (type == SearchType.TITLE) {
                logger.info("Searching by title...");
                return bookDAO.findByName(searchStr);
            }
        } catch (NoResultException ex) {
            logger.info("Nothing found.");
            //ignore
        }
        return new ArrayList<>();
    }

    @Override
    public int getBookQuantity(long id) {
        logger.info("Try to get books quantity in the stock by book id...");
        return findBookById(id).getQuantity();
    }

    @Override
    public List<Book> getBooksByAuthor(Author author) {
        logger.info("Try to get books by author...");
        return bookDAO.findByAuthor(author);
    }

    @Override
    public Book findBookByIsbn(String value) {
        logger.info("Try to get one book by id...");
        return bookDAO.findByIsbn(value);
    }
}
