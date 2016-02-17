package com.tsystems.javaschool.services.interfaces;

import com.tsystems.javaschool.dao.entity.Client;

/**
 * @author Alexander Dvortsov
 * @version 1.0
 * @since 17.02.2016
 */
public interface ClientManager {
    public Client findByUserName(String name);

}
