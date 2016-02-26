package com.tsystems.javaschool.services.impl;

import com.tsystems.javaschool.dao.entity.Client;
import com.tsystems.javaschool.dao.entity.Order;
import com.tsystems.javaschool.dao.exeption.NotRegisteredUserException;
import com.tsystems.javaschool.dao.impl.ClientDAOImpl;
import com.tsystems.javaschool.dao.impl.OrderDAOImpl;
import com.tsystems.javaschool.dao.interfaces.ClientDAO;
import com.tsystems.javaschool.dao.interfaces.OrderDAO;
import com.tsystems.javaschool.dao.util.JpaUtil;
import com.tsystems.javaschool.services.interfaces.ClientManager;

import javax.persistence.PersistenceException;
import javax.persistence.Query;
import java.util.List;
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
    public Client findByUserName(String name) throws NotRegisteredUserException {
        return clientDAO.findByUserName(name);
    }
    @Override
    public Client findById(long id) throws NotRegisteredUserException {
        return clientDAO.findByID(Client.class, id);
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

    @Override
    public List<Order> getClientOrders(Client currClient) {
        OrderDAO orderDAO = new OrderDAOImpl();
        List<Order> orders = null;
        String sql = "SELECT o FROM Order o WHERE o.client = :currClient";
        Query query = JpaUtil.getEntityManager().createQuery(sql).
                setParameter("currClient", currClient);
        orders = orderDAO.findMany(query);
        return orders;
    }
}
