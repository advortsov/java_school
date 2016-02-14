package com.tsystems.javaschool.services.interfaces;

import com.tsystems.javaschool.dao.entity.Client;
import com.tsystems.javaschool.dao.entity.Order;

import java.util.Date;
import java.util.List;

/**
 * @author Alexander Dvortsov
 * @version 1.0
 * @since 11.02.2016
 */
public interface AdminManager {

    public List<Order> getTopTenBooks();
    public List<Client> getTopTenClients();
    public List<Order> getRevenuePerPeriod(Date periodStart, Date periodEnd);

}
