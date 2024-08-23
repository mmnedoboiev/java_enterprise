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
            <title>Edit Book</title>
        </head>
        <body>
            <h1>Edit Book</h1>

            <form action="${pageContext.request.contextPath}/edit" method="post">
                <input type="hidden" name="id" value="${book.id}">

                <label for="name">Name:</label>
                    <input type="text" id="name" name="name" value="${book.name}" required>
                <br>

                <label for="author">Author:</label>
                <input type="text" id="author" name="author" value="${book.author}" required>
                <br>

                <label for="year">Year:</label>
                <input type="text" id="year" name="year" value="${book.year}" required>
                <br>

                <label for="genre">Genre:</label>
                <input type="text" id="genre" name="genre" value="${book.genre}" required>
                <br>

                <button type="submit">Update</button>
            </form>

            <br>
            <a href="${pageContext.request.contextPath}/select">Back to List</a>
            <br>
            <br>
            <a href="logout">Logout</a>
        </body>
        </html>
    </c:otherwise>
</c:choose>
