package com.tsystems.javaschool.view.servlets;

import com.tsystems.javaschool.dao.entity.Client;
import com.tsystems.javaschool.dao.entity.Order;
import com.tsystems.javaschool.dao.entity.OrderLine;
import com.tsystems.javaschool.services.enums.OrderStatus;
import com.tsystems.javaschool.services.enums.PaymentStatus;
import com.tsystems.javaschool.services.enums.PaymentType;
import com.tsystems.javaschool.services.enums.ShippingType;
import com.tsystems.javaschool.services.exception.NotEnoughBooksInTheStockException;
import com.tsystems.javaschool.services.impl.BookManagerImpl;
import com.tsystems.javaschool.services.impl.OrderManagerImpl;
import com.tsystems.javaschool.services.interfaces.BookManager;
import com.tsystems.javaschool.services.interfaces.OrderManager;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Date;
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

        BookManager bookManager = new BookManagerImpl();
        // заданное на прошлой форме количество перезаписываем в каждом ордерлайне

        Order order = new Order();

        for (OrderLine orderLine : orderLines){

            //validation:
            int wantedQuantity = Integer.parseInt(req.getParameter("q-"+orderLine.getBook().getId()));
            if (wantedQuantity > bookManager.getBookQuantity(orderLine.getBook().getId()) ){
                try {
                    throw new NotEnoughBooksInTheStockException(wantedQuantity);
                } catch (NotEnoughBooksInTheStockException e) {
                    e.printStackTrace();
                    //resp.sendRedirect("pages/cart.jsp");
                }
            } else {
                orderLine.setQuantity(wantedQuantity);
                orderLine.setOrder(order);
            }
        }

        order.setOrderLines(orderLines);
        order.setShippingType(ShippingType.valueOf(req.getParameter("shipping_type")));
        order.setPaymentType(PaymentType.valueOf(req.getParameter("payment_type")));
        order.setPaymentStatus(PaymentStatus.WAITING_FOR_PAYMENT); // потому что заказ только что создан
        order.setOrderStatus(OrderStatus.WAITING_FOR_PAYMENT); // потому что заказ только что создан
        order.setClient((Client) req.getSession().getAttribute("currentClient"));
        order.setDate(new Date(System.currentTimeMillis()));

        Client client = (Client) req.getSession().getAttribute("currentClient");
        order.setClient(client);

        OrderManager orderManager = new OrderManagerImpl();
        orderManager.saveNewOrder(order);

        resp.sendRedirect("pages/cart.jsp");
    }
}
