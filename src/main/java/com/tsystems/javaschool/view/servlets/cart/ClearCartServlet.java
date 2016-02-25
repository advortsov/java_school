package com.tsystems.javaschool.view.servlets.cart;

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
public class ClearCartServlet extends HttpServlet {
    public static void clearCartAndCookies(HttpServletRequest req, HttpServletResponse resp) {
        ShoppingCartManager shoppingCartManager
                = (ShoppingCartManager) req.getSession().getAttribute("cartManager");

        if (shoppingCartManager != null) {
            shoppingCartManager.clearCart();
        }
        CartController.deleteCartsBooksCookies(req, resp);

        req.setAttribute("cartManager", shoppingCartManager);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        clearCartAndCookies(req, resp);
        resp.sendRedirect("pages/cart.jsp");
    }
}
