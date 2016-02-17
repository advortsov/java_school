package com.tsystems.javaschool.view.servlets;

import com.tsystems.javaschool.dao.entity.Book;
import com.tsystems.javaschool.dao.entity.OrderLine;
import com.tsystems.javaschool.services.ShoppingCart;
import com.tsystems.javaschool.services.impl.BookManagerImpl;
import com.tsystems.javaschool.services.impl.ShoppingCartManagerImpl;
import com.tsystems.javaschool.services.interfaces.BookManager;
import com.tsystems.javaschool.services.interfaces.ShoppingCartManager;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * @author Alexander Dvortsov
 * @version 1.0
 * @since 17.02.2016
 */
public class AddToCartServlet extends HttpServlet{
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ShoppingCart cart = (ShoppingCart) req.getSession().getAttribute("cart"); // где то надо бы ее записать
        List<OrderLine> orderLines = cart.getItems();
        BookManager bookManager = new BookManagerImpl();

        Book newBook = bookManager.findBookById(Long.parseLong(req.getParameter("book_id")));
        OrderLine orderLineWithThisBook = null;

        for (OrderLine orderLine : orderLines){ // ищем, есть ли уже такая книга в заказе
            if(orderLine.getBook().equals(newBook)){
                orderLineWithThisBook = orderLine;
            }
        }

        int previousQuantity = 0;
        if (orderLineWithThisBook == null){
            orderLines.add(new OrderLine(1, newBook)); //одну книгу добавляем может сетом реализовать? или мапой?
        } else {
            previousQuantity = orderLineWithThisBook.getQuantity();
            for (OrderLine orderLine : orderLines){ // ищем, есть ли уже такая книга в заказе
                if(orderLine.getBook().equals(newBook)){
                    orderLine.setQuantity(++previousQuantity);
                }
            }
        }

        ShoppingCartManager shoppingCartManager = new ShoppingCartManagerImpl();
        shoppingCartManager.deleteExistingCookies(newBook.getId(), req);

        Cookie cookie = new Cookie("book", newBook.getId()+"qty"+ previousQuantity++);

        cart.setItems(orderLines);
        req.getSession().setAttribute("cart", cart);

        resp.sendRedirect("pages/books.jsp?genre=all");

    }
}
