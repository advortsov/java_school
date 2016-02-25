<%@ page import="com.tsystems.javaschool.dao.entity.Publisher" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<!DOCTYPE html>
<%@include file="../WEB-INF/jspf/left_menu.jspf" %>
<jsp:useBean id="publisherManager" class="com.tsystems.javaschool.services.impl.PublisherManagerImpl" scope="page"/>


<div class="edit_penal">
  <br><strong>Добавление издателя</strong>
  <form name="publisher_add_form" action ="/addPublisher" method="post">
    <div class="book_info">
      <div class="book_details">
        <br><strong>Издатель:</strong><input name="publisher_name" type="text">
        <p><input type="submit" value="Сохранить"></p>
      </div>
    </div>
  </form>
</div>


<div class="edit_penal">
  <br><strong>Удаление издателя</strong>
  <form name="publisher_del_form" action ="/delPublisher" method="post">
    <div class="book_info">
      <div class="book_details">
        <br><strong>Издатель:</strong><select name="publisher_name">
        <%
          for (Publisher publisher : publisherManager.loadAllPublishers() ){
        %>
        <option><%=publisher.getName()%></option>
        <% } %>
      </select>
        <p><input type="submit" value="Удалить"></p>
      </div>
    </div>
  </form>
</div>


<div class="edit_penal">
  <br><strong>Редактирование издателя</strong>
  <form name="publisher_edit_form" action ="/editPublisher" method="post">
    <div class="book_info">
      <div class="book_details">
        <br><strong>Выберите издателя для редактирования:</strong><select name="publisher_for_edit">
        <%
          for (Publisher publisher : publisherManager.loadAllPublishers() ){
        %>
        <option><%=publisher.getName()%></option>
        <% } %>
      </select>
        <br><strong>Введите его новое имя:</strong><input name="publisher_name" type="text">

        <p><input type="submit" value="Редактировать"></p>
      </div>
    </div>
  </form>
</div>