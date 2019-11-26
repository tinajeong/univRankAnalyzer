package main.java.util.analyzer.dao;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class HibernateSessionFactory {
    private static final SessionFactory sessionFactory = buildSessionFactory();

    public static SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    private static SessionFactory buildSessionFactory()
    {
       return new Configuration().configure().buildSessionFactory();
    }
    public static void shutdown() {
        getSessionFactory().close();
    }
}
