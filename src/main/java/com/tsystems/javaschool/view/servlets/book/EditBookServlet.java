package com.tsystems.javaschool.view.servlets.book;

import com.tsystems.javaschool.dao.entity.Book;
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

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

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
        GenreManager genreManager = new GenreManagerImpl();

        BookManager bookManager = new BookManagerImpl();

        FileItemFactory factory = new DiskFileItemFactory();
        ServletFileUpload upload = new ServletFileUpload(factory);
        List<FileItem> items = null;
        try {
            items = upload.parseRequest(request);
        }
        catch (FileUploadException e) {
            e.printStackTrace();
        }

       for (FileItem item : items) {
           if (item.isFormField()) {
               String name = item.getFieldName();
               String value = item.getString();
               switch (name){
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

        resp.sendRedirect("pages/books.jsp?genre=all");
    }

}
