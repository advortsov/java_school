<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<!DOCTYPE html>
<%@include file="../WEB-INF/jspf/left_menu.jspf" %>

<%--<jsp:useBean id="bookManager" class="com.tsystems.javaschool.services.impl.BookManagerImpl" scope="page"/>--%>
<%--<jsp:useBean id="publisherManager" class="com.tsystems.javaschool.services.impl.PublisherManagerImpl" scope="page"/>--%>
<%--<jsp:useBean id="authorManager" class="com.tsystems.javaschool.services.impl.AuthorManagerImpl" scope="page"/>--%>

<div class="edit_penal">
  <br><strong>Добавление жанра</strong>
  <form name="genre_add_form" action ="/addGenre" method="post">
    <div class="book_info">
      <div class="book_details">
        <br><strong>Название:</strong><input name="genre_name" type="text">
        <p><input type="submit" value="Сохранить"></p>
      </div>
    </div>
  </form>
</div>

<div class="edit_penal">
  <br><strong>Удаление жанра</strong>
  <form name="genre_del_form" action ="/delGenre" method="post">
    <div class="book_info">
      <div class="book_details">
        <br><strong>Жанр:</strong><select name="genre_name">
        <%
          for (Genre genre : genreManager.loadAllGenres() ){
        %>
        <option><%=genre.getName()%></option>
        <% } %>
      </select>
        <p><input type="submit" value="Удалить"></p>
      </div>
    </div>
  </form>
</div>


<div class="edit_penal">
  <br><strong>Редактирование жанра</strong>
  <form name="genre_edit_form" action ="/editGenre" method="post">
    <div class="book_info">
      <div class="book_details">
        <br><strong>Выберите жанр для редактирования:</strong><select name="genre_for_edit">
        <%
          for (Genre genre : genreManager.loadAllGenres() ){
        %>
        <option><%=genre.getName()%></option>
        <% } %>
      </select>
        <br><strong>Введите его новое имя:</strong><input name="genre_name" type="text">

        <p><input type="submit" value="Редактировать"></p>
      </div>
    </div>
  </form>
</div>