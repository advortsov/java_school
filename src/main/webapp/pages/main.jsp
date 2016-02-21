<%@ page import="com.tsystems.javaschool.services.interfaces.ClientManager" %>
<%@ page import="com.tsystems.javaschool.services.impl.ClientManagerImpl" %>
<%@ page import="com.tsystems.javaschool.services.ShoppingCart" %>
<%@ page import="javax.persistence.NoResultException" %>
<%@ page import="com.tsystems.javaschool.dao.entity.Client" %>
<%@ page import="com.tsystems.javaschool.view.controllers.ClientController" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>

           
<%@include file="../WEB-INF/jspf/left_menu.jspf" %>

<jsp:useBean id="shoppingCartManager"
             class="com.tsystems.javaschool.services.impl.ShoppingCartManagerImpl" scope="application"/>

<div style="float:left; margin-top: 20px;">

      <%
            Client client = ClientController.actualizeClient(request, userName);



            // инициализация корзины
            ShoppingCart shoppingCart = new ShoppingCart();
            shoppingCart.setClient(client);
            shoppingCartManager.setShoppingCart(shoppingCart);
            shoppingCartManager.fillUpFromCookies(request); // заполняем ее из кукисов

            session.setAttribute("cart", shoppingCart);

      %>


<h3>Выберите раздел в каталоге или используйте поиск книги</h3>


</div>