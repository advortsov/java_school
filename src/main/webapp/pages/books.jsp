<%@ page import="com.tsystems.javaschool.dao.entity.Book" %>
<%@ page import="com.tsystems.javaschool.services.enums.SearchType" %>
<%@ page import="java.util.List" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>

<%@include file="../WEB-INF/jspf/left_menu.jspf" %>

<jsp:useBean id="bookManager" class="com.tsystems.javaschool.services.impl.BookManagerImpl"
             scope="page"/>

<div class="book_list">

    <%
        List<Book> list = null;
      
        if (request.getParameter("genre") != null && !request.getParameter("genre").equals("all")) {
            String genreName = String.valueOf(request.getParameter("genre"));
            list = bookManager.getBooksByGenre(genreManager.findByGenreName(genreName));
        }
        else if (request.getParameter("genre").equals("all")){
            list = bookManager.loadAllBooks();

        } else if (request.getParameter("search_string") != null) {
            String searchStr = request.getParameter("search_string");
            SearchType type = SearchType.TITLE;
                if (request.getParameter("search_option").equals("Автор")) {
                    type = SearchType.AUTHOR;
                }

                if (searchStr != null && !searchStr.trim().equals("")) {
                    list = bookManager.getBooksBySearch(searchStr, type);
                }
        }
       %>
    <h5 style="text-align: left; margin-top:20px;">Найдено книг: <%=list.size() %> </h5>
              <%  session.setAttribute("currentBookList", list);
                for (Book book : list) {

    %>

    <div class="book_info">
        <div class="book_title">
            <p> <%=book.getName()%></p>
        </div>
        <div class="book_image">
            <a href="#"><img src="<%=request.getContextPath()%>/ShowImage?index=<%=list.indexOf(book)%>" height="250" width="190" alt="Обложка"/></a>
        </div>
        <div class="book_details">
            <br><strong>ISBN:</strong> <%=book.getIsbn()%>
            <br><strong>Издательство:</strong> <%=book.getPublisher()%>
            <br><strong>Количество страниц:</strong> <%=book.getPageCount()%>
            <br><strong>Год издания:</strong> <%=book.getPublishYear()%>
            <br><strong>Автор:</strong> <%=book.getAuthor()%>
            <br><strong>Цена:</strong> <%=book.getPrice()%> <strong> руб.</strong>
            <br><a href="../pages/edit.jsp?book_id=<%=book.getId()%>">Редактировать</a>
            <p style="margin:10px;"> <a href="#">Добавить в корзину</a></p>
        </div>
    </div>


    <%}%>

    

</div>
