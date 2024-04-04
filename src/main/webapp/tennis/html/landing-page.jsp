<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <title>Tennis Scoreboard</title>
    <c:set var="contextPath" value="${pageContext.request.contextPath}"/>
    <link rel="stylesheet" type="text/css" href="${contextPath}/tennis/css/landing-page.css" />
</head>
<body>
<div class="container">
    <div class="title">Tennis Scoreboard</div>
    <div class="links">
        <a href="http://localhost:8080/new-match">Create a new match</a>
        <a href="http://localhost:8080/matches">Completed matches</a>
    </div>
</div>
</body>
</html>
