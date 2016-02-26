<%@ page import="com.tsystems.javaschool.services.enums.OrderStatus" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<!DOCTYPE html>
<%@include file="../WEB-INF/jspf/left_menu.jspf" %>

<div class="edit_penal">
    <br><strong>Изменение статуса заказа</strong>

    <form name="order_set_status_form" action="/setOrderStatus" method="get">
        <div class="book_info">
            <div class="book_details">
                <br><strong>ID заказа:</strong>
                <input type="text" value="<%=request.getParameter("order_id")%>"
                       name="order_id" readonly>
                <br><strong>Статус выполнения заказа:</strong>
                <select name="order_status">
                    <%
                        for (OrderStatus status : OrderStatus.values()) {
                    %>
                    <option><%=status.toString()%>
                    </option>
                    <% } %>
                </select>

                <p><input type="submit" value="Задать"></p>
            </div>
        </div>
    </form>
</div>