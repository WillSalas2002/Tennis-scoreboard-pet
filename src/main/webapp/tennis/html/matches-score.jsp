<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <title>Matches Score</title>
    <c:set var="contextPath" value="${pageContext.request.contextPath}"/>
<%--    <link rel="stylesheet" type="text/css" href="${contextPath}/tennis/css/finished-matches.css" />--%>
</head>
<body>
    <c:set var="match" value="${requestScope.match}"/>
    <div class="title">Matches Score page</div>
    <div class="match container">
        <div>Match Score</div>
        <div>${match.player1.name}</div>
        <div>${match.player2.name}</div>
        <c:if test="${match.player1.getMatchScore().size() != 0}">
            <c:forEach var="score" items="${match.player1.getMatchScore()}">
                ${score}
            </c:forEach>
            <c:forEach var="score" items="${match.player2.getMatchScore()}">
                ${score}
            </c:forEach>
        </c:if>
    </div>
    <div class="set container">
        <div>Set Score</div>
        <div>${match.player1.name}</div>
        <div>${match.player2.name}</div>
        <div>${match.player1.setScore}</div>
        <div>${match.player2.setScore}</div>
    </div>
    <div class="players container">
        <div class="player">
            Player: ${match.player1.name}
            Points: ${match.player1.point.value}
            <form method="POST" action="${contextPath}/matches-score?uuid=${requestScope.uuid}">
                <button type="submit" name="scorer-id" value="${match.player1.id}">${match.player1.name} Scored</button>
            </form>
        </div>
        <div class="player">
            Player: ${match.player2.name}
            Points: ${match.player2.point.value}
            <form method="POST" action="${contextPath}/matches-score?uuid=${requestScope.uuid}">
                <button type="submit" name="scorer-id" value="${match.player2.id}">${match.player2.name} Scored</button>
            </form>
        </div>
    </div>

</body>
</html>
