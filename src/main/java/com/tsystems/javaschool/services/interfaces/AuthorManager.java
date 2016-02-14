package com.tsystems.javaschool.services.interfaces;

import com.tsystems.javaschool.dao.entity.Author;
import com.tsystems.javaschool.dao.entity.Book;
import com.tsystems.javaschool.dao.entity.Client;
import com.tsystems.javaschool.dao.entity.Order;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * @author Alexander Dvortsov
 * @version 1.0
 * @since 11.02.2016
 */
public interface AuthorManager {
    public Author findByAuthorName(String name);

    public List<Author> loadAllAuthors();

    public void saveNewAuthor(Author author);

    public Author findAuthorById(long id);

    public void deleteAuthor(Author author);
}
