<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<!DOCTYPE html>
<%@include file="../WEB-INF/jspf/left_menu.jspf" %>

<jsp:useBean id="bookManager" class="com.tsystems.javaschool.services.impl.BookManagerImpl" scope="page"/>

<div class="admin_penal">

  <br><strong>Заказы</strong>

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

  <table border="1">
    <tr>
      <td>id</td>
      <td>Имя клиента</td>
      <td>Статус</td>
      <td>Способ доставки</td>
      <td>Способ оплаты</td>
      <td>Дата</td>
      <td></td>
      <td></td>
    </tr>
    <tr>
      <td>...</td>
      <td>...</td>
      <td>...</td>
      <td>...</td>
      <td>...</td>
      <td>...</td>
      <td><a href=""><img src="../images/delete.png" alt="Удаллить" name="delete"/></a></td>
      <td><a href=""><img src="../images/edit.png" alt="Редактировать" name="edit"/></a></td>
      <td><a href=""><img src="../images/save.png" alt="Сохранить" name="save"/></a></td>
    </tr>
  </table>



</div>