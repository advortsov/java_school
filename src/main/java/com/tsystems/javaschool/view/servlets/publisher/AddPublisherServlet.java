package com.tsystems.javaschool.view.servlets.publisher;

import com.tsystems.javaschool.dao.entity.Publisher;
import com.tsystems.javaschool.services.exception.DuplicateException;
import com.tsystems.javaschool.services.impl.PublisherManagerImpl;
import com.tsystems.javaschool.services.interfaces.PublisherManager;
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
public class AddPublisherServlet extends HttpServlet {

    private String status = "admin_pages/admin.jsp#tab3";
    private String notOk = "error/duplicate.html";

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setCharacterEncoding("UTF-8");
        Publisher publisher = new Publisher(req.getParameter("publisher_name"));
        PublisherManager publisherManager = Managers.getPublisherManager();
        try {
            publisherManager.saveNewPublisher(publisher);
        } catch (DuplicateException e) {
            status = notOk;
        }
        resp.sendRedirect(status);
    }
}
