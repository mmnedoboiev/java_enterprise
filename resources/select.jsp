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
            <title>List of Books</title>
        </head>
        <body>
            <h1>List of Books</h1>
            <table border="1">
                <thead>
                    <tr>
                        <th>ID</th>
                        <th>Name</th>
                        <th>Author</th>
                        <th>Year</th>
                        <th>Genre</th>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach var="book" items="${books}">
                        <tr>
                            <td>${book.id}</td>
                            <td>${book.name}</td>
                            <td>${book.author}</td>
                            <td>${book.year}</td>
                            <td>${book.genre}</td>
                        </tr>
                    </c:forEach>
                </tbody>
            </table>
            <a href="${pageContext.request.contextPath}/addbook">AddBook</a>

            <form action="${pageContext.request.contextPath}/delete" method="post">
                <input name="id" type="hidden" value="${book.id}">
                <button type="submit">Delete</button>
            </form>
        </body>
        </html>
    </c:otherwise>
</c:choose>
