package com.tsystems.javaschool.view.controllers;

import com.tsystems.javaschool.dao.entity.Book;
import com.tsystems.javaschool.services.enums.SearchType;
import com.tsystems.javaschool.services.exception.DuplicateException;
import com.tsystems.javaschool.services.impl.AuthorManagerImpl;
import com.tsystems.javaschool.services.impl.BookManagerImpl;
import com.tsystems.javaschool.services.impl.GenreManagerImpl;
import com.tsystems.javaschool.services.impl.PublisherManagerImpl;
import com.tsystems.javaschool.services.interfaces.AuthorManager;
import com.tsystems.javaschool.services.interfaces.BookManager;
import com.tsystems.javaschool.services.interfaces.GenreManager;
import com.tsystems.javaschool.services.interfaces.PublisherManager;
import com.tsystems.javaschool.services.util.Managers;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileItemFactory;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import javax.persistence.NoResultException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.List;

/**
 * @author Alexander Dvortsov
 * @version 1.0
 * @since 20.02.2016
 */
public class BookListController extends HttpServlet {

    private String status = "/pages/books.jsp";
    private String notOk = "/error/duplicate.jsp";

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        resp.setCharacterEncoding("UTF-8");
        resp.setContentType("text/html; charset=UTF-8");

        List<Book> currBookList = getByParam(req);
        req.getSession().setAttribute("currentBookList", currBookList);
        RequestDispatcher requestDispatcher = getServletContext().getRequestDispatcher(status);
        requestDispatcher.forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        resp.setCharacterEncoding("UTF-8");
        resp.setContentType("text/html; charset=UTF-8");

        String action = null;

        // --------------------- cause its encrypt data form:

        FileItemFactory factory = new DiskFileItemFactory();
        ServletFileUpload upload = new ServletFileUpload(factory);
        List<FileItem> items = null;
        try {
            items = upload.parseRequest(req);
        } catch (FileUploadException e) {
            e.printStackTrace();
        }

        for (FileItem item : items) {
            if (item.isFormField()) {
                String name = item.getFieldName();
                String value = item.getString();

                if (name.equals("action")) {
                    action = value;
                }
            }
        }
        // --------------------- cause its encrypt data form

        if (action != null) {
            try {
                switch (action) {
                    case "add": {
                        addBook(items);
                        break;
                    }
                    case "edit": {
                        editBook(req, items);
                        break;
                    }
                }
            } catch (DuplicateException e) {
                throw new IOException();
            }
        }

        RequestDispatcher requestDispatcher = getServletContext().getRequestDispatcher(status);
        requestDispatcher.forward(req, resp);
    }

    private List<Book> getByParam(HttpServletRequest req) {

        try {
            req.setCharacterEncoding("UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        List<Book> currentBookList = null;
        BookManager bookManager = new BookManagerImpl();
        GenreManager genreManager = new GenreManagerImpl();

        if (req.getParameter("genre") != null && req.getParameter("genre").equals("all")) {
            currentBookList = bookManager.loadAllBooks();
        } else if (req.getParameter("genre") != null && !req.getParameter("genre").equals("all")) {
            String genreName = String.valueOf(req.getParameter("genre"));
            currentBookList = bookManager.getBooksByGenre(genreManager.findByGenreName(genreName));
        } else if (req.getParameter("search_string") != null) {
            String searchStr = req.getParameter("search_string");
            SearchType type = SearchType.TITLE;
            if (req.getParameter("search_option").equals("AUTHOR")) {
                type = SearchType.AUTHOR;
            }

            if (searchStr != null && !searchStr.trim().equals("")) {//
                currentBookList = bookManager.getBooksBySearch(searchStr.trim(), type);
            }
        }

        req.setAttribute("currentBookList", currentBookList);
        return currentBookList;
    }

    private void addBook(List<FileItem> items) throws DuplicateException {

        Book book = new Book();

        PublisherManager publisherManager = Managers.getPublisherManager();
        AuthorManager authorManager = Managers.getAuthorManager();
        GenreManager genreManager = Managers.getGenreManager();
        BookManager bookManager = Managers.getBookManager();

        for (FileItem item : items) {
            if (item.isFormField()) {
                String name = item.getFieldName();
                String value = item.getString();
                switch (name) {
                    case "book_name":
                        if (value.length() == 0) throw new IllegalArgumentException();
                        book.setName(value);
                        break;
                    case "book_genre":
                        book.setGenre(genreManager.findByGenreName(value));
                        break;
                    case "book_isbn":
                        if (value.length() == 0) throw new IllegalArgumentException();
                        verifyIsbnUniqueness(value, book);
                        book.setIsbn(value);
                        break;
                    case "book_publisher":
                        book.setPublisher(publisherManager.findByPublisherName(value));
                        break;
                    case "book_author":
                        book.setAuthor(authorManager.findByAuthorName(value));
                        break;
                    case "book_pages":
                        book.setPageCount(Integer.parseInt(value));
                        break;
                    case "book_year":
                        book.setPublishYear(Integer.parseInt(value));
                        break;
                    case "book_price":
                        book.setPrice(Integer.parseInt(value));
                        break;
                    case "book_count":
                        book.setQuantity(Integer.parseInt(value));
                        break;
                }
            } else {
                book.setImage(item.get());
            }
        }

        try {
            bookManager.saveNewBook(book);
        } catch (DuplicateException e) {
            status = notOk;
        }
    }

    private void editBook(HttpServletRequest request, List<FileItem> items) throws DuplicateException {
        try {
            request.setCharacterEncoding("UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        Book book = (Book) request.getSession().getAttribute("currentBook");

        PublisherManager publisherManager = Managers.getPublisherManager();
        AuthorManager authorManager = Managers.getAuthorManager();
        GenreManager genreManager = Managers.getGenreManager();
        BookManager bookManager = Managers.getBookManager();

        for (FileItem item : items) {
            if (item.isFormField()) {
                String name = item.getFieldName();
                String value = item.getString();
                switch (name) {
                    case "book_name":
                        book.setName(value);
                        break;
                    case "book_genre":
                        book.setGenre(genreManager.findByGenreName(value));
                        break;
                    case "book_isbn":
                        verifyIsbnUniqueness(value, book);
                        book.setIsbn(value);
                        break;
                    case "book_publisher":
                        book.setPublisher(publisherManager.findByPublisherName(value));
                        break;
                    case "book_author":
                        book.setAuthor(authorManager.findByAuthorName(value));
                        break;
                    case "book_pages":
                        book.setPageCount(Integer.parseInt(value));
                        break;
                    case "book_year":
                        book.setPublishYear(Integer.parseInt(value));
                        break;
                    case "book_price":
                        book.setPrice(Integer.parseInt(value));
                        break;
                    case "book_count":
                        book.setQuantity(Integer.parseInt(value));
                        break;
                }
            } else {
                if (item.get().length != 0) {
                    book.setImage(item.get());
                }
            }
        }
        try {
            bookManager.updateBook(book);
        } catch (DuplicateException e) {
            status = notOk;
        }
    }

    private void verifyIsbnUniqueness(String value, Book verifyBook) throws DuplicateException {
        Book book = null;
        try {
            book = Managers.getBookManager().findBookByIsbn(value);
        } catch (NoResultException exception) {
            book = null;
        }
        if (verifyBook.getName() != null) {
            if (book != null && !book.getName().equals(verifyBook.getName())) throw new DuplicateException();
        }
    }

}
