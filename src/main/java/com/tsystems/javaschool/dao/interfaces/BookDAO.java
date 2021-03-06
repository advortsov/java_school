package com.tsystems.javaschool.dao.interfaces;

import com.tsystems.javaschool.dao.entity.Author;
import com.tsystems.javaschool.dao.entity.Book;
import com.tsystems.javaschool.dao.entity.Genre;

import javax.persistence.EntityManager;
import java.util.List;

/**
 * @author Alexander Dvortsov
 * @version 1.0
 * @since 10.02.2016
 */
public interface BookDAO extends GenericDAO<Book, Long> {
    List<Book> findByName(String name); // could return many books with the same names

    List<Book> findByGenre(Genre genre);

    List<Book> findByAuthor(Author author);

    Book findByIsbn(String isbn);

    void setBookQuantity(long bookId, int quantity, EntityManager em);
}
