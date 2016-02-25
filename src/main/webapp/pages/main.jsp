<%@ page import="com.tsystems.javaschool.dao.entity.Client" %>
<%@ page import="com.tsystems.javaschool.view.controllers.ClientController" %>
<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%--<jsp:directive.page errorPage="/error/not_registered_user.html" />--%>
<!DOCTYPE html>


<%@include file="../WEB-INF/jspf/left_menu.jspf" %>

<jsp:useBean id="shoppingCartManager"
             class="com.tsystems.javaschool.services.impl.ShoppingCartManagerImpl" scope="application"/>

<div style="float:left; margin-top: 20px;">

    <%
        Client client = ClientController.actualizeClient(request, userName);
        ClientController.actualizeCart(request, client, shoppingCartManager);
    %>


    <h3>Выберите раздел в каталоге или используйте поиск книги</h3>


</div>