package com.example.demo.DBUtil;

import com.example.demo.data.Result;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;

import java.util.ArrayList;
import java.util.List;

public class DataBase {
    private final SessionFactory sessionFactory;

    public DataBase() {
        sessionFactory = ConfigurationUtil.getEntityManager();
    }


    public void setResult(Result result) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        System.out.println(result);
        session.save(result);
        session.getTransaction().commit();
    }

    public List<Result> listResult() {
        Query query = sessionFactory.openSession().createQuery("FROM Result ");
        return query.list();
    }

    public void clearBase() {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        session.createQuery("FROM Result ").list().forEach(e -> session.delete(e));
        session.getTransaction().commit();
    }

    public List<Result> getResults() {
        return new ArrayList<>();
    }

    public SessionFactory getSessionFactory() {
        return sessionFactory;
    }
}