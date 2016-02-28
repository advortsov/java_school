package com.tsystems.javaschool.services.impl;

import com.tsystems.javaschool.dao.entity.Book;
import com.tsystems.javaschool.dao.entity.Client;
import com.tsystems.javaschool.dao.entity.Order;
import com.tsystems.javaschool.dao.exeption.NotRegisteredUserException;
import com.tsystems.javaschool.dao.impl.OrderDAOImpl;
import com.tsystems.javaschool.dao.interfaces.OrderDAO;
import com.tsystems.javaschool.dao.util.JpaUtil;
import com.tsystems.javaschool.services.interfaces.AdminManager;
import com.tsystems.javaschool.services.interfaces.BookManager;
import com.tsystems.javaschool.services.interfaces.ClientManager;
import com.tsystems.javaschool.services.util.Managers;

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

//
//        System.out.println("---------------------------------------------");
//        System.out.println("---------------------------------------------");
//        Iterator it = topBooks.entrySet().iterator();
//        while (it.hasNext()) {
//            Map.Entry pair = (Map.Entry) it.next();
//            System.out.println(pair.getKey() + " " + pair.getValue());
//        }
//        System.out.println("---------------------------------------------");
//        System.out.println("---------------------------------------------");
//        System.out.println("---------------------------------------------");
        return sortByValue(topBooks);
    }

    @Override
    public Map<Client, Integer> getTopTenClients() { //Map<Client, Integer>
//        ClientManager clientManager = Managers.getClientManager();
//        OrderManager orderManager = Managers.getOrderManager();
//
//        List<Order> allOrders = orderManager.loadAllOrders();
//
//        Map<Client, Integer> clientTotalSummMap = new HashMap<>();
//
//        for (Order order : allOrders) {
//            int orderTotalSumm = orderManager.orderTotalSumm(order);
//            Client currClient = order.getClient();
//            if (clientTotalSummMap.get(currClient) == null) clientTotalSummMap.put(currClient, 0);
//            int newTotalSumm = clientTotalSummMap.get(currClient) + orderTotalSumm;
//            clientTotalSummMap.put(currClient, newTotalSumm);
//        }
//
//        return sortByValue(clientTotalSummMap);

        Map<Client, Integer> topClients = new HashMap<>();
        //String sql = "SELECT o FROM Order o WHERE o.date >= :periodStart AND o.date <= :periodEnd";
//
//        SELECT buy.client_id as clientId, SUM(book.price*order_line.quantity) as total
//        FROM buy JOIN order_line ON buy.id = order_line.order_id
//        JOIN book ON order_line.book_id = book.id
//
//        GROUP BY clientId, buy.id
//        ORDER BY total DESC
//        #LIMIT 1
        // String sup_name ="Tortuga Trading";
//        BigDecimal sum = (List)em.createNativeQuery("SELECT SUM(p.price*l.quantity)
//                FROM orders o JOIN orderlineitems l ON o.pono=l.pono
//                JOIN products p ON l.prod_id=p.prod_id
//                JOIN suppliers s ON p.sup_id=s.sup_id WHERE sup_name =?1")
//                .setParameter(1, sup_name)
//                .getSingleResult();

        String sql = "SELECT buy.client_id as clientId, SUM(book.price*order_line.quantity) as total " +
                "FROM buy JOIN order_line ON buy.id = order_line.order_id " +
                "JOIN book ON order_line.book_id = book.id " +
                "GROUP BY clientId ORDER BY total DESC LIMIT 10";

        List<Object[]> resultList = JpaUtil.getEntityManager().createNativeQuery(sql).getResultList();
//
//        TypedQuery<Object[]> q = getEntityManager().createQuery(
//                "SELECT c.id, count(p.id) " +
//                        "FROM Product p LEFT JOIN p.category c " +
//                        "WHERE p.seller.id = :id " +
//                        "GROUP BY c.id", Object[].class).setParameter("id", id);
//
//        List<Object[]> resultList = q.getResultList();
//        Map<String, Long> resultMap = new HashMap<String, Long>(resultList.size());
//        for (Object[] result : resultList)
//            resultMap.put((String)result[0], (Long)result[1]);

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
        Query query = JpaUtil.getEntityManager().createQuery(sql).
                setParameter("periodStart", periodStart).setParameter("periodEnd", periodEnd);
        orders = orderDAO.findMany(query);
        return orders;
    }
}
