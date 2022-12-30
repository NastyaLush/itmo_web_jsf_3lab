package com.example.demo.main.auth;


import com.example.demo.main.util.ConfigurationUtil;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;

public class UserRepository  {
    private final SessionFactory sessionFactory;

    public UserRepository() {
        sessionFactory = ConfigurationUtil.getResultManager();
    }
    public void save(User user) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        System.out.println(user);
        session.save(user);
        session.getTransaction().commit();
    }

    public boolean existsById(String id) {
        Session session = sessionFactory.openSession();
        return session.byId(User.class).getReference(id)!=null;
    }

    public boolean exists(User user) {
        boolean answer;
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        System.out.println(user);
        answer = session.get(User.class, user) != null;
        session.getTransaction().commit();
        return answer;
    }
}
