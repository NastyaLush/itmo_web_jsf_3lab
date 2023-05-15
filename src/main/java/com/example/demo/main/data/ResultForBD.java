package com.example.demo.main.data;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

import java.time.LocalDateTime;

import static jakarta.persistence.GenerationType.AUTO;
@Entity
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
@Table
public class ResultForBD extends Result{
    @Id
    @GeneratedValue(strategy = AUTO)
    private long id;
    private String login;

    public ResultForBD(String login, Result result) {
        super(result.getX(), result.getY(), result.getR(), result.getConclusion(), result.getNormalDate(), result.getScriptTime());
        this.login = login;
    }

    public ResultForBD() {

    }
}
