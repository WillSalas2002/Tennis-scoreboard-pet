package com.will.repository;

import com.will.model.Match;
import com.will.model.Player;
import com.will.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

public class FinishedMatchRepository {
    private final SessionFactory sessionFactory = HibernateUtil.getSessionFactory();

    public Match save(Match match) {
        try (Session session = sessionFactory.getCurrentSession()) {
            session.beginTransaction();

            Player player1 = match.getPlayer1();
            Player player2 = match.getPlayer2();

            player1 = session.get(Player.class, player1.getId());
            player2 = session.get(Player.class, player2.getId());
            Player winner = match.getWinner().getId() == player1.getId() ? player1 : player2;

            Match persistedMatch = new Match(player1, player2, winner);
            session.persist(persistedMatch);
            session.getTransaction().commit();

            return persistedMatch;
        }
    }
}
