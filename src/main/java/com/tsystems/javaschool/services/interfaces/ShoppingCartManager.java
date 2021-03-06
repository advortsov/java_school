package com.tsystems.javaschool.services.interfaces;


import com.tsystems.javaschool.dao.entity.Book;
import com.tsystems.javaschool.services.ShoppingCart;

import javax.servlet.http.HttpServletRequest;

/**
 * @author Alexander Dvortsov
 * @version 1.0
 * @since 09.02.2016
 */
public interface ShoppingCartManager {
    void addBook(Book book, int amount);

    void setBookAmount(Book book, int amount);

    void removeLine(Book book);

    void clearCart();

    boolean isEnoughBooksInStock(int quantity);

    ShoppingCart getShoppingCart();

    void setShoppingCart(ShoppingCart shoppingCart);
}
