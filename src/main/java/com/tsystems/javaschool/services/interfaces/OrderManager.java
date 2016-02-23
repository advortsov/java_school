package com.tsystems.javaschool.services.interfaces;

import com.tsystems.javaschool.dao.entity.Client;
import com.tsystems.javaschool.dao.entity.Order;

import java.util.List;

/**
 * @author Alexander Dvortsov
 * @version 1.0
 * @since 17.02.2016
 */
public interface OrderManager {
    public Order findByGenreName(String name);

    public List<Order> loadAllOrders();

    public void saveNewOrder(Order order);

    public Order findOrderById(long id);

    public void deleteOrder(Order order);

    public int orderTotalSumm(Order order);

    void updateOrder(Order order);

    void deductBooksFromStore(Order order);

}
