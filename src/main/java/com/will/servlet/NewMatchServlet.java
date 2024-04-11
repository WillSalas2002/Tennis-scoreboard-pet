package com.will.servlet;

import com.will.dto.TempMatchStorage;
import com.will.service.score_models.MatchScoreModel;
import com.will.model.Player;
import com.will.service.NewMatchService;
import com.will.util.JspHelper;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.UUID;

@WebServlet("/new-match")
public class NewMatchServlet extends HttpServlet {

    private final NewMatchService newMatchService = new NewMatchService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher(JspHelper.getPath("new-match")).forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String firstPlayerName = req.getParameter("first-player");
        String secondPlayerName = req.getParameter("second-player");

        Player firstPlayer = newMatchService.findOrCreatePlayer(firstPlayerName);
        Player secondPlayer = newMatchService.findOrCreatePlayer(secondPlayerName);

        MatchScoreModel matchScoreModel = new MatchScoreModel(firstPlayer, secondPlayer);
        UUID matchUuid = UUID.randomUUID();

        // Add matches to temporary storage that exists only in memory of the program
        TempMatchStorage.addMatch(matchUuid, matchScoreModel);

        resp.sendRedirect(req.getContextPath() + "/matches-score?uuid=" + matchUuid);
    }
}
