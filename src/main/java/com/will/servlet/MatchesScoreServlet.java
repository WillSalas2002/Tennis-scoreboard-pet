package com.will.servlet;

import com.will.dto.TempMatchStorage;
import com.will.model.Match;
import com.will.service.score_models.MatchScoreModel;
import com.will.model.Player;
import com.will.service.FinishedMatchesPersistenceService;
import com.will.service.MatchScoreCalculationService;
import com.will.util.JspHelper;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.UUID;

@WebServlet("/matches-score")
public class MatchesScoreServlet extends HttpServlet {
    private final MatchScoreCalculationService calculationService = new MatchScoreCalculationService();
    private final FinishedMatchesPersistenceService persistenceService = new FinishedMatchesPersistenceService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        UUID uuid = UUID.fromString(req.getParameter("uuid"));
        MatchScoreModel match = TempMatchStorage.getMatchScoreModel(uuid);
        req.setAttribute("uuid", uuid);
        req.setAttribute("match", match);
        req.getRequestDispatcher(JspHelper.getPath("matches-score")).forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        UUID uuid = UUID.fromString(req.getParameter("uuid"));
        MatchScoreModel matchScoreModel = TempMatchStorage.getMatchScoreModel(uuid);
        int scoringPlayerId = Integer.parseInt(req.getParameter("scorer-id"));
        calculationService.addScore(scoringPlayerId, matchScoreModel);
        if (!matchScoreModel.isMatchFinished()) {
            doGet(req, resp);
            return;
        }
        Match match = new Match(
                new Player(matchScoreModel.getPlayer1().getId()),
                new Player(matchScoreModel.getPlayer2().getId()),
                new Player(matchScoreModel.getWinner().getId())
        );
        persistenceService.save(match);
        TempMatchStorage.removeMatch(uuid);
        req.setAttribute("match", matchScoreModel);
        req.getRequestDispatcher(JspHelper.getPath("matches-score")).forward(req, resp);
    }
}
