package com.tsystems.javaschool.view.servlets.order;

import com.tsystems.javaschool.dao.entity.Order;
import com.tsystems.javaschool.services.enums.OrderStatus;
import com.tsystems.javaschool.services.util.Managers;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author Alexander Dvortsov
 * @version 1.0
 * @since 23.02.2016
 */
public class SetOrderStatusServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Order orderForEdit = Managers.getOrderManager().
                findOrderById(Long.parseLong(req.getParameter("order_id")));
        OrderStatus newOrderStatus = OrderStatus.valueOf(req.getParameter("order_status"));

        orderForEdit.setOrderStatus(newOrderStatus);

        Managers.getOrderManager().updateOrder(orderForEdit);

        resp.sendRedirect("admin_pages/admin.jsp");
    }
}
