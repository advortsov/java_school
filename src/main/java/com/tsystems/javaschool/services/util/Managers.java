package com.tsystems.javaschool.services.util;

import com.tsystems.javaschool.services.impl.*;
import com.tsystems.javaschool.services.interfaces.*;

/**
 * @author Alexander Dvortsov
 * @version 1.0
 * @since 23.02.2016
 */
public class Managers {

    private static AdminManager adminManager;
    private static AuthorManager authorManager;
    private static BookManager bookManager;
    private static ClientManager clientManager;
    private static GenreManager genreManager;
    private static OrderManager orderManager;
    private static PublisherManager publisherManager;
    private static ShoppingCartManager shoppingCartManager;

    public static AdminManager getAdminManager() {
        if (adminManager == null) adminManager = new AdminManagerImpl();
        return adminManager;
    }

    public static AuthorManager getAuthorManager() {
        if (authorManager == null) authorManager = new AuthorManagerImpl();
        return authorManager;
    }

    public static BookManager getBookManager() {
        if (bookManager == null) bookManager = new BookManagerImpl();
        return bookManager;
    }

    public static ClientManager getClientManager() {
        if (clientManager == null) clientManager = new ClientManagerImpl();
        return clientManager;
    }

    public static GenreManager getGenreManager() {
        if (genreManager == null) genreManager = new GenreManagerImpl();
        return genreManager;
    }

    public static OrderManager getOrderManager() {
        if (orderManager == null) orderManager = new OrderManagerImpl();
        return orderManager;
    }

    public static PublisherManager getPublisherManager() {
        if (publisherManager == null) publisherManager = new PublisherManagerImpl();
        return publisherManager;
    }

    public static ShoppingCartManager getShoppingCartManager() {
        if (shoppingCartManager == null) shoppingCartManager = new ShoppingCartManagerImpl();
        return shoppingCartManager;
    }

}
