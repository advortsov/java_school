package com.tsystems.javaschool.dao.entity;


/**
 * @author Alexander Dvortsov
 * @version 1.0
 * @since 09.02.2016
 *
 * POJO
 */

public class OrderLine {

    private long id;
    private int quantity;
    private Book book;
    private Order order; // onetomany

    public OrderLine() {
    }

    public OrderLine(int quantity, Book book) {
        this.quantity = quantity;
        this.book = book;
    }

    public long getId() {
        return id;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }
}
