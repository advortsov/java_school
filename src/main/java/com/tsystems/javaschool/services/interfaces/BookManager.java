package com.tsystems.javaschool.services.interfaces;

import com.tsystems.javaschool.dao.entity.Book;
import com.tsystems.javaschool.dao.entity.Genre;
import com.tsystems.javaschool.services.enums.SearchType;

import java.math.BigDecimal;
import java.util.List;

/**
 * @author Alexander Dvortsov
 * @version 1.0
 * @since 10.02.2016
 */
public interface BookManager {
    public List<Book> findByBookName(String name);

    public List<Book> loadAllBooks();

    public void saveNewBook(Book book);

    public Book findBookById(long id);

    public void deleteBook(Book book);

    //////

    public List<Book> getBooksByGenre(Genre genre);
    public List<Book> findByAuthorName(String name);
    public List<Book> getBooksBySearch(String searchStr, SearchType type);
}
