package com.tsystems.javaschool.services.impl;



import com.tsystems.javaschool.dao.entity.Book;
import com.tsystems.javaschool.dao.entity.Order;
import com.tsystems.javaschool.dao.entity.OrderLine;
import com.tsystems.javaschool.services.ShoppingCart;
import com.tsystems.javaschool.services.interfaces.ShoppingCartManager;

import java.util.Iterator;
import java.util.List;

/**
 * @author Alexander Dvortsov
 * @version 1.0
 * @since 09.02.2016
 */
public class ShoppingCartManagerImpl implements ShoppingCartManager {

    private ShoppingCart shoppingCart;

    public ShoppingCartManagerImpl(ShoppingCart shoppingCart) {
        this.shoppingCart = shoppingCart;
    }

    public ShoppingCart getShoppingCart() {
        return shoppingCart;
    }

    public void setShoppingCart(ShoppingCart shoppingCart) {
        this.shoppingCart = shoppingCart;
    }

    @Override
    public void addBook(Book book, int amount) {
        List<OrderLine> lines = shoppingCart.getItems();
        lines.add(new OrderLine(amount, book));
        shoppingCart.setItems(lines);
    }

    @Override
    public void setBookAmount(Book book, int amount) {
        List<OrderLine> currentLines = shoppingCart.getItems();
        for (OrderLine line : currentLines){
            if (line.getBook().equals(book.getId())){
                line.setQuantity(amount);
                break;
            }
        }

    }

    @Override
    public void removeLine(Book book){
        List<OrderLine> currentLines = shoppingCart.getItems();
        Iterator<OrderLine> i = currentLines.iterator();
        while (i.hasNext()) {
            OrderLine orderLine = i.next();
            if (orderLine.getBook().equals(book)){
                i.remove();
            }
        }
    }

    @Override
    public void clearCart() {
        List<OrderLine> items = shoppingCart.getItems();
        items.clear();
        shoppingCart.setItems(items);
    }

    // writing into DB
    @Override
    public Order createOrder() {
        //Order order = new Order(shoppingCart.getClient(), )
        return null;
    }
}
