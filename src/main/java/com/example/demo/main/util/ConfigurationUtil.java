package com.example.demo.main.util;


import com.example.demo.main.data.Result;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class ConfigurationUtil {
    public static SessionFactory getResultManager() {
        return new Configuration().configure("src/main/resources/hibernate.cfg.xml").addAnnotatedClass(Result.class).buildSessionFactory();
    }

}
