package com.tsystems.javaschool.dao.interfaces;


import com.tsystems.javaschool.dao.entity.Client;
import com.tsystems.javaschool.dao.exeption.NotRegisteredUserException;

/**
 * @author Alexander Dvortsov
 * @version 1.0
 * @since 09.02.2016
 */
public interface ClientDAO extends GenericDAO<Client, Long> {
    Client findByUserName(String name) throws NotRegisteredUserException;

}
