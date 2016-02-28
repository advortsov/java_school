package com.tsystems.javaschool.view.servlets.publisher;

import com.tsystems.javaschool.dao.entity.Publisher;
import com.tsystems.javaschool.services.impl.PublisherManagerImpl;
import com.tsystems.javaschool.services.interfaces.PublisherManager;

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
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setCharacterEncoding("UTF-8");

        PublisherManager publisherManager = new PublisherManagerImpl();
        Publisher publisher = publisherManager.findByPublisherName(req.getParameter("publisher_for_edit"));

        publisher.setName(req.getParameter("publisher_name"));
        publisherManager.updatePublisher(publisher);
        resp.sendRedirect("admin_pages/admin.jsp");
    }
}
