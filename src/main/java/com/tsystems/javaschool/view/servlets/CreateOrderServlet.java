package com.tsystems.javaschool.view.servlets;

import com.tsystems.javaschool.dao.entity.Client;
import com.tsystems.javaschool.dao.entity.Order;
import com.tsystems.javaschool.dao.entity.OrderLine;
import com.tsystems.javaschool.services.enums.PaymentStatus;
import com.tsystems.javaschool.services.enums.PaymentType;
import com.tsystems.javaschool.services.enums.ShippingType;
import com.tsystems.javaschool.services.impl.OrderManagerImpl;
import com.tsystems.javaschool.services.interfaces.OrderManager;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * @author Alexander Dvortsov
 * @version 1.0
 * @since 17.02.2016
 */
public class CreateOrderServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        List<OrderLine> orderLines = (List<OrderLine>) req.getSession().getAttribute("orderLines");

        // заданное на прошлой форме количество перезаписываем в каждом ордерлайне

        for (OrderLine orderLine : orderLines){
            orderLine.setQuantity(Integer.parseInt(req.getParameter("q-"+orderLine.getBook().getId())));
        }

        Order order = new Order();
        order.setOrderLines(orderLines);
        order.setShippingType(ShippingType.valueOf(req.getParameter("shipping_type")));
        order.setPaymentType(PaymentType.valueOf(req.getParameter("payment_type")));
        order.setPaymentStatus(PaymentStatus.WAITING_FOR_PAYMENT); // потому что заказ только что создан

        Client client = (Client) req.getSession().getAttribute("client");
        order.setClient(client);

        OrderManager orderManager = new OrderManagerImpl();
        orderManager.saveNewOrder(order);

    }
}
