<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page import="org.example.Book" %>

<c:choose>
    <c:when test="${sessionScope.user == null}">
        <c:redirect url="login.jsp" />
    </c:when>
    <c:otherwise>
        <html>
        <head>
            <title>Додати Книгу</title>
        </head>
        <body>
            <h1>Додати нову книгу</h1>
            <form action="addbook" method="post">
                <label for="name">Назва книги:</label>
             <input type="text" id="name" name="name" required><br>
                <label for="author">Імя автора книги книги:</label>
                <input type="text" id="author" name="author" required><br>
                <label for="year">Рік видання:</label>
                <input type="text" id="year" name="year" required><br>
                <label for="genre">Жанр:</label>
                <input type="text" id="genre" name="genre" required><br>
                <input type="submit" value="Додати">
            </form>

            <a href="${pageContext.request.contextPath}/select">Select</a>
        </body>
        </html>
    </c:otherwise>
</c:choose>
