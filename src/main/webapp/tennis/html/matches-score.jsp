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

    <h1>Matches Score page</h1>
    <h3>First Player: ${match.player1.name} ===> ${match.player1.point} : ${match.player1.setScore} :
        ${match.player1.gameScore}</h3>
    <h3>Second Player: ${match.player2.name} ===> ${match.player2.point} : ${match.player2.setScore} :
        ${match.player2.gameScore}</h3>
    <form method="POST" action="${contextPath}/matches-score?uuid=${requestScope.uuid}">
        <button type="submit" name="scorer-id" value="${match.player1.id}">Player 1 Scored</button>
        <button type="submit" name="scorer-id" value="${match.player2.id}">Player 2 Scored</button>
    </form>


</body>
</html>
