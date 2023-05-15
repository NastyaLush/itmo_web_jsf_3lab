package com.example.demo.data;

import jakarta.persistence.*;

import javax.enterprise.context.SessionScoped;
import javax.inject.Named;
import java.io.Serializable;

@Named("result")
@SessionScoped
@Entity
@Table(name = "results")
public class Result implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue()
    @Column(name = "ID")
    private Integer id;
    @Column(name = "X")
    private Double x;
    @Column(name = "Y")
    private Double y;
    @Column(name = "R")
    private Double r;
    @Column(name = "RESULT")
    private ResultHit resultHit;
    @Column(name = "DATE")
    private String date;
    @Column(name = "TIME_SCRIPT")
    private Double timeScript;

    public Result(Double x, Double y, Double r, ResultHit resultHit, String date, Double timeScript) {

        this.x = x;
        this.y = y;
        this.r = r;
        this.resultHit = resultHit;
        this.date = date;
        this.timeScript = timeScript;
    }

    public Result() {

    }

    public Double getX() {
        return x;
    }

    public String getDate() {
        return date;
    }

    public Double getY() {
        return y;
    }

    public Double getR() {
        return r;
    }

    public ResultHit getResultHit() {
        return resultHit;
    }


    public Double getTimeScript() {
        return timeScript;
    }


    public void setX(Double x) {
        this.x = roundTo3(x);
    }

    public void setY(Double y) {
        this.y = roundTo3(y);
    }

    public void setR(Double r) {
        this.r = roundTo3(r);
    }

    public void setResultHit(ResultHit resultHit) {
        this.resultHit = resultHit;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public void setTimeScript(Double timeScript) {
        this.timeScript = timeScript;
    }

    private final double roundTo3(Double x) {
        return Math.floor(x * 1000) / 1000;
    }

    @Override
    public String toString() {
        return "Result{" +
                ", x=" + x +
                ", y=" + y +
                ", r=" + r +
                ", resultHit=" + resultHit +
                ", date=" + date +
                ", timeScript=" + timeScript +
                '}';
    }

}
