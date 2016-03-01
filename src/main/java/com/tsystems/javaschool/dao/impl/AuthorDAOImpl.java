package com.tsystems.javaschool.dao.impl;


import com.tsystems.javaschool.dao.entity.Author;
import com.tsystems.javaschool.dao.entity.Book;
import com.tsystems.javaschool.dao.interfaces.AuthorDAO;
import com.tsystems.javaschool.dao.interfaces.BookDAO;
import com.tsystems.javaschool.dao.util.JpaUtil;
import com.tsystems.javaschool.services.impl.BookManagerImpl;
import com.tsystems.javaschool.services.interfaces.BookManager;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.List;

/**
 * @author Alexander Dvortsov
 * @version 1.0
 * @since 09.02.2016
 */
public class AuthorDAOImpl extends GenericDAOImpl<Author, Long> implements AuthorDAO {
    private BookDAO bookDAO = new BookDAOImpl();


    @Override
    public Author findByName(String name) {
        Author author = null;
        String sql = "SELECT a FROM Author a WHERE a.name = :name";
        EntityManager em = JpaUtil.getEntityManager();
        Query query = em.createQuery(sql).
                setParameter("name", name);
        author = findOne(query);
        em.close();
        return author;
    }

    @Override
    public void setNullBeforeDelete(Author author, EntityManager em) {
        BookManager bookManager = new BookManagerImpl();
        List<Book> allBooks = bookManager.getBooksByAuthor(author);
        for (Book book : allBooks) {
            book.setAuthor(null);
            bookDAO.merge(book, em);
        }
    }
}

