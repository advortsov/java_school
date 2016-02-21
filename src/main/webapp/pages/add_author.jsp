<%@ page import="com.tsystems.javaschool.dao.entity.Author" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<!DOCTYPE html>
<%@include file="../WEB-INF/jspf/left_menu.jspf" %>

<jsp:useBean id="authorManager" class="com.tsystems.javaschool.services.impl.AuthorManagerImpl" scope="page"/>


<div class="edit_penal">
  <br><strong>Добавление автора</strong>
  <form name="book_add_form" action ="/addAuthor" method="post">
    <div class="book_info">
      <div class="book_details">
        <br><strong>Автор:</strong><input name="author_name" type="text">
        <p><input type="submit" value="Сохранить"></p>
      </div>
    </div>
  </form>
</div>


<div class="edit_penal">
  <br><strong>Удаление автора</strong>
  <form name="genre_del_form" action ="/delAuthor" method="post">
    <div class="book_info">
      <div class="book_details">
        <br><strong>Автор:</strong><select name="author_name">
        <%
          for (Author author : authorManager.loadAllAuthors() ){
        %>
        <option><%=author.getName()%></option>
        <% } %>
      </select>
        <p><input type="submit" value="Удалить"></p>
      </div>
    </div>
  </form>
</div>


<div class="edit_penal">
  <br><strong>Редактирование автора</strong>
  <form name="genre_edit_form" action ="/editAuthor" method="post">
    <div class="book_info">
      <div class="book_details">
        <br><strong>Выберите жанр для редактирования:</strong><select name="author_for_edit">
        <%
          for (Author author : authorManager.loadAllAuthors() ){
        %>
        <option><%=author.getName()%></option>
        <% } %>
      </select>
        <br><strong>Введите его новое имя:</strong><input name="author_name" type="text">

        <p><input type="submit" value="Редактировать"></p>
      </div>
    </div>
  </form>
</div>