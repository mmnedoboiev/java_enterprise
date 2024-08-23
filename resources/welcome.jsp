<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<c:choose>
    <c:when test="${sessionScope.user == null}">
        <c:redirect url="login.jsp" />
    </c:when>
    <c:otherwise>
        <html>
        <meta>
        </meta>
        <body>
        <p>Hello </p>
        <a href="logout">Logout</a>
        </body>
        </html>
    </c:otherwise>
</c:choose>
