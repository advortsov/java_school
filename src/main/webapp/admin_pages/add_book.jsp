<%@ page import="com.tsystems.javaschool.dao.entity.Publisher" %>
<%@ page import="com.tsystems.javaschool.dao.entity.Author" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<!DOCTYPE html>
<%@include file="../WEB-INF/jspf/left_menu.jspf" %>

<jsp:useBean id="bookManager" class="com.tsystems.javaschool.services.impl.BookManagerImpl" scope="page"/>
<jsp:useBean id="publisherManager" class="com.tsystems.javaschool.services.impl.PublisherManagerImpl" scope="page"/>
<jsp:useBean id="authorManager" class="com.tsystems.javaschool.services.impl.AuthorManagerImpl" scope="page"/>

<div class="edit_penal">

  <br><strong>Добавление книги</strong>

  <form name="book_add_form" enctype="multipart/form-data" action ="/bookList" method="post">
    <div class="book_info">
      <div class="book_details">
        <br><strong>Обложка:</strong><input type="file" name="cover" multiple accept="image/jpeg">
        <br><strong>Название:</strong><input name="book_name" type="text">
        <br><strong>ISBN:</strong><input name="book_isbn" type="text">
        <br><strong>Жанр:</strong><select name="book_genre">
        <%
          for (Genre genre : genreManager.loadAllGenres() ){
        %>
        <option><%=genre.getName()%></option>
        <% } %>
      </select>

        <br><strong>Издательство:</strong><select name="book_publisher">
        <%
          for (Publisher publisher : publisherManager.loadAllPublishers() ){
        %>
        <option><%=publisher.getName()%></option>
        <% } %>
      </select>

        <br><strong>Автор:</strong><select name="book_author">
        <%
          for (Author author : authorManager.loadAllAuthors() ){//
        %>
        <option><%=author.getName()%></option>
        <%
          }
        %>
      </select>

        <br><strong>Количество страниц:</strong><input name="book_pages" type="text">
        <br><strong>Год издания:</strong><input name="book_year" type="text">
        <br><strong>количество на складе:</strong><input name="book_count" type="number">
        <br><strong>Цена:</strong><input name="book_price" type="text"><strong> руб.</strong>
        <input type="hidden" name="action" value="add"></p>
        <p><input type="submit" value="Добавить"></p>
      </div>
    </div>

  </form>

</div>