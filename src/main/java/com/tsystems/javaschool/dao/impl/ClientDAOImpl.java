package com.tsystems.javaschool.dao.impl;

import com.tsystems.javaschool.dao.entity.Client;
import com.tsystems.javaschool.dao.exeption.NotRegisteredUserException;
import com.tsystems.javaschool.dao.interfaces.ClientDAO;
import com.tsystems.javaschool.dao.util.JpaUtil;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.Query;

/**
 * @author Alexander Dvortsov
 * @version 1.0
 * @since 17.02.2016
 */
public class ClientDAOImpl extends GenericDAOImpl<Client, Long> implements ClientDAO {
    @Override
    public Client findByUserName(String name) throws NotRegisteredUserException {
        Client client = null;
        String sql = "SELECT c FROM Client c WHERE c.user.userName = :name";
        EntityManager em = JpaUtil.getEntityManager();
        Query query = em.createQuery(sql).
                setParameter("name", name);
        try {
            client = findOne(query);
        } catch (NoResultException ex){
            throw new NotRegisteredUserException();
        }
        em.close();
        return client;
    }
}
