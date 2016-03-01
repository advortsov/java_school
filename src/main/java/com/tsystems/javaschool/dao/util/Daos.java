package com.tsystems.javaschool.dao.util;

import com.tsystems.javaschool.dao.impl.*;
import com.tsystems.javaschool.dao.interfaces.*;

/**
 * The main purpose of this class is to provide a single
 * copy of each DAO class to using in Service layer
 *
 * @author Alexander Dvortsov
 * @version 1.0
 * @since 01.03.2016
 */
public class Daos {

    private static AuthorDAO authorDAO;
    private static BookDAO bookDAO;
    private static ClientDAO clientDAO;
    private static GenericDAO genericDAO;
    private static GenreDAO genreDAO;
    private static OrderDAO orderDAO;
    private static PublisherDAO publisherDAO;

    public static AuthorDAO getAuthorDAO() {
        if (authorDAO == null) authorDAO = new AuthorDAOImpl();
        return authorDAO;
    }

    public static BookDAO getBookDAO() {
        if (bookDAO == null) bookDAO = new BookDAOImpl();
        return bookDAO;
    }

    public static ClientDAO getClientDAO() {
        if (clientDAO == null) clientDAO = new ClientDAOImpl();
        return clientDAO;
    }

    public static GenericDAO getGenericDAO() {
        if (genericDAO == null) genericDAO = new GenreDAOImpl();
        return genericDAO;
    }

    public static GenreDAO getGenreDAO() {
        if (genreDAO == null) genreDAO = new GenreDAOImpl();
        return genreDAO;
    }

    public static OrderDAO getOrderDAO() {
        if (orderDAO == null) orderDAO = new OrderDAOImpl();
        return orderDAO;
    }

    public static PublisherDAO getPublisherDAO() {
        if (publisherDAO == null) publisherDAO = new PublisherDAOImpl();
        return publisherDAO;
    }

}
