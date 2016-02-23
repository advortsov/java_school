package com.tsystems.javaschool.services.interfaces;

import com.tsystems.javaschool.dao.entity.Client;
import com.tsystems.javaschool.dao.entity.Order;

import java.util.List;

/**
 * @author Alexander Dvortsov
 * @version 1.0
 * @since 17.02.2016
 */
public interface ClientManager {
    public Client findByUserName(String name);

    public void updateClient(Client client);

    List<Order> getClientOrders(Client client);
}
