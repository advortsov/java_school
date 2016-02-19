package com.tsystems.javaschool.view.servlets;

import com.tsystems.javaschool.dao.entity.Client;
import com.tsystems.javaschool.services.impl.ClientManagerImpl;
import com.tsystems.javaschool.services.interfaces.ClientManager;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;

/**
 * @author Alexander Dvortsov
 * @version 1.0
 * @since 19.02.2016
 */
public class EditClientServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse resp) throws ServletException, IOException {

        Client client = (Client) request.getSession().getAttribute("currentClient");

        ClientManager clientManager = new ClientManagerImpl();
        client.setName(request.getParameter("client_name"));
        client.setSurname(request.getParameter("client_surname"));
        client.setAddress(request.getParameter("client_address"));
        client.setEmail(request.getParameter("client_email"));

        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");

        try {
            java.util.Date date = format.parse(request.getParameter("client_bday"));
            java.sql.Date sqlDate = new java.sql.Date(date.getTime());
            client.setBirthday(sqlDate);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        clientManager.updateClient(client);

        resp.sendRedirect("pages/books.jsp?genre=all");
    }
}
