package com.tsystems.javaschool.view.servlets.publisher;

import com.tsystems.javaschool.dao.entity.Publisher;
import com.tsystems.javaschool.services.exception.DuplicateException;
import com.tsystems.javaschool.services.interfaces.PublisherManager;
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
public class EditPublisherServlet extends HttpServlet {

    private String status = "admin_pages/admin.jsp#tab3";
    private String notOk = "error/duplicate.html";

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setCharacterEncoding("UTF-8");

        PublisherManager publisherManager = Managers.getPublisherManager();
        Publisher publisher = publisherManager.findByPublisherName(req.getParameter("publisher_for_edit"));

        try {
            verifyPublisherName(req.getParameter("publisher_name"));
        } catch (DuplicateException e) {
            throw new IOException();
        }

        publisher.setName(req.getParameter("publisher_name"));

        publisherManager.updatePublisher(publisher);

        resp.sendRedirect(status);
    }

    private void verifyPublisherName(String value) throws DuplicateException {
        Publisher publisher = null;
        try {
            publisher = Managers.getPublisherManager().findByPublisherName(value);
        } catch (NoResultException exception) {
            publisher = null;
        }
        if (publisher != null) throw new DuplicateException();
    }
}
