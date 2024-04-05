package com.will.repository;

import com.will.model.Player;
import com.will.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import java.util.Optional;

public class NewMatchRepository {

    private final SessionFactory sessionFactory = HibernateUtil.getSessionFactory();

    public Optional<Player> findByName(String name) {
        try (Session session = sessionFactory.getCurrentSession()) {
            session.beginTransaction();
            Optional<Player> player = session.createQuery("SELECT p FROM Player p WHERE name = :name", Player.class)
                    .setParameter("name", name)
                    .stream().findAny();
            session.getTransaction().commit();
            return player;
        }
    }

    public Player save(Player player) {
        try (Session session = sessionFactory.getCurrentSession()) {
            session.beginTransaction();
            Player savedPlayer = session.merge(player);
            session.getTransaction().commit();
            return savedPlayer;
        }
    }
}
