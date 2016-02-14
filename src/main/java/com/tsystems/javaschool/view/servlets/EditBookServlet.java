package com.tsystems.javaschool.view.servlets;

import com.tsystems.javaschool.dao.entity.Book;
import com.tsystems.javaschool.services.impl.AuthorManagerImpl;
import com.tsystems.javaschool.services.impl.BookManagerImpl;
import com.tsystems.javaschool.services.impl.PublisherManagerImpl;
import com.tsystems.javaschool.services.interfaces.AuthorManager;
import com.tsystems.javaschool.services.interfaces.BookManager;
import com.tsystems.javaschool.services.interfaces.PublisherManager;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author Alexander Dvortsov
 * @version 1.0
 * @since 14.02.2016
 */
public class EditBookServlet extends HttpServlet{

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse resp) throws ServletException, IOException {

        Book book = (Book) request.getSession().getAttribute("currentBook");

        PublisherManager publisherManager = new PublisherManagerImpl();
        AuthorManager authorManager = new AuthorManagerImpl();
        BookManager bookManager = new BookManagerImpl();
        book.setName(request.getParameter("book_name"));
        book.setIsbn(request.getParameter("book_isbn"));
        book.setPublisher(publisherManager.findByPublisherName(request.getParameter("book_publisher")));
        book.setAuthor(authorManager.findByAuthorName(request.getParameter("book_author")));
        book.setPageCount(Integer.parseInt(request.getParameter("book_pages")));
        book.setPublishYear(Integer.parseInt(request.getParameter("book_year")));
        book.setPrice(Integer.parseInt(request.getParameter("book_price")));

        bookManager.updateBook(book);

        resp.sendRedirect("pages/books.jsp?genre=all");
    }

}
