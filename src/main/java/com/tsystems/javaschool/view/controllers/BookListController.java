package com.tsystems.javaschool.view.controllers;

import com.tsystems.javaschool.dao.entity.Book;
import com.tsystems.javaschool.services.enums.SearchType;
import com.tsystems.javaschool.services.impl.AuthorManagerImpl;
import com.tsystems.javaschool.services.impl.BookManagerImpl;
import com.tsystems.javaschool.services.impl.GenreManagerImpl;
import com.tsystems.javaschool.services.impl.PublisherManagerImpl;
import com.tsystems.javaschool.services.interfaces.AuthorManager;
import com.tsystems.javaschool.services.interfaces.BookManager;
import com.tsystems.javaschool.services.interfaces.GenreManager;
import com.tsystems.javaschool.services.interfaces.PublisherManager;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileItemFactory;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * @author Alexander Dvortsov
 * @version 1.0
 * @since 20.02.2016
 */
public class BookListController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Book> currBookList = getByParam(req);
        req.getSession().setAttribute("currentBookList", currBookList);
        RequestDispatcher requestDispatcher = getServletContext().getRequestDispatcher("/pages/books.jsp");
        requestDispatcher.forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
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
            switch (action) {
                case "add": {
                    addBook(items);
                    break;
                }
                case "edit": {
                    editBook(req, items);
                    break;
                }
                case "delete": {
                    //deleteBook(req);
                    break;
                }
            }
        }


        RequestDispatcher requestDispatcher = getServletContext().getRequestDispatcher("/pages/books.jsp");
        requestDispatcher.forward(req, resp);
    }

    private List<Book> getByParam(HttpServletRequest req) {
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
            if (req.getParameter("search_option").equals("Автор")) {
                type = SearchType.AUTHOR;
            }

            if (searchStr != null && !searchStr.trim().equals("")) {//
                currentBookList = bookManager.getBooksBySearch(searchStr, type);
            }
        }

        req.setAttribute("currentBookList", currentBookList);
        return currentBookList;
    }

    private void addBook(List<FileItem> items) {
        Book book = new Book();

        PublisherManager publisherManager = new PublisherManagerImpl();
        AuthorManager authorManager = new AuthorManagerImpl();
        GenreManager genreManager = new GenreManagerImpl();

        BookManager bookManager = new BookManagerImpl();

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

        bookManager.saveNewBook(book);
    }

    private void editBook(HttpServletRequest request, List<FileItem> items) {
        Book book = (Book) request.getSession().getAttribute("currentBook");

        PublisherManager publisherManager = new PublisherManagerImpl();
        AuthorManager authorManager = new AuthorManagerImpl();
        GenreManager genreManager = new GenreManagerImpl();

        BookManager bookManager = new BookManagerImpl();

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
                }
            } else {
                book.setImage(item.get());
            }
        }

        bookManager.updateBook(book);

    }

}
