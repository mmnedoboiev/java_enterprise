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
                        <th>Actions</th>
                        <th>Edit</th>
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
                            <td>
                                <!-- Форма видалення для кожної книги -->
                                <form action="${pageContext.request.contextPath}/delete" method="post">
                                    <input type="hidden" name="id" value="${book.id}">
                                    <button type="submit">Delete</button>
                                </form>
                            </td>
                                    <td>
                                        <!-- Кнопка для редагування -->
                                        <a href="${pageContext.request.contextPath}/edit?id=${book.id}">Edit</a>
                                    </td>
                        </tr>
                    </c:forEach>
                </tbody>
            </table>

            <br><br>
            <a href="${pageContext.request.contextPath}/addbook">Add Book</a>


            <br>
            <br>
            <a href="logout">Logout</a>
        </body>
        </html>
    </c:otherwise>
</c:choose>
