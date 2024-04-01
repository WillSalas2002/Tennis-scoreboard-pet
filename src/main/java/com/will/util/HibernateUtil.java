package com.will.util;

import com.will.model.Match;
import com.will.model.Player;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class HibernateUtil {
    private static final SessionFactory sessionFactory;

    static {
        try {
            Configuration configuration = new Configuration();

            // Set database connection properties from the application.properties file
            configuration.setProperty("hibernate.connection.url", PropertiesUtil.get("db.url"));
            configuration.setProperty("hibernate.connection.driver_class", PropertiesUtil.get("db.driver"));
            configuration.setProperty("hibernate.connection.username", PropertiesUtil.get("db.username"));
            configuration.setProperty("hibernate.connection.password", PropertiesUtil.get("db.password"));

            // Enable logging of SQL statements
            configuration.setProperty("hibernate.show_sql", "true");
            // Enable formatting of SQL statements
            configuration.setProperty("hibernate.format_sql", "true");
            // Set the session context
            configuration.setProperty("hibernate.current_session_context_class", "thread");
            // Configure connection pooling
            configuration.setProperty("hibernate.connection.pool_size", "10");
            // Map entity classes
            configuration.addAnnotatedClass(Player.class);
            configuration.addAnnotatedClass(Match.class);

            sessionFactory = configuration.buildSessionFactory();
        } catch (Exception e) {
            throw new ExceptionInInitializerError(e);
        }
    }

    public static SessionFactory getSessionFactory() {
        return sessionFactory;
    }
}
