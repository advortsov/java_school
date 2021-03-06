<%@ page import="com.tsystems.javaschool.dao.entity.Book" %>
<%@ page import="com.tsystems.javaschool.services.util.Managers" %>
<%@ page import="java.util.List" %>
<%@page contentType="text/html" pageEncoding="UTF-8" %>

<!DOCTYPE html>


<%@include file="../WEB-INF/jspf/left_menu.jspf" %>

<jsp:useBean id="bookManager" class="com.tsystems.javaschool.services.impl.BookManagerImpl"
             scope="page"/>

<div class="book_list">

    <%

        List<Book> currentBookList = (List<Book>) session.getAttribute("currentBookList");
        if (currentBookList == null) {
            currentBookList = Managers.getBookManager().loadAllBooks();
        }
    %>
    <h5 style="text-align: left; margin-top:20px;">Найдено книг: <%=currentBookList.size() %>
    </h5>
    <%
        for (Book book : currentBookList) {
    %>

    <div class="book_info">
        <div class="book_title">
            <p><%=book.getName()%>
            </p>
        </div>
        <div class="book_image">

            <a href=""><img src="<%=request.getContextPath()%>/ShowImage?index=<%=currentBookList.indexOf(book)%>"
                            height="250" width="190" alt="Обложка" id="cover"/></a>



        </div>
        <div class="book_details">
            <br><strong>ISBN:</strong> <%=book.getIsbn()%>
            <br><strong>Издательство:</strong> <%=book.getPublisher()%>
            <br><strong>Количество страниц:</strong> <%=book.getPageCount()%>
            <br><strong>Год издания:</strong> <%=book.getPublishYear()%>
            <br><strong>Автор:</strong> <%=book.getAuthor()%>
            <br><strong>Цена:</strong> <%=book.getPrice()%> <strong> руб.</strong>
            <% if (request.isUserInRole("admin")) {%>
            <br><a href="../admin_pages/edit.jsp?book_id=<%=book.getId()%>">Редактировать</a>
            <% } %>
            <p style="margin:10px;"><a href="/addToCart?book_id=<%=book.getId()%>">Добавить в корзину</a></p>
        </div>
    </div>


    <%}%>


</div>
