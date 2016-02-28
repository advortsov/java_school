package com.tsystems.javaschool.view.servlets.author;

import com.tsystems.javaschool.dao.entity.Author;
import com.tsystems.javaschool.services.impl.AuthorManagerImpl;
import com.tsystems.javaschool.services.interfaces.AuthorManager;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author Alexander Dvortsov
 * @version 1.0
 * @since 21.02.2016
 */
public class AddAuthorServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setCharacterEncoding("UTF-8");
        Author author = new Author(req.getParameter("author_name"));
        AuthorManager authorManager = new AuthorManagerImpl();
        authorManager.saveNewAuthor(author);
        resp.sendRedirect("admin_pages/admin.jsp");
    }
}

