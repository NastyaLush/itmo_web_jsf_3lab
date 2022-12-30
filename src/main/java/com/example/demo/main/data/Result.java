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
public class Result {
    @Id
    @GeneratedValue(strategy = AUTO)
    private long id;
    private double x;
    private double y;
    private double r;
    private Conclusion conclusion;
    private LocalDateTime date;
    private long scriptTime;

    public Result(long id, double x, double y, double r, Conclusion conclusion, LocalDateTime date, long scriptTime) {
        this.id = id;
        this.x = x;
        this.y = y;
        this.r = r;
        this.conclusion = conclusion;
        this.date = date;
        this.scriptTime = scriptTime;
    }

    public Result(double x, double y, double r, Conclusion conclusion, LocalDateTime date, long scriptTime) {
        this.x = x;
        this.y = y;
        this.r = r;
        this.conclusion = conclusion;
        this.date = date;
        this.scriptTime = scriptTime;
    }

    public Result() {

    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public double getX() {
        return x;
    }

    public void setX(double x) {
        this.x = x;
    }

    public double getY() {
        return y;
    }

    public void setY(double y) {
        this.y = y;
    }

    public double getR() {
        return r;
    }

    public void setR(double r) {
        this.r = r;
    }

    public Conclusion getConclusion() {
        return conclusion;
    }

    public void setConclusion(Conclusion conclusion) {
        this.conclusion = conclusion;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    public long getScriptTime() {
        return scriptTime;
    }

    public void setScriptTime(long scriptTime) {
        this.scriptTime = scriptTime;
    }

    @Override
    public String toString() {
        return "Result{" +
                "id=" + id +
                ", x=" + x +
                ", y=" + y +
                ", r=" + r +
                ", conclusion=" + conclusion +
                ", date=" + date +
                ", scriptTime=" + scriptTime +
                '}';
    }
}
