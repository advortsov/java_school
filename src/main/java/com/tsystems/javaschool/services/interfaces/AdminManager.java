package com.tsystems.javaschool.services.interfaces;

import com.tsystems.javaschool.dao.entity.Book;
import com.tsystems.javaschool.dao.entity.Client;
import com.tsystems.javaschool.dao.entity.Order;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @author Alexander Dvortsov
 * @version 1.0
 * @since 11.02.2016
 */
public interface AdminManager {

    public Map<Book, Integer> getTopTenBooks();

    public Map<Client, Integer> getTopTenClients();

    public List<Order> getOrdersPerPeriod(Date periodStart, Date periodEnd);

}
