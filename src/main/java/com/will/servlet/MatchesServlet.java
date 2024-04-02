package com.will.servlet;

import com.will.service.MatchesRepresentationService;
import com.will.util.JspHelper;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/matches")
public class MatchesServlet extends HttpServlet {
    private final MatchesRepresentationService service = new MatchesRepresentationService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String pageStr = req.getParameter("page");
        String name = req.getParameter("filter_by_player_name");

        req.setAttribute("matches", service.findAll(name, pageStr));
        req.getRequestDispatcher(JspHelper.getPath("finished-matches")).forward(req, resp);
    }
}
