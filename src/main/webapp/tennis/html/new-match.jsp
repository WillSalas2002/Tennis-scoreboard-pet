<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <title>New Match</title>
    <c:set var="contextPath" value="${pageContext.request.contextPath}"/>
    <link rel="stylesheet" type="text/css" href="${contextPath}/tennis/css/new-match.css"/>
</head>
<body>
<div class="container">
    <form method="POST" action="${contextPath}/new-match">
        <div class="title">New Match Form</div>
        <label for="player-1"><span>First Player:</span>
            <input id="player-1" type="text" name="first-player"/>
        </label>
        <label for="player-2"><span>Second Player:</span>
            <input id="player-2" type="text" name="second-player"/>
        </label>
        <input type="submit" value="Start"/>
    </form>
</div>
</body>
</html>
