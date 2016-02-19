package com.tsystems.javaschool.view.servlets;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * @author Alexander Dvortsov
 * @version 1.0
 * @since 19.02.2016
 */
public class LogoutServlet extends HttpServlet {
        @Override
        protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
            HttpSession session = request.getSession(false);

            // Destroys the session for this user.
            if (session != null)
                session.invalidate();

            // Redirects back to the initial page.
            response.sendRedirect("pages/main.jsp");
        }
}
