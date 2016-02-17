package com.tsystems.javaschool.services.interfaces;


import com.tsystems.javaschool.dao.entity.Book;
import com.tsystems.javaschool.dao.entity.Order;

import javax.servlet.http.HttpServletRequest;

/**
 * @author Alexander Dvortsov
 * @version 1.0
 * @since 09.02.2016
 */
public interface ShoppingCartManager {
    public void addBook(Book book, int amount);
    public void setBookAmount(Book book, int amount);
    public void removeLine(Book book);
    public void clearCart();
    public Order createOrder();
    public boolean isEnoughBooksInStock(int quantity);

    public void fillUpFromCookies(HttpServletRequest request);

    public void deleteExistingCookies(long id, HttpServletRequest req);
}
