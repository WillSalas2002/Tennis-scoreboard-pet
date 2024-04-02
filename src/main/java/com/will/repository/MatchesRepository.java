package com.will.repository;

import com.will.dto.MatchFilter;
import com.will.model.Match;
import com.will.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;

import java.util.List;

public class MatchesRepository {

    private final SessionFactory sessionFactory = HibernateUtil.getSessionFactory();

    public List<Match> findAll(MatchFilter matchFilter) {
        try (Session session = sessionFactory.getCurrentSession()) {
            session.beginTransaction();

            StringBuilder hql = new StringBuilder("""
                    SELECT m
                    FROM Match m
                        LEFT JOIN FETCH m.player1
                        LEFT JOIN FETCH m.player2
                        LEFT JOIN FETCH m.winner
                    WHERE 1=1
                    """);

            // If name is not null append query for searching by name
            if (matchFilter.name() != null) {
                hql.append(" AND (UPPER(m.player1.name) LIKE UPPER(:name) OR UPPER(m.player2.name) LIKE UPPER(:name))");
            }

            Query<Match> query = session.createQuery(hql.toString(), Match.class);

            // Set name param with wildcard %, to make it possible to search with starting chars
            if (matchFilter.name() != null) {
                query.setParameter("name", matchFilter.name() + "%");
            }

            // Set offset and limit
            query.setFirstResult(matchFilter.offset());
            query.setMaxResults(matchFilter.limit());

            List<Match> matches = query.getResultList();
            session.getTransaction().commit();
            return matches;
        }
    }
}
