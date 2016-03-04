package com.tsystems.javaschool.view.servlets.genre;

import com.tsystems.javaschool.dao.entity.Genre;
import com.tsystems.javaschool.services.exception.DuplicateException;
import com.tsystems.javaschool.services.impl.GenreManagerImpl;
import com.tsystems.javaschool.services.interfaces.GenreManager;
import com.tsystems.javaschool.services.util.Managers;

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

    private String status = "admin_pages/admin.jsp#tab2";
    private String notOk = "error/duplicate.html";

    private String message = "";

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        resp.setCharacterEncoding("UTF-8");
        GenreManager genreManager = Managers.getGenreManager();
        Genre newGenre = new Genre(req.getParameter("genre_name"));

        try {
            genreManager.saveNewGenre(newGenre);
        } catch (DuplicateException ex) {
            message = "Невозможно добавить дубликат!";
            //status = notOk;
        }

        resp.setContentType("text/plain");
        resp.setCharacterEncoding("UTF-8");
        resp.getWriter().write(message);

        resp.sendRedirect(status);
    }
}
