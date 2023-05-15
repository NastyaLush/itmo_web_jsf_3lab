package com.example.demo.main.util;

import com.example.demo.main.auth.User;
import com.example.demo.main.data.Result;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class ConfigurationUtil {
    public static SessionFactory getResultManager() {
        return new Configuration().configure("hibernate.cfg.xml").addAnnotatedClass(Result.class).buildSessionFactory();
    }
    public static SessionFactory getUserManager() {
        return new Configuration().configure("hibernate-user.cfg.xml").addAnnotatedClass(User.class).buildSessionFactory();
    }
}
