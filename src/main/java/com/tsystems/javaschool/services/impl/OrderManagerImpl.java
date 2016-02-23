package com.tsystems.javaschool.services.impl;

import com.tsystems.javaschool.dao.entity.Order;
import com.tsystems.javaschool.dao.entity.OrderLine;
import com.tsystems.javaschool.dao.impl.BookDAOImpl;
import com.tsystems.javaschool.dao.impl.OrderDAOImpl;
import com.tsystems.javaschool.dao.interfaces.BookDAO;
import com.tsystems.javaschool.dao.interfaces.OrderDAO;
import com.tsystems.javaschool.dao.util.JpaUtil;
import com.tsystems.javaschool.services.interfaces.OrderManager;

import javax.persistence.PersistenceException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * @author Alexander Dvortsov
 * @version 1.0
 * @since 17.02.2016
 */
public class OrderManagerImpl implements OrderManager {

    private OrderDAO orderDAO = new OrderDAOImpl();
    private BookDAO bookDAO = new BookDAOImpl();

    @Override
    public Order findByGenreName(String name) {
        return null;
    }

    @Override
    public List<Order> loadAllOrders() {
        return orderDAO.findAll(Order.class);
    }

    @Override
    public void saveNewOrder(Order order) {
        try {
            JpaUtil.beginTransaction();
            deductBooksFromStore(order); // reserve books from the store, because the order was placed
            orderDAO.save(order);
            JpaUtil.commitTransaction();
        } catch (PersistenceException ex) {
            ex.printStackTrace();
            JpaUtil.rollbackTransaction();
        }
    }

    @Override
    public void deductBooksFromStore(Order order) {
        List<OrderLine> orderLines = order.getOrderLines();
        for (OrderLine orderLine : orderLines) {
            long bookId = orderLine.getBook().getId();
            int quantity = orderLine.getQuantity();
            bookDAO.setBookQuantity(bookId, quantity);
        }
    }


    @Override
    public Order findOrderById(long id) {
        return orderDAO.findByID(Order.class, id);
    }

    @Override
    public void deleteOrder(Order order) {
        throw new UnsupportedOperationException();
    }

    public int orderTotalSumm(Order order) {
        List<OrderLine> orderLines = order.getOrderLines();
        int totalSumm = 0;
        for (OrderLine orderLine : orderLines) {
            totalSumm += (orderLine.getBook().getPrice() * orderLine.getQuantity());
        }
        return totalSumm;
    }

    @Override
    public void updateOrder(Order order) {
        try {
            JpaUtil.beginTransaction();
            orderDAO.merge(order);
            JpaUtil.commitTransaction();
        } catch (PersistenceException ex) {
            Logger.getLogger(BookManagerImpl.class.getName()).log(Level.SEVERE, null, ex);
            ex.printStackTrace();
            JpaUtil.rollbackTransaction();
        }
    }

}
