package com.tsystems.javaschool.view.servlets.genre;

import com.tsystems.javaschool.dao.entity.Genre;
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
 * @since 21.02.2016
 */
public class AddGenreServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setCharacterEncoding("UTF-8");
        GenreManager genreManager = new GenreManagerImpl();

        Genre newGenre = new Genre(req.getParameter("genre_name"));
        genreManager.saveNewGenre(newGenre);

        resp.sendRedirect("pages/admin.jsp");
    }
}
