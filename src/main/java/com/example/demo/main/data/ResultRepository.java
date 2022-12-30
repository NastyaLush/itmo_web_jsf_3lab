package com.example.demo.main.data;


import com.example.demo.main.util.ConfigurationUtil;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;

import java.util.List;

public class ResultRepository {
    private final SessionFactory sessionFactory;

    public ResultRepository() {
        sessionFactory = ConfigurationUtil.getResultManager();
    }
    // TODO: 21.12.2022
    List<Result> findAll() {
        Query query = sessionFactory.openSession().createQuery("FROM Result ");
        System.out.println(query.list());
        return query.list();
    }

    Result save(Result result) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        System.out.println(result);
        session.save(result);
        session.getTransaction().commit();
        return result;
    }
}
