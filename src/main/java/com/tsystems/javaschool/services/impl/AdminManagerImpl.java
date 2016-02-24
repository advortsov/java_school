package com.tsystems.javaschool.services.impl;

import com.tsystems.javaschool.dao.entity.Client;
import com.tsystems.javaschool.dao.entity.Order;
import com.tsystems.javaschool.dao.impl.OrderDAOImpl;
import com.tsystems.javaschool.dao.interfaces.OrderDAO;
import com.tsystems.javaschool.dao.util.JpaUtil;
import com.tsystems.javaschool.services.interfaces.AdminManager;
import com.tsystems.javaschool.services.interfaces.ClientManager;
import com.tsystems.javaschool.services.interfaces.OrderManager;
import com.tsystems.javaschool.services.util.Managers;

import javax.persistence.Query;
import java.util.*;

/**
 * @author Alexander Dvortsov
 * @version 1.0
 * @since 11.02.2016
 */
public class AdminManagerImpl implements AdminManager {

    private OrderDAO orderDAO = new OrderDAOImpl();

    private static <K, V extends Comparable<? super V>> Map<K, V> sortByValue(Map<K, V> map) {
        List<Map.Entry<K, V>> list = new LinkedList<>(map.entrySet());
        Collections.sort(list, new Comparator<Map.Entry<K, V>>() {
            @Override
            public int compare(Map.Entry<K, V> o1, Map.Entry<K, V> o2) {
                return (o2.getValue()).compareTo(o1.getValue());
            }
        });

        Map<K, V> result = new LinkedHashMap<>();
        for (Map.Entry<K, V> entry : list) {
            result.put(entry.getKey(), entry.getValue());
        }
        return result;
    }

    @Override
    public List<Order> getTopTenBooks() {
        return null;
    }

    @Override
    public Map<Client, Integer> getTopTenClients() {
        ClientManager clientManager = Managers.getClientManager();
        OrderManager orderManager = Managers.getOrderManager();

        List<Order> allOrders = orderManager.loadAllOrders();

        Map<Client, Integer> clientTotalSummMap = new HashMap<>();

        for (Order order : allOrders) {
            int orderTotalSumm = orderManager.orderTotalSumm(order);
            Client currClient = order.getClient();
            if (clientTotalSummMap.get(currClient) == null) clientTotalSummMap.put(currClient, 0);
            int newTotalSumm = clientTotalSummMap.get(currClient) + orderTotalSumm;
            clientTotalSummMap.put(currClient, newTotalSumm);
        }

        return sortByValue(clientTotalSummMap);
    }

    @Override
    public List<Order> getOrdersPerPeriod(Date periodStart, Date periodEnd) {
        List<Order> orders = null;
        String sql = "SELECT o FROM Order o WHERE o.date >= :periodStart AND o.date <= :periodEnd";
        Query query = JpaUtil.getEntityManager().createQuery(sql).
                setParameter("periodStart", periodStart).setParameter("periodEnd", periodEnd);
        orders = orderDAO.findMany(query);
        return orders;
    }
}
