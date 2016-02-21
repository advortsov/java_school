package com.tsystems.javaschool.services.impl;



import com.tsystems.javaschool.dao.entity.Book;
import com.tsystems.javaschool.dao.entity.Order;
import com.tsystems.javaschool.dao.entity.OrderLine;
import com.tsystems.javaschool.services.ShoppingCart;
import com.tsystems.javaschool.services.interfaces.BookManager;
import com.tsystems.javaschool.services.interfaces.ShoppingCartManager;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

/**
 * @author Alexander Dvortsov
 * @version 1.0
 * @since 09.02.2016
 */
public class ShoppingCartManagerImpl implements ShoppingCartManager {

    private ShoppingCart shoppingCart;

    public ShoppingCartManagerImpl() {
    }

    BookManager bookManager = new BookManagerImpl();


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


    @Override
    public boolean isEnoughBooksInStock(int id) {
        BookManager bookManager = new BookManagerImpl();

        for (OrderLine orderLine : shoppingCart.getItems()){
            int wantedQuantity = orderLine.getQuantity();
            int storeQuantity = bookManager.getBookQuantity(orderLine.getBook().getId());

            if (wantedQuantity > storeQuantity){
                //throw new NotEnoughBooksInTheStockException(quantity);
                return false;
            }
        }
        return true;
    }

    @Override
    public void fillUpFromCookies(HttpServletRequest request) {
        Cookie[] cookies = request.getCookies();

        List<OrderLine> newItems = new ArrayList<>();
        // заполняем по куки корзину

        if (cookies != null){
            for (Cookie cookie : cookies){
                String value  = cookie.getValue();
                if (value.contains("qty")) {
                    String[] arr = value.split("qty");
                    long id = Long.parseLong(arr[0]);
                    int quantity = Integer.parseInt(arr[1]);

                    newItems.add(new OrderLine(quantity, bookManager.findBookById(id)));
                }
            }
        }

        if (!newItems.isEmpty()){
            shoppingCart.setItems(newItems);
        }

    }

    @Override
    public void deleteExistingCookies(long bookId, HttpServletRequest req) {
        // удаляем куки с таким же айди, чтобы перезаписать количество
        Cookie[] cookies = req.getCookies();
        for (Cookie cookie : cookies){
            String value  = cookie.getValue();
            if (value.contains("qty")) {
                String[] arr = value.split("qty");
                long id = Long.parseLong(arr[0]);
                if (id == bookId) {
                    cookie.setMaxAge(0);
                }
            }
        }
    }


}
