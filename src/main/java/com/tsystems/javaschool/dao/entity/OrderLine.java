package com.tsystems.javaschool.dao.entity;


import javax.persistence.*;

/**
 * @author Alexander Dvortsov
 * @version 1.0
 * @since 09.02.2016
 *
 * POJO
 */

@Entity
@Table(name="order_line")//
//@Embeddable
@NamedQuery(name = "OrderLine.getAll", query = "SELECT b from OrderLine b")
public class OrderLine {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @ManyToOne(cascade = CascadeType.DETACH) // много линий в одном заказе
    @JoinColumn(name="order_id", nullable = false)
    private Order order;

    @Column(name = "quantity", nullable = false)
    private int quantity;

    @OneToOne(cascade = CascadeType.DETACH)
    @JoinColumn(name="book_id", nullable = false)
    private Book book;

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

    @OneToOne
    @JoinColumn(name = "book_id")
    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }

    @ManyToOne
    @JoinColumn(name = "order_id")
    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }
}
