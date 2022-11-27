package com.example.demo;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.io.File;


public class EntityManagerUtil {
    public static SessionFactory getEntityManager() {
        return new Configuration().configure("hibernate.cfg.xml").addAnnotatedClass(Result.class).buildSessionFactory();
    }
}