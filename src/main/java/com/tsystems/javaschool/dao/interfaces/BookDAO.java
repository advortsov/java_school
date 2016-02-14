package com.tsystems.javaschool.dao.interfaces;

import com.tsystems.javaschool.dao.entity.Book;
import com.tsystems.javaschool.dao.entity.Genre;

import java.util.List;

/**
 * @author Alexander Dvortsov
 * @version 1.0
 * @since 10.02.2016
 */
public interface BookDAO extends GenericDAO<Book, Long>{
    public List<Book> findByName(String name); // could return many books with the same names

    public List<Book> findByGenre(Genre genre);

    public List<Book> findByAuthor(String name);
}
