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
 * POJO
 */
public class Order {
    private long id;
    private Client client;
    private OrderStatus orderStatus;
    private PaymentStatus paymentStatus;
    private PaymentType paymentType;
    private ShippingType shippingType;
//    @Column(name = "date")
//    @Temporal(TemporalType.TIMESTAMP)
    private Date date;
    private List<OrderLine> orderLines;

    public Order() {
    }

    public Order(Client client, OrderStatus orderStatus, PaymentStatus paymentStatus, PaymentType paymentType, ShippingType shippingType, List<OrderLine> orderLines) {
        this.client = client;
        this.orderStatus = orderStatus;
        this.paymentStatus = paymentStatus;
        this.paymentType = paymentType;
        this.shippingType = shippingType;
        this.orderLines = orderLines;
    }

    public long getId() {
        return id;
    }

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

    public List<OrderLine> getOrderLines() {
        return orderLines;
    }

    public void setOrderLines(List<OrderLine> orderLines) {
        this.orderLines = orderLines;
    }
}
