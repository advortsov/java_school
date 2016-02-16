<%@ page import="com.tsystems.javaschool.dao.entity.Book" %>
<%@ page import="com.tsystems.javaschool.dao.entity.Publisher" %>
<%@ page import="com.tsystems.javaschool.dao.entity.Author" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<!DOCTYPE html>
<%@include file="../WEB-INF/jspf/left_menu.jspf" %>

<jsp:useBean id="bookManager" class="com.tsystems.javaschool.services.impl.BookManagerImpl" scope="page"/>
<jsp:useBean id="publisherManager" class="com.tsystems.javaschool.services.impl.PublisherManagerImpl" scope="page"/>
<jsp:useBean id="authorManager" class="com.tsystems.javaschool.services.impl.AuthorManagerImpl" scope="page"/>

<div class="edit_penal">

  <br><strong>Редактирование книги</strong>

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
    Book book = null;
    book = bookManager.findBookById(Long.valueOf(request.getParameter("book_id")));
  %>
  <%--<form name="book_edit_form" enctype="multipart/form-data" action="/EditBook" method="post">--%>
  <form name="book_edit_form" action="/EditBook" method="post">

    <div class="book_info">
      <div class="book_details">
        <%--<br><input type="file" name="cover" multiple accept="image/jpeg">--%>
        <br><strong>Название:</strong><input name="book_name" type="text" value="<%=book.getName()%>">
        <br><strong>ISBN:</strong><input name="book_isbn" type="text" value="<%=book.getIsbn()%>">

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
          for (Author author : authorManager.loadAllAuthors() ){
        %>
        <option><%=author.getFio()%></option>
        <% }
          session.setAttribute("currentBook", book);
        %>
      </select>

        <br><strong>Количество страниц:</strong><input name="book_pages" type="text" value="<%=book.getPageCount()%>">
        <br><strong>Год издания:</strong><input name="book_year" type="text" value="<%=book.getPublishYear()%>">
        <br><strong>Цена:</strong><input name="book_price" type="text" value="<%=book.getPrice()%>"><strong> руб.</strong>
        <p><input type="submit" value="Сохранить"></p>
      </div>
    </div>

  </form>

</div>