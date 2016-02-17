package com.tsystems.javaschool.services.impl;

import com.tsystems.javaschool.dao.entity.Book;
import com.tsystems.javaschool.dao.entity.Genre;
import com.tsystems.javaschool.dao.impl.AuthorDAOImpl;
import com.tsystems.javaschool.dao.impl.BookDAOImpl;
import com.tsystems.javaschool.dao.interfaces.AuthorDAO;
import com.tsystems.javaschool.dao.interfaces.BookDAO;
import com.tsystems.javaschool.dao.util.JpaUtil;
import com.tsystems.javaschool.services.enums.SearchType;
import com.tsystems.javaschool.services.interfaces.BookManager;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceException;
import javax.persistence.Query;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * @author Alexander Dvortsov
 * @version 1.0
 * @since 10.02.2016
 */
public class BookManagerImpl implements BookManager {

    private BookDAO bookDAO = new BookDAOImpl();
    private AuthorDAO authorDAO = new AuthorDAOImpl();

    @Override
    public List<Book> findByBookName(String name) {
        return bookDAO.findByName(name);
    }

    @Override
    public List<Book> findByAuthorName(String name) {
        return bookDAO.findByAuthor(authorDAO.findByName(name));
    }

    @Override
    public List<Book> loadAllBooks() {
        EntityManager manager = JpaUtil.getEntityManager();
        Query query = manager.createQuery("Select b from Book b");
        return bookDAO.findMany(query);
        //return bookDAO.findAll(Book.class);
    }

    @Override
    public void saveNewBook(Book book) {
        try {
            JpaUtil.beginTransaction();
            bookDAO.save(book);
            JpaUtil.commitTransaction();
        } catch (PersistenceException ex) {
            ex.printStackTrace();
            JpaUtil.rollbackTransaction();
        }
    }

    @Override
    public void updateBook(Book book) {
        try {
            JpaUtil.beginTransaction();
            bookDAO.merge(book);
            JpaUtil.commitTransaction();
        } catch (PersistenceException ex) {
            Logger.getLogger(BookManagerImpl.class.getName()).log(Level.SEVERE, null, ex);
            ex.printStackTrace();
            JpaUtil.rollbackTransaction();
        }
    }

    @Override
    public Book findBookById(long id) {
        return bookDAO.findByID(Book.class, id);
    }

    @Override
    public void deleteBook(Book book) {
        try {
            JpaUtil.beginTransaction();
            bookDAO.delete(book);
            JpaUtil.commitTransaction();
        } catch (PersistenceException ex) {
            ex.printStackTrace();
            JpaUtil.rollbackTransaction();
        }
    }

    @Override
    public List<Book> getBooksByGenre(Genre genre) {
        return bookDAO.findByGenre(genre);
    }

    @Override
    public List<Book> getBooksBySearch(String searchStr, SearchType type) {
        if (type == SearchType.AUTHOR){
            return findByAuthorName(searchStr);
        } else if (type == SearchType.TITLE){
            return bookDAO.findByName(searchStr);
        }
        return null;

    }

    @Override
    public int getBookQuantity(long id) {
        return findBookById(id).getQuantity();
    }
}
