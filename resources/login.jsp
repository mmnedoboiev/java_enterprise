<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<%-- Перевіряємо, чи існує активна сесія та чи є в ній атрибут "user" --%>
<c:choose><!-- Використовуємо JSTL тег для умовної логіки -->
    <c:when test="${sessionScope.user != null}"><!-- Якщо користувач не авторизований, перенаправляємо на сторінку входу -->
        <c:redirect url="welcome.jsp" />
    </c:when>
    <c:otherwise><!-- В іншому випадку, якщо користувач авторизований -->
        <html>
        <head>
            <title>Login</title>
        </head>
        <body>
            <h1>Login</h1>
            <form action="login" method="post">
                <label for="login">Login:</label>
                <input type="text" id="login" name="login" required>
                <br>
                <label for="password">Password:</label>
                <input type="password" id="password" name="password" required>
                <br>
                <input type="submit" value="Login">
            </form>
            <% if (request.getParameter("error") != null) { %>
                <p style="color:red;">Invalid login or password</p>
            <% } %>
        </body>
        </html>
    </c:otherwise>
</c:choose>
