package com.tsystems.javaschool.services.impl;

import com.tsystems.javaschool.dao.entity.Order;
import com.tsystems.javaschool.dao.impl.OrderDAOImpl;
import com.tsystems.javaschool.dao.interfaces.OrderDAO;
import com.tsystems.javaschool.dao.util.JpaUtil;
import com.tsystems.javaschool.services.interfaces.OrderManager;

import javax.persistence.PersistenceException;
import java.util.List;

/**
 * @author Alexander Dvortsov
 * @version 1.0
 * @since 17.02.2016
 */
public class OrderManagerImpl implements OrderManager {

    private OrderDAO orderDAO = new OrderDAOImpl();

    @Override
    public Order findByGenreName(String name) {
        return null;
    }

    @Override
    public List<Order> loadAllOrders() {
        return null;
    }

    @Override
    public void saveNewOrder(Order order) {
        try {
            JpaUtil.beginTransaction();
            orderDAO.save(order);
            JpaUtil.commitTransaction();
        } catch (PersistenceException ex) {
            ex.printStackTrace();
            JpaUtil.rollbackTransaction();
        }
    }

    @Override
    public Order findOrderById(long id) {
        return null;
    }

    @Override
    public void deleteOrder(Order order) {

    }
}
