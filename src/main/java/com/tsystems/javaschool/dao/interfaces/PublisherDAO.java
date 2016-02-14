package com.tsystems.javaschool.dao.interfaces;

import com.tsystems.javaschool.dao.entity.Order;
import com.tsystems.javaschool.dao.entity.Publisher;

import java.math.BigDecimal;

/**
 * @author Alexander Dvortsov
 * @version 1.0
 * @since 11.02.2016
 */
public interface PublisherDAO extends GenericDAO<Publisher, Long> {
    public Publisher findByName(String name);

}
