<%@ page import="com.tsystems.javaschool.services.interfaces.ClientManager" %>
<%@ page import="com.tsystems.javaschool.services.impl.ClientManagerImpl" %>
<%@ page import="com.tsystems.javaschool.services.ShoppingCart" %>
<%@ page import="javax.persistence.NoResultException" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>

           
<%@include file="../WEB-INF/jspf/left_menu.jspf" %>

<jsp:useBean id="shoppingCartManager" class="com.tsystems.javaschool.services.impl.ShoppingCartManagerImpl" scope="application"/>

<div style="float:left; margin-top: 20px;">

      <%
            ClientManager clientManager = new ClientManagerImpl();
            Client client = null;
            try {
                  client = clientManager.findByUserName(userName); // вообще должен выгружаться из бд здесь, и анонимус тоже
            } catch (NoResultException ex){
                  //ignore
            }


            if (client == null || userName == "") {
                  userName = "Anonymous";
                  session.setAttribute("username", userName);
                  client = clientManager.findByUserName(userName);
            }
            session.setAttribute("currentClient", client);
            // теперь у нас в сессии есть наш клиент из базы

            // инициализация корзины
            ShoppingCart shoppingCart = new ShoppingCart();
            shoppingCart.setClient(client);
            shoppingCartManager.setShoppingCart(shoppingCart);
            shoppingCartManager.fillUpFromCookies(request); // заполняем ее из кукисов

            session.setAttribute("cart", shoppingCart);

      %>


<h3>Выберите раздел в каталоге или используйте поиск книги</h3>


</div>