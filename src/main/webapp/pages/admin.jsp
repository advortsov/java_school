<%@ page import="com.tsystems.javaschool.dao.entity.Client" %>
<%@ page import="com.tsystems.javaschool.dao.entity.Order" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.ArrayList" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<!DOCTYPE html>
<%@include file="../WEB-INF/jspf/left_menu.jspf" %>

<jsp:useBean id="bookManager" class="com.tsystems.javaschool.services.impl.BookManagerImpl" scope="page"/>
<jsp:useBean id="currentClient" class="com.tsystems.javaschool.dao.entity.Client" scope="page"/>
<jsp:useBean id="orderManager" class="com.tsystems.javaschool.services.impl.OrderManagerImpl" scope="page"/>

<%
  currentClient = (Client) session.getAttribute("currentClient");
  if (currentClient == null){
    session.invalidate();
  }

  List<Order> orders = new ArrayList<>();

  if (request.getSession(false) != null){
    orders = orderManager.loadAllOrders();
  } else {
    response.sendRedirect("index.jsp");
  }

%>

<div class="admin_penal">
  <%
    if (!orders.isEmpty()) {
  %>

  <br><strong>Заказы</strong>

  <style>
    table {
      /*font-family: "Lucida Sans Unicode", "Lucida Grande", Sans-Serif;*/
      font-size: 13px;
      border-collapse: collapse;
      text-align: center;
    }

    th, td:first-child {
      /*background: white;*/
      color: black;
      padding: 10px 20px;
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
      <td>Статус</td>
      <td>Способ доставки</td>
      <td>Способ оплаты</td>
      <td>Статус оплаты</td>
      <td>Сумма заказа</td>
      <td></td>
      <td></td>
    </tr>
    <%
      for (Order order : orders){
    %>
    <tr>
      <td><%=order.getId()%></td>
      <td><%=order.getDate()%></td>
      <td><%=order.getClient().getName()%></td>
      <td><%=order.getOrderStatus()%></td>
      <td><%=order.getShippingType()%></td>
      <td><%=order.getPaymentType()%></td>
      <td><%=order.getPaymentStatus()%></td>
      <td><%=orderManager.orderTotalSumm(order)%> руб.</td>
      <td><a href=""><img src="../images/delete.png" alt="Удалить" name="delete"/></a></td>
      <td><a href=""><img src="../images/edit.png" alt="Редактировать" name="edit"/></a></td>
      <td><a href=""><img src="../images/save.png" alt="Сохранить" name="save"/></a></td>
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