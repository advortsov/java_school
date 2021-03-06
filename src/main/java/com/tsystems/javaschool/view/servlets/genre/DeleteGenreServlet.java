package com.tsystems.javaschool.view.servlets.genre;

import com.tsystems.javaschool.dao.entity.Genre;
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
public class DeleteGenreServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setCharacterEncoding("UTF-8");
        GenreManager genreManager = Managers.getGenreManager();
        Genre genreForDel = genreManager.findByGenreName(req.getParameter("genre_name"));
        genreManager.deleteGenre(genreForDel);
        resp.sendRedirect("admin_pages/admin.jsp#tab2");
    }
}
