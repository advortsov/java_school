package com.tsystems.javaschool.services;


import com.tsystems.javaschool.dao.entity.Client;
import com.tsystems.javaschool.dao.entity.OrderLine;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Alexander Dvortsov
 * @version 1.0
 * @since 09.02.2016
 */
public class ShoppingCart {

    private Client client;
    private List<OrderLine> items;

    public ShoppingCart() {
        items = new ArrayList<>(); // чтоб нуллпоинтера не было
    }

    public ShoppingCart(Client client, List<OrderLine> items) {
        this.client = client;
        this.items = items;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public List<OrderLine> getItems() {
        return items;
    }

    public void setItems(List<OrderLine> items) {
        this.items = items;
    }

    @Override
    public String toString() {
        return "ShoppingCart{" +
                "client=" + client +
                ", items=" + items +
                '}';
    }
}
