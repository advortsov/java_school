package com.tsystems.javaschool.dao.entity;

import com.tsystems.javaschool.services.enums.OrderStatus;
import com.tsystems.javaschool.services.enums.PaymentStatus;
import com.tsystems.javaschool.services.enums.PaymentType;
import com.tsystems.javaschool.services.enums.ShippingType;

import javax.persistence.*;
import java.util.Collection;
import java.util.Date;
import java.util.List;

/**
 * @author Alexander Dvortsov
 * @version 1.0
 * @since 09.02.2016
 *
 *

 * org.hibernate.MappingException:
 * Could not determine type for:
 * com.tsystems.javaschool.dao.entity.Client, at table: order, for columns: [org.hibernate.mapping.Column(client)]
 *
 *
 *  com.mysql.jdbc.exceptions.jdbc4.MySQLSyntaxErrorException:
 *  You have an error in your SQL syntax; check the manual that corresponds to your MySQL
 *  server version for the right syntax to use near 'order (id bigint not null auto_increment, date datetime, orderStatus varchar(255' at line 1

 */


@Entity
@Table(name="buy")
//@NamedQuery(name = "Order.getAll", query = "SELECT b from Order b")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @ManyToOne(cascade = CascadeType.DETACH)
    @JoinColumn(name="client_id")
    private Client client;

    @Column(name = "orderStatus")
    @Enumerated(EnumType.STRING)
    private OrderStatus orderStatus;

    @Column(name = "paymentStatus")
    @Enumerated(EnumType.STRING)
    private PaymentStatus paymentStatus;

    @Column(name = "paymentType")
    @Enumerated(EnumType.STRING)
    private PaymentType paymentType;

    @Column(name = "shippingType")
    @Enumerated(EnumType.STRING)
    private ShippingType shippingType;


    @Column(name = "date")
    @Temporal(TemporalType.DATE)
    private Date date;

//    @ElementCollection
//    @CollectionTable(
//            name="order_line",
//            joinColumns=@JoinColumn(name="order_id")
//    )


    //@OneToMany(orphanRemoval=true, cascade=CascadeType.ALL)

//    @ElementCollection
//    @CollectionTable(name = "order_line", joinColumns = @JoinColumn(name = "order_id"))
//    private List<OrderLine> orderLines;
    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL) // добавил
    private List<OrderLine> orderLines;

    public Order() {
    }


    public Order(Client client, OrderStatus orderStatus, PaymentStatus paymentStatus, PaymentType paymentType, ShippingType shippingType, List<OrderLine> orderLines) {
        this.client = client;
        this.orderStatus = orderStatus;//
        this.paymentStatus = paymentStatus;
        this.paymentType = paymentType;
        this.shippingType = shippingType;
        this.orderLines = orderLines;
    }

    //@Id @Column(name = "id")
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    //    @ManyToOne
//    @JoinColumn(name = "client_id")
    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public OrderStatus getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(OrderStatus orderStatus) {
        this.orderStatus = orderStatus;
    }

    public PaymentStatus getPaymentStatus() {
        return paymentStatus;
    }

    public void setPaymentStatus(PaymentStatus paymentStatus) {
        this.paymentStatus = paymentStatus;
    }

    public PaymentType getPaymentType() {
        return paymentType;
    }

    public void setPaymentType(PaymentType paymentType) {
        this.paymentType = paymentType;
    }

    public ShippingType getShippingType() {
        return shippingType;
    }

    public void setShippingType(ShippingType shippingType) {
        this.shippingType = shippingType;
    }

    //@OneToMany(mappedBy = "order", cascade=CascadeType.ALL, orphanRemoval=true)
    public List<OrderLine> getOrderLines() {
        return orderLines;
    }

    public void setOrderLines(List<OrderLine> orderLines) {
        this.orderLines = orderLines;
    }
}
