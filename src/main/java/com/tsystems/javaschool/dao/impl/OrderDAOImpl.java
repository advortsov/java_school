package com.tsystems.javaschool.dao.impl;

import com.tsystems.javaschool.dao.entity.Genre;
import com.tsystems.javaschool.dao.entity.Order;
import com.tsystems.javaschool.dao.interfaces.GenreDAO;
import com.tsystems.javaschool.dao.interfaces.OrderDAO;
import org.hibernate.Query;

import java.math.BigDecimal;
import java.util.List;

/**
 * @author Alexander Dvortsov
 * @version 1.0
 * @since 11.02.2016
 */
public class OrderDAOImpl extends GenericDAOImpl<Order, Long> implements OrderDAO {

}
