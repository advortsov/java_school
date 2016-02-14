package com.tsystems.javaschool.view.controllers;

import com.tsystems.javaschool.services.impl.GenreManagerImpl;
import com.tsystems.javaschool.services.interfaces.GenreManager;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author Alexander Dvortsov
 * @version 1.0
 * @since 12.02.2016
 */
public class GenreListController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        GenreManager genreManager = new GenreManagerImpl();
        genreManager.loadAllGenres();


    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doPost(req, resp);
    }
}
