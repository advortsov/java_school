<%@ page import="com.tsystems.javaschool.dao.entity.Client" %>
<%@ page import="com.tsystems.javaschool.dao.entity.Order" %>
<%@ page import="java.util.List" %>
<%@ page import="com.tsystems.javaschool.services.interfaces.OrderManager" %>
<%@ page import="com.tsystems.javaschool.services.impl.OrderManagerImpl" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<!DOCTYPE html>
<%@include file="../WEB-INF/jspf/left_menu.jspf" %>

<jsp:useBean id="bookManager" class="com.tsystems.javaschool.services.impl.BookManagerImpl" scope="page"/>
<jsp:useBean id="currentClient" class="com.tsystems.javaschool.dao.entity.Client" scope="page"/>
<jsp:useBean id="orderManager" class="com.tsystems.javaschool.services.impl.OrderManagerImpl" scope="page"/>
<jsp:useBean id="publisherManager" class="com.tsystems.javaschool.services.impl.PublisherManagerImpl" scope="page"/>
<jsp:useBean id="authorManager" class="com.tsystems.javaschool.services.impl.AuthorManagerImpl" scope="page"/>


<%
    currentClient = (Client) session.getAttribute("currentClient");
    if (currentClient == null) {
        session.invalidate();
    }

    OrderManager orderManager1 = new OrderManagerImpl();

    List<Order> orders = orderManager1.loadAllOrders();
    //System.out.println(orders.get(orders.size()-1));

%>


<style>
    #tab2, #tab3, #tab4, #tab5, #tab6 {
        position: fixed;
    }

    .menu1 > a,
    .menu1 #tab2:target ~ a:nth-of-type(1),
    .menu1 #tab3:target ~ a:nth-of-type(1),
    .menu1 #tab4:target ~ a:nth-of-type(1),
    .menu1 #tab5:target ~ a:nth-of-type(1),
    .menu1 #tab6:target ~ a:nth-of-type(1),
    .menu1 > div {
        padding: 5px;
        /*border: 1px solid #aaa;*/
        width: 700px;
        /*float: left;*/
    }

    .menu1 > a {
        line-height: 28px;
        background: #fff;
        text-decoration: none;
    }

    #tab2,
    #tab3,
    #tab4,
    #tab5,
    #tab6,
    .menu1 > div,
    .menu1 #tab2:target ~ div:nth-of-type(1),
    .menu1 #tab3:target ~ div:nth-of-type(1),
    .menu1 #tab4:target ~ div:nth-of-type(1),
    .menu1 #tab5:target ~ div:nth-of-type(1),
    .menu1 #tab6:target ~ div:nth-of-type(1) {
        display: none;
    }

    .menu1 > div:nth-of-type(1),
    .menu1 #tab2:target ~ div:nth-of-type(2),
    .menu1 #tab3:target ~ div:nth-of-type(3),
    .menu1 #tab4:target ~ div:nth-of-type(4),
    .menu1 #tab5:target ~ div:nth-of-type(5),
    .menu1 #tab6:target ~ div:nth-of-type(6) {
        display: block;
    }

    .menu1 > a:nth-of-type(1),
    .menu1 #tab2:target ~ a:nth-of-type(2),
    .menu1 #tab3:target ~ a:nth-of-type(3),
    .menu1 #tab4:target ~ a:nth-of-type(4),
    .menu1 #tab5:target ~ a:nth-of-type(5),
    .menu1 #tab6:target ~ a:nth-of-type(6) {
        border-bottom: 1px solid #fff;
    }
</style>
<div class="menu1">
    <br id="tab2"/>
    <br id="tab3"/>
    <br id="tab4"/>
    <br id="tab5"/>
    <br id="tab6"/>
    <a href="#tab1">Книги</a>
    <a href="#tab2">Жанры</a>
    <a href="#tab3">Издатели</a>
    <a href="#tab4">Авторы</a>
    <a href="#tab5">Заказы</a>
    <a href="#tab6">Клиенты</a>

    <%--tab1 - Книги--%>
    <%@include file="../WEB-INF/jspf/adminf/add_book.jspf" %>

    <%--tab1 - Жанры--%>
    <%@include file="../WEB-INF/jspf/adminf/add_genre.jspf" %>

    <%--tab1 - Издатели--%>
    <%@include file="../WEB-INF/jspf/adminf/add_publisher.jspf" %>

    <%--tab1 - Авторы--%>
    <%@include file="../WEB-INF/jspf/adminf/add_author.jspf" %>


    <div class="admin_penal">
        <%
            if (!orders.isEmpty()) {
        %>

        <br><strong>Заказы</strong>

        <style>
            table {
                font-size: 10px;
                border-collapse: collapse;
                text-align: center;
            }

            th, td:first-child {
                /*background: white;*/
                color: black;
                padding: 5px 10px;
            }

            th, td {
                border-style: solid;
                border-width: 0 1px 1px 0;
                border-color: black;
            }

            td {
                background: #D8E6F3;
            }

            th:first-child, td:first-child {
                text-align: left;
            }
        </style>

        <table border="1">
            <tr>
                <td>id</td>
                <td>Дата</td>
                <td>Имя клиента</td>
                <td>Статус заказа</td>
                <td>Способ доставки</td>
                <td>Способ оплаты</td>
                <td>Статус оплаты</td>
                <td>Сумма заказа</td>
            </tr>
            <%
                for (Order order : orders) {
            %>
            <tr>
                <td><%=order.getId()%>
                </td>
                <td><%=order.getDate()%>
                </td>
                <td><%=order.getClient().getName()%>
                </td>
                <td><%=order.getOrderStatus()%>
                </td>
                <td><%=order.getShippingType()%>
                </td>
                <td><%=order.getPaymentType()%>
                </td>
                <td><%=order.getPaymentStatus()%>
                </td>
                <td><%=orderManager1.orderTotalSumm(order)%> руб.</td>
                <td>
                    <a href="edit_order.jsp?order_id=<%=order.getId()%>">
                        <img src="../images/edit.png" alt="Редактировать" name="edit"/></a>
                </td>

            </tr>
            <%
                }
            %>
        </table>

        <%
        } else {
        %>
        <br><strong>В базе нет заказов</strong>
        <%
            }
        %>


    </div>


    <%--tab1 - Клиенты--%>
    <%@include file="../WEB-INF/jspf/adminf/top10.jspf" %>

</div>
