package com.tsystems.javaschool.view.servlets.cart;

import com.tsystems.javaschool.dao.entity.Book;
import com.tsystems.javaschool.services.impl.BookManagerImpl;
import com.tsystems.javaschool.services.interfaces.BookManager;
import com.tsystems.javaschool.services.interfaces.ShoppingCartManager;
import com.tsystems.javaschool.view.controllers.CartController;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author Alexander Dvortsov
 * @version 1.0
 * @since 22.02.2016
 */
public class RemoveOrderLineServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        BookManager bookManager = new BookManagerImpl();

        Book book = bookManager.findBookById(Long.parseLong(req.getParameter("id")));
        ShoppingCartManager shoppingCartManager
                = (ShoppingCartManager) req.getSession().getAttribute("cartManager");
        shoppingCartManager.removeLine(book);
        CartController.deleteOrderLineCookie(book.getId(), req, resp);

        req.setAttribute("cartManager", shoppingCartManager);
        resp.sendRedirect("pages/cart.jsp");
    }
}
