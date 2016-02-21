package com.tsystems.javaschool.view.controllers;

import com.tsystems.javaschool.dao.entity.Book;
import com.tsystems.javaschool.services.enums.SearchType;
import com.tsystems.javaschool.services.impl.BookManagerImpl;
import com.tsystems.javaschool.services.impl.GenreManagerImpl;
import com.tsystems.javaschool.services.interfaces.BookManager;
import com.tsystems.javaschool.services.interfaces.GenreManager;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * @author Alexander Dvortsov
 * @version 1.0
 * @since 20.02.2016
 */
public class BookListController {

    public static List<Book> getBySearch(HttpServletRequest request){
        List<Book> currentBookList = null;
        BookManager bookManager = new BookManagerImpl();
        GenreManager genreManager = new GenreManagerImpl();

        if (request.getParameter("genre") != null && request.getParameter("genre").equals("all")) {
            currentBookList = bookManager.loadAllBooks();
        }
        else if (request.getParameter("genre") != null && !request.getParameter("genre").equals("all")) {
            String genreName = String.valueOf(request.getParameter("genre"));
            currentBookList = bookManager.getBooksByGenre(genreManager.findByGenreName(genreName));
        }
        else if (request.getParameter("search_string") != null) {
            String searchStr = request.getParameter("search_string");
            SearchType type = SearchType.TITLE;
            if (request.getParameter("search_option").equals("Автор")) {
                type = SearchType.AUTHOR;
            }

            if (searchStr != null && !searchStr.trim().equals("")) {//
                currentBookList = bookManager.getBooksBySearch(searchStr, type);
            }
        }

        return currentBookList;
    }

}
