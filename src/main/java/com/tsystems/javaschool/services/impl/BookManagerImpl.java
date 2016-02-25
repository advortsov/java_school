package com.tsystems.javaschool.services.impl;

import com.tsystems.javaschool.dao.entity.Author;
import com.tsystems.javaschool.dao.entity.Book;
import com.tsystems.javaschool.dao.entity.Genre;
import com.tsystems.javaschool.dao.impl.AuthorDAOImpl;
import com.tsystems.javaschool.dao.impl.BookDAOImpl;
import com.tsystems.javaschool.dao.interfaces.AuthorDAO;
import com.tsystems.javaschool.dao.interfaces.BookDAO;
import com.tsystems.javaschool.dao.util.JpaUtil;
import com.tsystems.javaschool.services.enums.SearchType;
import com.tsystems.javaschool.services.interfaces.BookManager;
import com.tsystems.javaschool.view.LoggerUtil;
import org.apache.log4j.Logger;

import javax.persistence.PersistenceException;
import java.util.List;


/**
 * @author Alexander Dvortsov
 * @version 1.0
 * @since 10.02.2016
 */
public class BookManagerImpl implements BookManager {

    final static Logger logger = Logger.getLogger(BookManagerImpl.class);//    PropertyConfigurator.configure("log4j.properties");


    private BookDAO bookDAO = new BookDAOImpl();
    private AuthorDAO authorDAO = new AuthorDAOImpl();

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
    public void saveNewBook(Book book) {
        logger.info("Try to save new book...");
        try {
            JpaUtil.beginTransaction();
            bookDAO.save(book);
            JpaUtil.commitTransaction();
            logger.info("New book has been saved.");
        } catch (PersistenceException ex) {
            logger.error("New book has not been saved!");
            ex.printStackTrace();
            JpaUtil.rollbackTransaction();
        }
    }

    @Override
    public void updateBook(Book book) {
        logger.info("Try to update book...");
        try {
            JpaUtil.beginTransaction();
            bookDAO.merge(book);
            JpaUtil.commitTransaction();
            logger.info("New book has been updated.");
        } catch (PersistenceException ex) {
            logger.error("Book has not been updated!");
            ex.printStackTrace();
            JpaUtil.rollbackTransaction();
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
        try {
            JpaUtil.beginTransaction();
            bookDAO.delete(book);
            JpaUtil.commitTransaction();
            logger.info("New book has been deleted.");
        } catch (PersistenceException ex) {
            logger.error("Book has not been deleted!");
            ex.printStackTrace();
            JpaUtil.rollbackTransaction();
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

        if (type == SearchType.AUTHOR) {
            logger.info("Searching by author...");
            return findByAuthorName(searchStr);
        } else if (type == SearchType.TITLE) {
            logger.info("Searching by title...");
            return bookDAO.findByName(searchStr);
        }
        logger.info("Nothing found.");
        return null;
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
}
