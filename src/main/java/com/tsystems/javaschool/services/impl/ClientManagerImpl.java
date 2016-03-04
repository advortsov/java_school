package com.tsystems.javaschool.services.impl;

import com.tsystems.javaschool.dao.entity.Client;
import com.tsystems.javaschool.dao.entity.Order;
import com.tsystems.javaschool.dao.exeption.NotRegisteredUserException;
import com.tsystems.javaschool.dao.impl.OrderDAOImpl;
import com.tsystems.javaschool.dao.interfaces.ClientDAO;
import com.tsystems.javaschool.dao.interfaces.OrderDAO;
import com.tsystems.javaschool.dao.util.Daos;
import com.tsystems.javaschool.dao.util.JpaUtil;
import com.tsystems.javaschool.services.interfaces.ClientManager;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceException;
import javax.persistence.Query;
import java.util.List;

/**
 * @author Alexander Dvortsov
 * @version 1.0
 * @since 17.02.2016
 */
public class ClientManagerImpl implements ClientManager{
    private ClientDAO clientDAO = Daos.getClientDAO();

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
        EntityManager em = null;
        try {
            em = JpaUtil.beginTransaction();
            clientDAO.merge(client, em);
            JpaUtil.commitTransaction(em);
        } catch (PersistenceException ex) {
            ex.printStackTrace();
            JpaUtil.rollbackTransaction(em);
        }
    }

    @Override
    public List<Order> getClientOrders(Client currClient) {
        OrderDAO orderDAO = Daos.getOrderDAO();
        List<Order> orders = null;
        String sql = "SELECT o FROM Order o WHERE o.client = :currClient";
        EntityManager em = JpaUtil.getEntityManager();
        Query query = em.createQuery(sql).
                setParameter("currClient", currClient);
        orders = orderDAO.findMany(query);
        em.close();
        return orders;
    }
}
