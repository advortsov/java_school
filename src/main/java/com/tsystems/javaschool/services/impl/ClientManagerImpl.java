package com.tsystems.javaschool.services.impl;

import com.tsystems.javaschool.dao.entity.Client;
import com.tsystems.javaschool.dao.impl.ClientDAOImpl;
import com.tsystems.javaschool.dao.interfaces.ClientDAO;
import com.tsystems.javaschool.dao.util.JpaUtil;
import com.tsystems.javaschool.services.interfaces.ClientManager;

import javax.persistence.PersistenceException;
import java.util.logging.Level;
import java.util.logging.Logger;

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

    @Override
    public void updateClient(Client client) {
        try {
            JpaUtil.beginTransaction();
            clientDAO.merge(client);
            JpaUtil.commitTransaction();
        } catch (PersistenceException ex) {
            Logger.getLogger(ClientManagerImpl.class.getName()).log(Level.SEVERE, null, ex);
            ex.printStackTrace();
            JpaUtil.rollbackTransaction();
        }
    }
}
