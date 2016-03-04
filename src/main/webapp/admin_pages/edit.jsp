<%@ page import="com.tsystems.javaschool.dao.entity.Author" %>
<%@ page import="com.tsystems.javaschool.dao.entity.Book" %>
<%@ page import="com.tsystems.javaschool.dao.entity.Publisher" %>
<%@ page import="com.tsystems.javaschool.services.util.Managers" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<!DOCTYPE html>
<%@include file="../WEB-INF/jspf/left_menu.jspf" %>

<jsp:useBean id="bookManager" class="com.tsystems.javaschool.services.impl.BookManagerImpl" scope="page"/>
<jsp:useBean id="publisherManager" class="com.tsystems.javaschool.services.impl.PublisherManagerImpl" scope="page"/>
<jsp:useBean id="authorManager" class="com.tsystems.javaschool.services.impl.AuthorManagerImpl" scope="page"/>

<%--<div class="edit_penal">--%>

<%--<br><strong>Редактирование книги</strong>--%>

<%--<style>--%>
<%--table {--%>
<%--border-collapse: collapse;--%>
<%--width: 100%;--%>
<%--}--%>

<%--th, td {--%>
<%--text-align: left;--%>
<%--padding: 8px;--%>
<%--}--%>

<%--tr:nth-child(even){background-color: #f2f2f2}--%>

<%--th {--%>
<%--background-color: #4CAF50;--%>
<%--color: white;--%>
<%--}--%>
<%--</style>--%>


<%--<%--%>
<%--Book book = null;--%>
<%--book = bookManager.findBookById(Long.valueOf(request.getParameter("book_id")));--%>
<%--%>--%>
<%--<form name="book_edit_form" enctype="multipart/form-data" action ="/bookList" method="post">--%>

<%--<div class="book_info">--%>
<%--<div class="book_details">--%>
<%--<br><strong>Обложка:</strong><input type="file" name="cover" multiple accept="image/jpeg">--%>
<%--<br><strong>Название:</strong><input name="book_name" type="text" value="<%=book.getName()%>">--%>
<%--<br><strong>ISBN:</strong><input name="book_isbn" type="text" value="<%=book.getIsbn()%>">--%>
<%--<br><strong>Жанр:</strong><select name="book_genre">--%>
<%--<%--%>
<%--for (Genre genre : genreManager.loadAllGenres() ){--%>
<%--%>--%>
<%--<option><%=genre.getName()%></option>--%>
<%--<% } %>--%>
<%--</select>--%>

<%--<br><strong>Издательство:</strong><select name="book_publisher">--%>
<%--<%--%>
<%--for (Publisher publisher : publisherManager.loadAllPublishers() ){--%>
<%--%>--%>
<%--<option><%=publisher.getName()%></option>--%>
<%--<% } %>--%>
<%--</select>--%>

<%--<br><strong>Автор:</strong><select name="book_author">--%>
<%--<%--%>
<%--for (Author author : authorManager.loadAllAuthors() ){//--%>
<%--%>--%>
<%--<option><%=author.getName()%></option>--%>
<%--<% }--%>
<%--session.setAttribute("currentBook", book);--%>
<%--%>--%>
<%--</select>--%>

<%--<br><strong>Количество страниц:</strong><input name="book_pages" type="text" value="<%=book.getPageCount()%>">--%>
<%--<br><strong>Год издания:</strong><input name="book_year" type="text" value="<%=book.getPublishYear()%>">--%>
<%--<br><strong>Цена:</strong><input name="book_price" type="text" value="<%=book.getPrice()%>"><strong> руб.</strong>--%>
<%--<input name="action" type="text" value="edit">--%>

<%--<p><input type="submit" value="Сохранить"> </p>--%>
<%--</div>--%>
<%--</div>--%>

<%--</form>--%>

<%--</div>--%>


<div class="edit_penal">

    <br><strong>Редактирование книги</strong>

    <p>&nbsp;</p>

    <%
        Book book = null;
        book = bookManager.findBookById(Long.valueOf(request.getParameter("book_id")));
    %>
    <form name="book_edit_form" enctype="multipart/form-data" accept-charset="utf-8"
          action="/bookList" method="post">
        <div class="edit_book_info">
            <div>

                <link rel="stylesheet" href="${pageContext.request.contextPath}/css/form.css" type="text/css"/>

                <div class="field">
                    <label for="cover">Обложка:</label><input type="file" name="cover" id="cover" multiple
                                                              accept="image/jpeg">
                </div>
                <div class="field">
                    <label for="book_name">Название:</label><input name="book_name" type="text" id="book_name"
                                                                   pattern=".{5,255}"
                                                                   required
                                                                   value="<%=book.getName()%>"
                                                                   title="Название может содержать от 5 до 255 символов">
                </div>
                <div class="field">
                    <label for="book_isbn">ISBN:</label><input name="book_isbn" type="text" id="book_isbn"
                                                               pattern=".{5,35}"
                                                               required
                                                               value="<%=book.getIsbn()%>"
                                                               title="ISBN может содержать от 5 до 35 символов">
                </div>
                <div class="field">

                    <label for="book_genre">Жанр:</label><select name="book_genre" id="book_genre">
                    <%
                        for (Genre genre : Managers.getGenreManager().loadAllGenres()) {
                    %>
                    <option><%=genre.getName()%>
                    </option>
                    <% } %>
                </select>
                </div>

                <div class="field">
                    <label for="book_publisher">Издательство:</label><select name="book_publisher" id="book_publisher">
                    <%
                        for (Publisher publisher : Managers.getPublisherManager().loadAllPublishers()) {
                    %>
                    <option><%=publisher.getName()%>
                    </option>
                    <% } %>
                </select>
                </div>
                <div class="field">
                    <label for="book_author">Автор:</label><select name="book_author" id="book_author">
                    <%
                        for (Author author : Managers.getAuthorManager().loadAllAuthors()) {
                    %>
                    <option><%=author.getName()%>
                    </option>
                    <%
                        }
                        session.setAttribute("currentBook", book);
                    %>
                </select>
                </div>

                <div class="field">
                    <label for="book_pages">Количество страниц:</label><input name="book_pages" type="number"
                                                                              id="book_pages" pattern="[0-9]{2,4}"
                                                                              required
                                                                              value="<%=book.getPageCount()%>"
                                                                              title="Количество страниц может быть от 10 до 9999">
                </div>
                <div class="field">
                    <label for="book_year">Год издания:</label><input name="book_year" type="number" id="book_year"
                                                                      pattern="[0-9]{4,4}"
                                                                      required
                                                                      value="<%=book.getPublishYear()%>"
                                                                      title="Год должен состоять из 4 цифр">
                </div>
                <div class="field">
                    <label for="book_count">Количество:</label><input name="book_count" type="number" id="book_count"
                                                                      pattern="[0-9]{1,3}"
                                                                      required
                                                                      value="<%=book.getQuantity()%>"
                                                                      title="Количество экземпляров одной книги на складе может быть до 999">
                </div>
                <div class="field">
                    <label for="book_price">Цена, руб:</label><input name="book_price" type="number" id="book_price"
                                                                     pattern="[0-9]{1,5}"
                                                                     value="<%=book.getPrice()%>"
                                                                     required title="Цена может быть до 99 999 руб.">
                </div>

                <input type="hidden" name="action" value="edit"></p>
                <p><input type="submit" value="Сохранить"></p>
            </div>
        </div>

    </form>

</div>