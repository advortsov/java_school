package com.tsystems.javaschool.services.impl;

import com.tsystems.javaschool.dao.entity.Client;
import com.tsystems.javaschool.dao.impl.ClientDAOImpl;
import com.tsystems.javaschool.dao.interfaces.ClientDAO;
import com.tsystems.javaschool.services.interfaces.ClientManager;

/**
 * @author Alexander Dvortsov
 * @version 1.0
 * @since 17.02.2016
 */
public class ClientManagerImpl implements ClientManager{
    private ClientDAO clientDAO = new ClientDAOImpl();

    @Override
    public Client findByUserName(String name) {
        return clientDAO.findByUserName(name);
    }
}
