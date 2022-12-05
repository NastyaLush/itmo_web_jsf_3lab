package com.example.demo.DBUtil;

import com.example.demo.data.Result;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class ConfigurationUtil {
    public static SessionFactory getEntityManager() {
        return new Configuration().configure("hibernate.cfg.xml").addAnnotatedClass(Result.class).buildSessionFactory();
    }
}