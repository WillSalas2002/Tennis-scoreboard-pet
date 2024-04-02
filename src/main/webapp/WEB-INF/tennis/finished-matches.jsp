<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Matches</title>
</head>
<body>
<h1>List of finished matches: </h1>
<h1>${requestScope.matches.size()}</h1>
<c:if test="${not empty requestScope.matches}">
    <ol>
        <c:forEach var="match" items="${requestScope.matches}">
            <li>
                    ${match}
            </li>
        </c:forEach>
    </ol>
</c:if>
</body>
</html>
