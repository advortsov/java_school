<%@ page import="com.tsystems.javaschool.dao.entity.Client" %>
<%@ page import="com.tsystems.javaschool.dao.entity.Order" %>
<%@ page import="com.tsystems.javaschool.services.util.Managers" %>
<%@ page import="com.tsystems.javaschool.view.controllers.ClientController" %>
<%@ page import="java.util.List" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<!DOCTYPE html>
<%@include file="../WEB-INF/jspf/left_menu.jspf" %>

<jsp:useBean id="clientManager" class="com.tsystems.javaschool.services.impl.ClientManagerImpl" scope="page"/>
<jsp:useBean id="publisherManager" class="com.tsystems.javaschool.services.impl.PublisherManagerImpl" scope="page"/>
<jsp:useBean id="authorManager" class="com.tsystems.javaschool.services.impl.AuthorManagerImpl" scope="page"/>

<div class="edit_penal">

    <br><strong>Личные данные</strong>

    <%
        Client currClient = (Client) session.getAttribute("currentClient");
        if (currClient == null) {
            currClient = ClientController.actualizeClient(request, userName);
            System.out.println(session.getAttribute("currentClient"));

        }
    %>
    <form name="client_edit_form" action="/editProfile" method="post">
        <div class="book_info">
            <div class="book_details">
                <br><strong>Имя:</strong><input name="client_name" type="text" value="<%=currClient.getName()%>">
                <br><strong>Фамилия:</strong><input name="client_surname" type="text"
                                                    value="<%=currClient.getSurname()%>">
                <br><strong>Адрес:</strong><input name="client_address" type="text"
                                                  value="<%=currClient.getAddress()%>">
                <br><strong>Дата рождения:</strong>
                <input type="date" name="client_bday" value="<%=currClient.getBirthday()%>">
                <br><strong>Почта:</strong><input type="email" name="client_email"
                                                  value="<%=currClient.getEmail()%>">

                <p><input type="submit" value="Сохранить"></p>
            </div>
        </div>
    </form>

    <%--copypast--%>


    <div class="client_orders_penal">
        <%
            List<Order> clientOrders = Managers.getClientManager().getClientOrders(currClient);
            if (!clientOrders.isEmpty()) {
        %>

        <br><strong>Ваши заказы</strong>

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
                border-style: dotted;
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

        <table>
            <%--<table border="1">--%>
            <tr>
                <td>id</td>
                <td>Дата</td>
                <td>Статус заказа</td>
                <td>Способ доставки</td>
                <td>Способ оплаты</td>
                <td>Статус оплаты</td>
                <td>Сумма заказа</td>
            </tr>
            <%
                for (Order order : clientOrders) {
            %>
            <tr>
                <td><%=order.getId()%>
                </td>
                <td><%=order.getDate()%>
                </td>
                <td><%=order.getOrderStatus()%>
                </td>
                <td><%=order.getShippingType()%>
                </td>
                <td><%=order.getPaymentType()%>
                </td>
                <td><%=order.getPaymentStatus()%>
                </td>
                <td><%=Managers.getOrderManager().orderTotalSumm(order)%> руб.</td>

            </tr>
            <%
                }
            %>
        </table>

        <%
        } else {
        %>
        <br><strong>У Вас пока нет заказов</strong>
        <%
            }
        %>


    </div>

</div>