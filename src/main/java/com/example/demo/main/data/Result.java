package com.example.demo.main.data;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@MappedSuperclass
public class Result {

    private double x;
    private double y;
    private double r;
    private Conclusion conclusion;
    private LocalDateTime date;
    private long scriptTime;

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

    public String getDate() {
        return date.toString();
    }
    public LocalDateTime getNormalDate() {
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
                ", x=" + x +
                ", y=" + y +
                ", r=" + r +
                ", conclusion=" + conclusion +
                ", date=" + date +
                ", scriptTime=" + scriptTime +
                '}';
    }
}
