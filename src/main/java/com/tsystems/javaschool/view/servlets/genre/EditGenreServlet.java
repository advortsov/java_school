package com.tsystems.javaschool.view.servlets.genre;

import com.tsystems.javaschool.dao.entity.Genre;
import com.tsystems.javaschool.services.exception.DuplicateException;
import com.tsystems.javaschool.services.interfaces.GenreManager;
import com.tsystems.javaschool.services.util.Managers;

import javax.persistence.NoResultException;
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
public class EditGenreServlet extends HttpServlet {

    private String status = "admin_pages/admin.jsp#tab2";
    private String notOk = "error/duplicate.html";

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setCharacterEncoding("UTF-8");
        GenreManager genreManager = Managers.getGenreManager();
        Genre genre = genreManager.findByGenreName(req.getParameter("genre_for_edit"));
        try {
            verifyGenreName(req.getParameter("genre_name"));
        } catch (DuplicateException e) {
            throw new IOException();
        }
        genre.setName(req.getParameter("genre_name"));
        genreManager.updateGenre(genre);
        resp.sendRedirect(status);
    }

    private void verifyGenreName(String value) throws DuplicateException {
        Genre genre = null;
        try {
            genre = Managers.getGenreManager().findGenreByName(value);
        } catch (NoResultException exception) {
            genre = null;
        }
        if (genre != null) throw new DuplicateException();
    }
}
