package com.tsystems.javaschool.dao.impl;

import com.tsystems.javaschool.dao.entity.Author;
import com.tsystems.javaschool.dao.entity.Book;
import com.tsystems.javaschool.dao.entity.Genre;
import com.tsystems.javaschool.dao.interfaces.BookDAO;
import com.tsystems.javaschool.dao.util.JpaUtil;

import javax.persistence.Query;
import java.util.List;

/**
 * @author Alexander Dvortsov
 * @version 1.0
 * @since 10.02.2016
 */
public class BookDAOImpl extends GenericDAOImpl<Book, Long> implements BookDAO {

    @Override
    public List<Book> findByName(String name) {
//        Book book = null;
//        String sql = "SELECT a FROM Book a WHERE a.name = :name";
//        Query query = JpaUtil.getEntityManager().createQuery(sql).
//                setParameter("name", name);
//        book = findOne(query);
//        return book;
        List<Book> books = null;
        String sql = "SELECT a FROM Book a WHERE a.name = :name";
        Query query = JpaUtil.getEntityManager().createQuery(sql).
                setParameter("name", name);
        books = findMany(query);
        return books;
    }

    @Override
    public List<Book> findByGenre(Genre genre) {
        List<Book> books = null;
        String sql = "SELECT a FROM Book a WHERE a.genre = :genre";
        Query query = JpaUtil.getEntityManager().createQuery(sql).
                setParameter("genre", genre);
        books = findMany(query);
        return books;
    }

    @Override
    public List<Book> findByAuthor(Author author) {
        List<Book> books = null;
        String sql = "SELECT a FROM Book a WHERE a.author = :author";
        Query query = JpaUtil.getEntityManager().createQuery(sql).
                setParameter("author", author);
        books = findMany(query);
        return books;
    }

    @Override
    public List<Book> findByAuthorName(String authorName) {
        return null;
    }


}
