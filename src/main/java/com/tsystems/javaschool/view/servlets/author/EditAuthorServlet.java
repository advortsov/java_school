package com.tsystems.javaschool.view.servlets.author;

import com.tsystems.javaschool.dao.entity.Author;
import com.tsystems.javaschool.services.exception.DuplicateException;
import com.tsystems.javaschool.services.impl.AuthorManagerImpl;
import com.tsystems.javaschool.services.interfaces.AuthorManager;
import com.tsystems.javaschool.services.util.Managers;

import javax.persistence.NoResultException;
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
public class EditAuthorServlet extends HttpServlet {

    private String status = "admin_pages/admin.jsp#tab4";
    private String notOk = "error/duplicate.html";

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        AuthorManager authorManager = Managers.getAuthorManager();
        Author author = authorManager.findByAuthorName(req.getParameter("author_for_edit"));

        try {
            verifyAuthorName(req.getParameter("author_name"));
        } catch (DuplicateException e) {
            throw new IOException();
        }

        author.setName(req.getParameter("author_name"));
        authorManager.updateAuthor(author);
        resp.sendRedirect(status);
    }

    private void verifyAuthorName(String value) throws DuplicateException {
        Author author = null;
        try {
            author = Managers.getAuthorManager().findByAuthorName(value);
        } catch (NoResultException exception) {
            author = null;
        }
        if (author != null) throw new DuplicateException();
    }
}
