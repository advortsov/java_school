package com.tsystems.javaschool.services.impl;

import com.tsystems.javaschool.dao.entity.Book;
import com.tsystems.javaschool.dao.entity.Client;
import com.tsystems.javaschool.dao.entity.Order;
import com.tsystems.javaschool.dao.exeption.NotRegisteredUserException;
import com.tsystems.javaschool.dao.interfaces.OrderDAO;
import com.tsystems.javaschool.dao.util.Daos;
import com.tsystems.javaschool.dao.util.JpaUtil;
import com.tsystems.javaschool.services.interfaces.AdminManager;
import com.tsystems.javaschool.services.interfaces.BookManager;
import com.tsystems.javaschool.services.interfaces.ClientManager;
import com.tsystems.javaschool.services.util.Managers;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.*;

/**
 * @author Alexander Dvortsov
 * @version 1.0
 * @since 11.02.2016
 */
public class AdminManagerImpl implements AdminManager {

    private OrderDAO orderDAO = Daos.getOrderDAO();

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
    public Map<Book, Integer> getTopTenBooks() {

        Map<Book, Integer> topBooks = new HashMap<>();

        String sql = "select order_line.book_id, sum(order_line.quantity) " +
                "as total from order_line GROUP BY book_id ORDER BY total DESC LIMIT 10";

        List<Object[]> resultList = JpaUtil.getEntityManager().createNativeQuery(sql).getResultList();

        BookManager bookManager = Managers.getBookManager();
        for (Object[] result : resultList) {
            BigInteger bookId = (BigInteger) result[0];
            long id = bookId.longValue();

            BigDecimal totalSold = (BigDecimal) result[1];
            int summ = totalSold.intValue();

            topBooks.put(bookManager.findBookById(id), summ);
        }

        return sortByValue(topBooks);
    }

    @Override
    public Map<Client, Integer> getTopTenClients() {

        Map<Client, Integer> topClients = new HashMap<>();

        String sql = "SELECT buy.client_id as clientId, SUM(book.price*order_line.quantity) as total " +
                "FROM buy JOIN order_line ON buy.id = order_line.order_id " +
                "JOIN book ON order_line.book_id = book.id " +
                "GROUP BY clientId ORDER BY total DESC LIMIT 10";

        List<Object[]> resultList = JpaUtil.getEntityManager().createNativeQuery(sql).getResultList();

        ClientManager clientManager = Managers.getClientManager();
        for (Object[] result : resultList) {
            try {
                BigInteger clientId = (BigInteger) result[0];
                long id = clientId.longValue();

                BigDecimal clientSumm = (BigDecimal) result[1];
                int summ = clientSumm.intValue();

                topClients.put(clientManager.findById(id), summ);
            } catch (NotRegisteredUserException e) {
                e.printStackTrace();
            }
        }

        return sortByValue(topClients);
    }

    @Override
    public List<Order> getOrdersPerPeriod(Date periodStart, Date periodEnd) {
        List<Order> orders = null;
        String sql = "SELECT o FROM Order o WHERE o.date >= :periodStart AND o.date <= :periodEnd";
        EntityManager em = JpaUtil.getEntityManager();
        Query query = em.createQuery(sql).
                setParameter("periodStart", periodStart).setParameter("periodEnd", periodEnd);
        orders = orderDAO.findMany(query);
        em.close();
        return orders;
    }
}
