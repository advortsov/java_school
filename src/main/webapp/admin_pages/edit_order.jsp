<%@ page import="com.tsystems.javaschool.services.enums.OrderStatus" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<!DOCTYPE html>
<%@include file="../WEB-INF/jspf/left_menu.jspf" %>

<div class="edit_penal">
    <br><strong>Изменение статуса заказа</strong>

    <form name="order_set_status_form" action="/setOrderStatus" method="get">
        <div class="edit_book_info">
            <div>
                <link rel="stylesheet" href="${pageContext.request.contextPath}/css/form.css" type="text/css"/>

                <div class="field">
                    <label for="order_id">ID заказа:</label> <%=request.getParameter("order_id")%>
                    <input type="text" value="<%=request.getParameter("order_id")%>"
                           name="order_id" id="order_id" hidden>
                </div>

                <div class="field">
                    <label for="order_status">Статус выполнения заказа:</label>
                    <select name="order_status" id="order_status">
                        <%
                            for (OrderStatus status : OrderStatus.values()) {
                        %>
                        <option><%=status.toString()%>
                        </option>
                        <% } %>
                    </select>
                </div>
                <input type="hidden" name="action" value="set"></p>
                <p><input type="submit" value="Задать"></p>
            </div>
        </div>
    </form>
</div>