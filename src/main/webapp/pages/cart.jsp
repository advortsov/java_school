<%@ page import="java.util.List" %>
<%@ page import="com.tsystems.javaschool.dao.entity.*" %>
<%@ page import="com.tsystems.javaschool.services.ShoppingCart" %>
<%@ page import="com.tsystems.javaschool.services.enums.ShippingType" %>
<%@ page import="com.tsystems.javaschool.services.enums.PaymentType" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<!DOCTYPE html>
<%@include file="../WEB-INF/jspf/left_menu.jspf" %>

<jsp:useBean id="bookManager" class="com.tsystems.javaschool.services.impl.BookManagerImpl" scope="page"/>
<jsp:useBean id="cartManager" class="com.tsystems.javaschool.services.impl.ShoppingCartManagerImpl" scope="page"/>
<jsp:useBean id="currentClient" class="com.tsystems.javaschool.dao.entity.Client" scope="page"/>

<%
  currentClient = (Client) session.getAttribute("currentClient");
    if (currentClient == null){
      session.invalidate();
      //response.sendRedirect("index.jsp");
    }

  List<OrderLine> orderLines = null;

  if (request.getSession(false) != null) {
      cartManager.setShoppingCart((ShoppingCart) session.getAttribute("cart"));
      cartManager.fillUpFromCookies(request); // заполняем ее из кукисов
      orderLines = cartManager.getShoppingCart().getItems();
  } else {
    response.sendRedirect("index.jsp");
  }

%>

<div class="cart_penal">
  <%
    if (!orderLines.isEmpty()) {
  %>

  <br><strong>Корзина</strong>

  <form name="order_form" action="/CreateOrder" method="post">

  <style>
    table {
      font-size: 13px;
      border-collapse: collapse;
      width: 100%;
    }

    th, td {
      text-align: left;
      padding: 8px;
    }

    tr:nth-child(even){background-color: #f2f2f2}

    th {
      background-color: #4CAF50;
      color: white;
    }
  </style>


  <table border="1">
    <tr>
      <td>id</td>
      <td>Название</td>
      <td>Количество</td>
      <td></td>
    </tr>
    <%
      for (OrderLine line : orderLines){
    %>
    <tr>
      <td><%=line.getBook().getId()%></td>
      <td><%=line.getBook().getName()%></td>
      <td><input type="number" name="q-<%=line.getBook().getId()%>" min="1" value="<%=line.getQuantity()%>" style="width:45px"> </td>
      <td><a href=""><img src="../images/delete.png" alt="Удалить" name="delete"/></a></td>
    </tr>
    <%
      }
      session.setAttribute("orderLines", orderLines);
    %>
  </table>

    <br>Способ доставки:<select name="shipping_type">
    <%
      for (ShippingType value : ShippingType.values()){
    %>
    <option><%=value.toString()%></option>
    <% } %>
  </select>


    <br>Способ оплаты:<select name="payment_type">
    <%
      for (PaymentType value : PaymentType.values()){
    %>
    <option><%=value.toString()%></option>
    <% } %>
  </select>

    <p><input type="submit"  value="Оформить заказ"></p>

  </form>
  <%
    } else {
  %>
  <br><strong>Ваша корзина пока пуста</strong>
<%
  }
%>

</div>

</body>
</html>
