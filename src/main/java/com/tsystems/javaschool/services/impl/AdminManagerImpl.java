package com.tsystems.javaschool.services.impl;

import com.tsystems.javaschool.dao.entity.Client;
import com.tsystems.javaschool.dao.entity.Order;
import com.tsystems.javaschool.services.interfaces.AdminManager;

import java.util.Date;
import java.util.List;

/**
 * @author Alexander Dvortsov
 * @version 1.0
 * @since 11.02.2016
 */
public class AdminManagerImpl implements AdminManager {
    @Override
    public List<Order> getTopTenBooks() {
        return null;
    }

    @Override
    public List<Client> getTopTenClients() {
        return null;
    }

    @Override
    public List<Order> getRevenuePerPeriod(Date periodStart, Date periodEnd) {
        return null;
    }
}
