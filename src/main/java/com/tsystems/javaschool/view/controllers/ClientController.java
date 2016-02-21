package com.tsystems.javaschool.view.controllers;

import com.tsystems.javaschool.dao.entity.Client;
import com.tsystems.javaschool.services.impl.ClientManagerImpl;
import com.tsystems.javaschool.services.interfaces.ClientManager;

import javax.persistence.NoResultException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * @author Alexander Dvortsov
 * @version 1.0
 * @since 21.02.2016
 */
public class ClientController {

    public static Client actualizeClient(HttpServletRequest request, String userName){
        ClientManager clientManager = new ClientManagerImpl();
        HttpSession session = request.getSession();

        Client client = null;
        try {
            client = clientManager.findByUserName(userName);
        } catch (NoResultException ex){
            //ignore
        }

        if (client == null || userName == "") {
            userName = "Anonymous";
            session.setAttribute("username", userName);
            client = clientManager.findByUserName(userName);
        }
        session.setAttribute("currentClient", client);
        // теперь у нас в сессии есть наш клиент из базы

        return client;
    }
}
