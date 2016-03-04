package com.tsystems.javaschool.view.servlets.author;

import com.tsystems.javaschool.dao.entity.Author;
import com.tsystems.javaschool.services.exception.DuplicateException;
import com.tsystems.javaschool.services.interfaces.AuthorManager;
import com.tsystems.javaschool.services.util.Managers;

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

    private String status = "admin_pages/admin.jsp#tab4";
    private String notOk = "error/duplicate.html";

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setCharacterEncoding("UTF-8");
        Author author = new Author(req.getParameter("author_name"));
        AuthorManager authorManager = Managers.getAuthorManager();
        try {
            authorManager.saveNewAuthor(author);
        } catch (DuplicateException e) {
            status = notOk;
        }
        resp.sendRedirect(status);
    }
}

