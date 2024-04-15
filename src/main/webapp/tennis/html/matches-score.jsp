<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <title>Matches Score</title>
    <c:set var="contextPath" value="${pageContext.request.contextPath}"/>
    <link rel="stylesheet" type="text/css" href="${contextPath}/tennis/css/matches-score.css" />
</head>
<body>
    <c:set var="match" value="${requestScope.match}"/>
    <div class="tennis-scoreboard">
        <div class="player-names">
            <div>Player №1: ${match.player1.name}</div>
            <div>Player №2: ${match.player2.name}</div>
        </div>
        <hr/>
        <div class="scoreboard">
            <div class="previous-sets-section">
                <div class="title">
                    PREVIOUS SETS
                </div>
                <div class="player-sets">
                    <div class="sets">${match.player1.matches.get(0)}</div>
                    <div class="sets">${match.player1.matches.get(1)}</div>
                    <div class="sets">${match.player1.matches.get(2)}</div>
                </div>
                <div class="player-sets">
                    <div class="sets">${match.player2.matches.get(0)}</div>
                    <div class="sets">${match.player2.matches.get(1)}</div>
                    <div class="sets">${match.player2.matches.get(2)}</div>
                </div>
            </div>
            <c:if test="${match.isGameFinished()}">
                <div class="finished-match-info">Match is finished!</div>
            </c:if>
            <c:if test="${not match.isGameFinished()}">
                <div class="button-section">
                    <div class="title">PLAYER</div>
                    <div class="button">
                        <form method="POST" action="${contextPath}/matches-score?uuid=${requestScope.uuid}">
                            <input type="hidden" name="scorer-id" value="${match.player1.id}">
                            <input type="submit" value="Player 1"/>
                        </form>
                    </div>
                    <div class="button">
                        <form method="POST" action="${contextPath}/matches-score?uuid=${requestScope.uuid}">
                            <input type="hidden" name="scorer-id" value="${match.player2.id}">
                            <input type="submit" value="Player 2"/>
                        </form>
                    </div>
                </div>
            </c:if>
            <div class="sets-section">
                <div class="title">SETS</div>
                <div class="sets">${match.player1.setScore}</div>
                <div class="sets">${match.player2.setScore}</div>
            </div>
            <div class="points-section">
                <c:if test="${match.isSixAll() == true}">
                    <div class="title">POINTS</div>
                    <div class="point">${match.player1.tieSetScore}</div>
                    <div class="point">${match.player2.tieSetScore}</div>
                </c:if>
                <c:if test="${match.isSixAll() == false}">
                    <div class="title">POINTS</div>
                    <div class="point">${match.player1.point.value}</div>
                    <div class="point">${match.player2.point.value}</div>
                </c:if>
            </div>
        </div>
        <c:if test="${match.isMatchFinished()}">
            <div class="winner-section">
                <div>Winner is: ${match.winner.name}</div>
            </div>
        </c:if>
    </div>
</body>
</html>
