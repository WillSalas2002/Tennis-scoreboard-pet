package com.will.repository;

import com.will.util.HibernateUtil;
import org.hibernate.SessionFactory;

public class NewMatchRepository {

    private final SessionFactory sessionFactory = HibernateUtil.getSessionFactory();

}
