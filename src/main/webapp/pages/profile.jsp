<%@ page import="com.tsystems.javaschool.dao.entity.*" %>
<%@ page import="com.tsystems.javaschool.view.controllers.ClientController" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<!DOCTYPE html>
<%@include file="../WEB-INF/jspf/left_menu.jspf" %>

<jsp:useBean id="clientManager" class="com.tsystems.javaschool.services.impl.ClientManagerImpl" scope="page"/>
<jsp:useBean id="publisherManager" class="com.tsystems.javaschool.services.impl.PublisherManagerImpl" scope="page"/>
<jsp:useBean id="authorManager" class="com.tsystems.javaschool.services.impl.AuthorManagerImpl" scope="page"/>

<div class="edit_penal">

  <br><strong>Личные данные</strong>

  <style>
    table {
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

  <%
    Client currClient = (Client) session.getAttribute("currentClient");
    if (currClient == null) {
      System.out.println("!!!");
      currClient = ClientController.actualizeClient(request, userName);
      System.out.println(session.getAttribute("currentClient"));

    }
  %>
  <form name="client_edit_form" action ="/editProfile" method="post">
    <div class="book_info">
      <div class="book_details">
        <br><strong>Имя:</strong><input name="client_name" type="text" value="<%=currClient.getName()%>">
        <br><strong>Фамилия:</strong><input name="client_surname" type="text" value="<%=currClient.getSurname()%>">
        <br><strong>Адрес:</strong><input name="client_address" type="text" value="<%=currClient.getAddress()%>">
        <br><strong>Дата рождения:</strong>
        <input type="date" name="client_bday" value="<%=currClient.getBirthday()%>">
        <br><strong>Почта:</strong><input type="email" name="client_email"
                                          value="<%=currClient.getEmail()%>">
       <p><input type="submit" value="Сохранить"></p>
      </div>
    </div>

  </form>

</div>