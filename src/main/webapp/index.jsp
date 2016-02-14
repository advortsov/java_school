<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
        <title>Магазин книг:: Вход</title>
        <link href="css/style_index.css" rel="stylesheet" type="text/css">
    </head>

    <body>
        <%session.invalidate(); %>
        <div class="main">

            <div class="content">
                <p class="title"><span class="text"><img src="images/lib.png" width="76" height="77" hspace="10" vspace="10" align="middle"></span></p>
                <p class="title">Магазин книг</p>

                <p class="text" align="center">
                    <a href="http://www.t-systems.ru/career/java-school/1037760">
                        Проект Java-school, T-Systems</a></p>
                <p>&nbsp;</p>

            </div>

            <div class="login_div" >
                <p class="title">Войти: </p>
                <form class="login_form" name="username" action="pages/main.jsp"
                      method="POST"  id="login_form" >
                    Логин: <input type="text" name="username" value="" size="20" />
                    Пароль: <input type="password" name="password" value="" size="20" />
                    <a href="">Войти анонимно</a></p>

                    <input type="submit" value="Войти" />
                </form>

            </div>

            <div class="footer">
                Разработчик: Александр Дворцов, 2016 г.
            </div>
        </div>


    </body>
</html>
