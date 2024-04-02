<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Matches</title>
    <c:set var="contextPath" value="${pageContext.request.contextPath}"/>
    <link rel="stylesheet" type="text/css" href="${contextPath}/tennis/css/finished-matches.css" />
</head>
<body>
<c:if test="${not empty requestScope.matches}">
    <div class="table-box">
        <div class="table-row table-head">
            <div class="table-cell">Score</div>
        </div>
        <div class="table-row table-head">
            <div class="table-cell">Id</div>
            <div class="table-cell">Player 1</div>
            <div class="table-cell">Player 2</div>
            <div class="table-cell">Winner</div>
        </div>
        <c:forEach var="match" items="${requestScope.matches}">
            <div class="table-row">
                <div class="table-cell">${match.id}</div>
                <div class="table-cell">${match.player1.name}</div>
                <div class="table-cell">${match.player2.name}</div>
                <div class="table-cell last-cell">${match.winner.name}</div>
            </div>
        </c:forEach>
    </div>
</c:if>
</body>
</html>
