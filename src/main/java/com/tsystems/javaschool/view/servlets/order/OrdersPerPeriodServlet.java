package com.tsystems.javaschool.view.servlets.order;

import com.tsystems.javaschool.dao.entity.Order;
import com.tsystems.javaschool.services.util.Managers;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

/**
 * @author Alexander Dvortsov
 * @version 1.0
 * @since 01.03.2016
 */
public class OrdersPerPeriodServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");

        try {
            java.util.Date start = format.parse(req.getParameter("start_date"));
            java.util.Date end = format.parse(req.getParameter("end_date"));
            java.sql.Date sqlStartDate = new java.sql.Date(start.getTime());
            java.sql.Date sqlEndDate = new java.sql.Date(end.getTime());
            List<Order> ordersPerPeriod = Managers.getAdminManager().getOrdersPerPeriod(sqlStartDate, sqlEndDate);
            req.getSession().setAttribute("ordersPerPeriod", ordersPerPeriod);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        resp.sendRedirect("admin_pages/admin.jsp#tab7");
    }
}
