package com.example.demo.main.data;


import com.example.demo.main.util.ConfigurationUtil;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;

import java.util.List;

public class ResultRepository {
    private final String salt="d2f3v232n3jb42j";
    private final String paper="fg2372bndejomwkejek4nbHBSouyg";
    private final SessionFactory sessionFactory;

    public ResultRepository() {
        sessionFactory = ConfigurationUtil.getResultManager();
    }
    // TODO: 21.12.2022
    List<Result> findAll(String token) {
        String user = paper+token+salt;
        Query query = sessionFactory.openSession().createQuery("FROM ResultForBD where login =:login");
        query.setParameter("login", user);
        return query.list();
    }

    Result save(Result result, String token) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        session.save(new ResultForBD(paper+token+salt, result));
        session.getTransaction().commit();
        return result;
    }
}
