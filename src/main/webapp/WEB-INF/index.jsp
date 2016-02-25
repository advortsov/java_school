<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
        <title>Магазин книг:: Вход</title>
        <link href="../css/style_index.css" rel="stylesheet" type="text/css">
    </head>

    <body>
        <%--<%session.invalidate(); %>--%>
        <div class="main">

            <div class="content">
                <p class="title"><span class="text"><img src="../images/lib.png" width="76" height="77" hspace="10" vspace="10" align="middle"></span></p>
                <p class="title">Магазин книг</p>

                <p class="text" align="center">
                    <a href="http://www.t-systems.ru/career/java-school/1037760">
                        Проект Java-school, T-Systems</a></p>
                <p>&nbsp;</p>

            </div>

            <div class="login_div" >
                <p class="title">Войти: </p>
                <form class="login_form" name="username" action="j_security_check" method="POST" id="login_form">
                    <br>Логин: <input type="text" name="j_username" value="" size="20" /></br>
                    <br>Пароль: <input type="password" name="j_password" value="" size="20" /></br>

                    <br><a href="/pages/main.jsp">Войти анонимно</a></br></p>

                    <input type="submit" value="Войти" />
                </form>

            </div>

            <div class="footer">
                Разработчик: Александр Дворцов, 2016 г.
            </div>
        </div>


    </body>
</html>
