<%@ page import="com.tsystems.javaschool.dao.entity.Client" %>
<%@ page import="com.tsystems.javaschool.services.impl.OrderManagerImpl" %>
<%@ page import="com.tsystems.javaschool.services.interfaces.OrderManager" %>
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

    OrderManager orderManager1 = Managers.getOrderManager();

    List<Order> orders = orderManager1.loadAllOrders();
%>


<link rel="stylesheet" href="${pageContext.request.contextPath}/css/tabs.css" type="text/css"/>

<div class="menu1">
    <br id="tab2"/>
    <br id="tab3"/>
    <br id="tab4"/>
    <br id="tab5"/>
    <br id="tab6"/>
    <br id="tab7"/>
    <a href="#tab1">Книги</a>
    <a href="#tab2">Жанры</a>
    <a href="#tab3">Издатели</a>
    <a href="#tab4">Авторы</a>
    <a href="#tab5">Заказы</a>
    <a href="#tab6">Топ-10</a>
    <a href="#tab7">Выручка</a>

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

        <link rel="stylesheet" href="${pageContext.request.contextPath}/css/order_table.css" type="text/css"/>

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

    <%--tab1 - top10--%>
    <%@include file="../WEB-INF/jspf/adminf/top10.jspf" %>

    <%--tab1 - Proceeds--%>
    <%@include file="../WEB-INF/jspf/adminf/proceeds_per_period.jspf" %>

</div>
