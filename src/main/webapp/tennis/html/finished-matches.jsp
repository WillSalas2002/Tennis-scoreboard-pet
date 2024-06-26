<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@ page contentType="text/html;charset=UTF-8"%>
<html>
<head>
    <title>Matches</title>
    <c:set var="contextPath" value="${pageContext.request.contextPath}"/>
    <link rel="stylesheet" type="text/css" href="${contextPath}/tennis/css/finished-matches.css" />
</head>
<body>
<div class="search-container">
    <form id="search-form" method="GET" action="${contextPath}/matches">
        <label for="search-input">
            <input id="search-input" type="text" name="filter_by_player_name" value="${requestScope.name}" placeholder="Enter player name"/>
        </label>
        <button type="submit">Search</button>
    </form>
</div>
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
    <div class="pagination-container">
        <c:set var="currentPage" value="${param.page}" />
        <form id="previous-page-form" method="GET" action="${contextPath}/matches">
            <input type="hidden" name="page" value="${currentPage - 1}" />
            <input class="page arrow" type="submit" name="action" value="<" ${currentPage == 1 ? 'disabled' : ''} />
        </form>
        <c:forEach var="page" items="${requestScope.pages}">
            <form class="page-number" method="GET" action="${contextPath}/matches">
                <input class="page" type="submit" name="page" value="${page}" ${page == currentPage ? 'disabled' : ''} />
            </form>
        </c:forEach>
        <form id="next-page-form" method="GET" action="${contextPath}/matches">
            <input type="hidden" name="page" value="${currentPage + 1}" />
            <input class="page arrow" type="submit" name="action" value=">" ${currentPage == requestScope.pages.size() ? 'disabled' : ''} />
        </form>
    </div>
</c:if>
<c:if test="${empty requestScope.matches}">
    <div class="info">
        <div>No matches found</div>
        <div><a href="http://localhost:8080/matches?page=1">Go to first page</a></div>
    </div>
</c:if>
</body>
</html>
