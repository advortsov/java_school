package com.tsystems.javaschool.dao.impl;


import com.tsystems.javaschool.dao.entity.Book;
import com.tsystems.javaschool.dao.entity.Genre;
import com.tsystems.javaschool.dao.interfaces.BookDAO;
import com.tsystems.javaschool.dao.interfaces.GenreDAO;
import com.tsystems.javaschool.dao.util.JpaUtil;
import com.tsystems.javaschool.services.impl.BookManagerImpl;
import com.tsystems.javaschool.services.interfaces.BookManager;

import javax.persistence.Query;
import java.util.List;

/**
 * @author Alexander Dvortsov
 * @version 1.0
 * @since 08.02.2016
 */
public class GenreDAOImpl extends GenericDAOImpl<Genre, Long> implements GenreDAO {

    private BookDAO bookDAO = new BookDAOImpl();

    @Override
    public Genre findByName(String name) {
        Genre genre = null;
        String sql = "SELECT g FROM Genre g WHERE g.name = :name";
        Query query = JpaUtil.getEntityManager().createQuery(sql).
                setParameter("name", name);
        genre = findOne(query);
        return genre;
    }

    @Override
    public void setNullBeforeDelete(Genre entity) {
        BookManager bookManager = new BookManagerImpl();
        List<Book> allBooks = bookManager.getBooksByGenre(entity);

        for (Book book : allBooks) {
            book.setGenre(null);
            bookDAO.merge(book);
        }
    }


}

